package com.applet.apply.service;

import com.applet.common.entity.*;
import com.applet.common.entity.pay.WxUnifiedOrder;
import com.applet.common.entity.pay.WxUnifiedOrderResult;
import com.applet.common.enums.OrderEnums;
import com.applet.common.util.*;
import com.applet.common.util.encryption.EncryptionUtil;
import jodd.datetime.JDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2020/4/16
 * Time: 16:19
 * To change this template use File | Settings | File Templates.
 * Description: 微信统一下单参
 * 考文档地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
 */
@Slf4j
@Service
public class WeChantPayService {
    @Autowired
    @Lazy
    private UserOrderService userOrderService;
    @Autowired
    private UserCartService userCartService;
    @Autowired
    private UserCouponService userCouponService;

    /**
     * 微信统一下单
     *
     * @param data      订单参数信息
     * @param goodsId
     * @param ipAddress
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String sendWeChantUnifiedOrder(ViewOrderPayData data, String goodsId, String ipAddress) {
        // 设置微信统一下单信息
        WxUnifiedOrder wo = new WxUnifiedOrder();
        wo.setOutTradeNo(data.getOrderNo());
        wo.setBody(data.getOrderNo() + "-订单支付");
        wo.setDeviceInfo(data.getAppletCode());
        wo.setAttach(data.getAppletName());
        wo.setAppid(data.getAppId());
        wo.setMchId(data.getMchId());
        wo.setTotalFee((int) Arith.mul(data.getActualAmount(), 100.0d));
        wo.setTradeType(data.getPayChannel());
        wo.setOpenid(data.getOpenId());

        wo.setProductId(goodsId);
        wo.setNonceStr(RandomUtil.getRandomStr32());
        wo.setSignType("MD5");
        wo.setDetail(null);
        wo.setFeeType("CNY");
        wo.setSpbillCreateIp(ipAddress);
        JDateTime time = new JDateTime(new Date());
        wo.setTimeStart(time.toString(Constants.DEFAULT_DATE_FORMAT_STAMP));
        wo.setTimeExpire(time.addHour(2).toString(Constants.DEFAULT_DATE_FORMAT_STAMP));
        wo.setGoodsTag(null);
        wo.setNotifyUrl(NullUtil.getWebSite() + "/api/applet/orderPayNotice");
        wo.setLimitPay(null);
        wo.setReceipt(null);
        wo.setSceneInfo(null);

        wo.setSign(WeChatAppletUtil.getUnifiedSign(wo, data.getPayKey()));
        // 将订单信息转换成XML
        String xml = JaxbUtil.convertToXmlIgnoreXmlHead(wo, "UTF-8");
        // 发送微信统一下单请求
        String resultXML = WeChatAppletUtil.sendWeChantUnifiedOrderToPOST(xml);
        WxUnifiedOrderResult result = JaxbUtil.converyToJavaBean(resultXML, WxUnifiedOrderResult.class);
        Boolean bool = analysisWeChantUnifiedOrderResult(data.getId(), wo, resultXML, result);
        return bool ? result.getPrepayId() : null;
    }

    public String sendWeChantUnifiedOrder(ViewOrderPayData data, String ipAddress) {
        return sendWeChantUnifiedOrder(data, null, ipAddress);
    }

    /**
     * 解析返回的斡旋统一下单结果
     *
     * @param result
     * @param orderId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean analysisWeChantUnifiedOrderResult(Integer orderId, WxUnifiedOrder wo, String resultXML, WxUnifiedOrderResult result) {
        Boolean bool = false;
        OrderRequestRecord record = new OrderRequestRecord();
        record.setOrderId(orderId);
        record.setOrderNo(wo.getOutTradeNo());
        record.setDeviceNo(wo.getDeviceInfo());
        record.setRequestType("WX_" + wo.getTradeType() + "_REQUEST" + (NullUtil.isNullOrEmpty(orderId) ? "_TEST" : ""));
        record.setResultCode(result.getReturnCode());
        if (result.getReturnCode().equals("SUCCESS")) {
            if (result.getResultCode().equals("SUCCESS")) {
                bool = true;
                record.setResultCode(result.getResultCode());
            } else {
                bool = record.getErrCode().equals("NOTENOUGH");
            }
            record.setErrCode(result.getErrCode());
            record.setErrCodeDes(result.getErrCodeDes());
        } else {
            record.setErrCodeDes(result.getReturnMsg());
        }
        record.setRequestResultMsg(resultXML);
        OrderInfo order = null;
        if (NullUtil.isNotNullOrEmpty(orderId)) {
            OrderInfo orderInfo = userOrderService.selectOrderInfoById(orderId);
            order = new OrderInfo();
            order.setId(orderInfo.getId());
            order.setPayRelationId(result.getPrepayId());
            order.setPayChannel(record.getRequestType());
            // 统一下单成功和余额不足以外的状况，则订单不再继续，支付失败
            if (!bool) {
                order.setPayStatus(-1);
                order.setOrderStatus(-1);
            }
        }
        // 添加订单支付请求记录，并更新订单信息
        userOrderService.updateOrder(record, order);
        return bool;
    }


    /**
     * 微信订单支付通知处理
     *
     * @param xml
     * @return
     * @throws Exception
     */
    public WxUnifiedOrderResult orderPayNoticeHandle(String xml) throws Exception {
        WxUnifiedOrderResult result = JaxbUtil.converyToJavaBean(xml, WxUnifiedOrderResult.class);
        if (result.getReturnCode().equals("SUCCESS")) {
            String outTradeNo = result.getOutTradeNo();
            ViewOrderPayData data = userOrderService.selectOrderData(outTradeNo);
            if (null != data) {
                data.setAppId(EncryptionUtil.decryptAppletRSA(data.getAppId()));
                data.setMchId(EncryptionUtil.decryptAppletRSA(data.getMchId()));
                data.setPayKey(EncryptionUtil.decryptAppletRSA(data.getPayKey()));
                Integer totalFee = (int) Arith.mul(data.getActualAmount(), 100.0d);
                // 对支付回调的信息进行校验
                if (data.getAppId().equals(result.getAppid()) && data.getMchId().equals(result.getMchId())
                        && data.getAppletCode().equals(result.getDeviceInfo())
                        && !data.getPayRelationId().equals(result.getTransactionId())
                        && totalFee.toString().equals(result.getTotalFee())) {
                    // 校验通过
                    result = new WxUnifiedOrderResult();
                    result.setReturnCode("SUCCESS");
                    result.setReturnMsg("OK");
                    // 添加支付请求记录，并更新订单支付状态
                    OrderRequestRecord record = new OrderRequestRecord();
                    record.setOrderId(data.getId());
                    record.setOrderNo(data.getOrderNo());
                    record.setDeviceNo(data.getAppletCode());
                    record.setRequestType("WX_" + result.getTradeType() + "_BACK");
                    record.setResultCode(result.getResultCode());
                    record.setErrCode(result.getErrCode());
                    record.setErrCodeDes(result.getErrCodeDes());
                    record.setRequestResultMsg(xml);
                    OrderInfo info = new OrderInfo();
                    info.setId(data.getId());
                    info.setPayStatus(-1);
                    if (result.getResultCode().equals("SUCCESS")) {
                        info.setPayStatus(1);
                    }
                    userOrderService.updateOrder(record, info);
                } else {
                    // 信息校验不通过
                    result = new WxUnifiedOrderResult();
                    result.setReturnCode("FAIL");
                    result.setReturnMsg("签名失败");
                }
            } else {
                // 交易已成功处理，直接返回
                result = new WxUnifiedOrderResult();
                result.setReturnCode("SUCCESS");
                result.setReturnMsg("OK");
            }
            return result;
        }
        return null;
    }

    /**
     * 支付成功，更新预下订单操作记录
     * @param order
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateOrderOperateStatusByPaySuccess(ViewOrderDetails order) {
        // 更新预下单状态
        OrderOperateRecord record = new OrderOperateRecord();
        record.setOrderId(order.getId());
        record.setOperateUserId(order.getUserId());
        record.setOperateTime(new Date());
        record.setOperateStatus(1);
        userOrderService.addOrderOperateRecord(record);
        // 更新购物车状态
        List<OrderDetails> list = userOrderService.selectOrderDetailsList(order.getId());
        List<Integer> idList = new ArrayList<>();
        for (OrderDetails details : list) {
            idList.add(details.getGoodsSpecsId());
        }
        userCartService.updateUserCartStatus(order.getId(), order.getAppletId(), order.getWxId(), idList);
        if (NullUtil.isNotNullOrEmpty(order.getUserCouponId())) {
            // 更新优惠券状态为已使用
            userCouponService.updateUserCouponStatus(order.getUserCouponId(), OrderEnums.UserCouponStatus.USED.getCode());
        }
    }

    /**
     * 支付失败，更新预下订单操作记录
     * @param order
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateOrderOperateStatusByPayFail(ViewOrderDetails order) {
        // 更新预下单状态
        OrderOperateRecord record = new OrderOperateRecord();
        record.setOrderId(order.getId());
        record.setOperateUserId(order.getUserId());
        record.setOperateTime(new Date());
        record.setOperateStatus(-2);
        userOrderService.addOrderOperateRecord(record);
        if (NullUtil.isNotNullOrEmpty(order.getUserCouponId())) {
            // 更新优惠券状态为未使用
            userCouponService.updateUserCouponStatus(order.getUserCouponId(), OrderEnums.UserCouponStatus.UNUSED.getCode());
        }
    }


    public static void main(String[] arge) {
        String resultXML = "<xml>\n" +
                "  <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "  <attach><![CDATA[支付测试]]></attach>\n" +
                "  <bank_type><![CDATA[CFT]]></bank_type>\n" +
                "  <fee_type><![CDATA[CNY]]></fee_type>\n" +
                "  <is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
                "  <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "  <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>\n" +
                "  <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>\n" +
                "  <out_trade_no><![CDATA[1409811653]]></out_trade_no>\n" +
                "  <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "  <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>\n" +
                "  <time_end><![CDATA[20140903131540]]></time_end>\n" +
                "  <total_fee>1</total_fee>\n" +
                "<coupon_fee><![CDATA[10]]></coupon_fee>\n" +
                "<coupon_count><![CDATA[1]]></coupon_count>\n" +
                "<coupon_type><![CDATA[CASH]]></coupon_type>\n" +
                "<coupon_id><![CDATA[10000]]></coupon_id>\n" +
                "<coupon_fee><![CDATA[100]]></coupon_fee>\n" +
                "  <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "  <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>\n" +
                "</xml>";
        WxUnifiedOrderResult result = JaxbUtil.converyToJavaBean(resultXML, WxUnifiedOrderResult.class);
        System.out.println(result.getReturnCode());
    }
}
