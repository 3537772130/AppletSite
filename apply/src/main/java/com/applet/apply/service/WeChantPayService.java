package com.applet.apply.service;

import com.applet.common.entity.*;
import com.applet.common.entity.pay.WxUnifiedOrder;
import com.applet.common.entity.pay.WxUnifiedOrderResult;
import com.applet.common.util.enums.OrderEnums;
import com.applet.common.util.*;
import com.applet.common.util.encryption.EncryptionUtil;
import com.applet.common.util.wechant.WeChatAppletUtil;
import jodd.datetime.JDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
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
    @Autowired
    private AppletService appletService;


    public String sendWeChantUnifiedOrder(ViewOrderPayData data, String ipAddress) {
        return sendWeChantUnifiedOrder(data, null, ipAddress);
    }

    /**
     * 微信统一下单
     *
     * @param data      订单参数信息
     * @param goodsId
     * @param ipAddress
     * @return
     */
    public String sendWeChantUnifiedOrder(ViewOrderPayData data, String goodsId, String ipAddress) {
        // 设置微信统一下单信息
        WxUnifiedOrder wo = new WxUnifiedOrder();
        wo.setOut_trade_no(data.getOrderNo());
        wo.setBody(data.getOrderNo() + "-订单支付");
        wo.setDevice_info(data.getAppletCode());
        wo.setAttach(data.getAppletName());
        wo.setAppid(data.getAppId());
        wo.setMch_id(data.getMchId());
        wo.setTotal_fee((int) Arith.mul(data.getActualAmount(), 100.0d));
        wo.setTrade_type(data.getPayChannel());
        wo.setOpenid(data.getOpenId());

        wo.setProduct_id(goodsId);
        wo.setNonce_str(RandomUtil.getRandomStr32());
        wo.setSign_type("MD5");
        wo.setDetail(null);
        wo.setFee_type("CNY");
        wo.setSpbill_create_ip(ipAddress);
        JDateTime time = new JDateTime(new Date());
        wo.setTime_start(time.toString(Constants.DEFAULT_DATE_FORMAT_STAMP));
        wo.setTime_expire(time.addHour(2).toString(Constants.DEFAULT_DATE_FORMAT_STAMP));
        wo.setGoods_tag(null);
        wo.setNotify_url(NullUtil.getWebSite() + "/api/applet/pay/orderPayNotice");
        wo.setLimit_pay(null);
        wo.setReceipt(null);
        wo.setScene_info(null);

        wo.setSign(WeChatAppletUtil.getUnifiedSign(wo, data.getPayKey()));
        // 将订单信息转换成XML
        String xml = JaxbUtil.convertToXmlIgnoreXmlHead(wo, "UTF-8");
//        System.out.println(xml);
        // 发送微信统一下单请求
        String resultXML = WeChatAppletUtil.sendWeChantUnifiedOrderToPOST(xml);
        WxUnifiedOrderResult result = JaxbUtil.converyToJavaBean(resultXML, WxUnifiedOrderResult.class);
        Boolean bool = analysisWeChantUnifiedOrderResult(data.getId(), wo, resultXML, result);
        return bool ? result.getPrepayId() : null;
    }

    /**
     * 解析返回的微信统一下单结果
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
        record.setOrderNo(wo.getOut_trade_no());
        record.setDeviceNo(wo.getDevice_info());
        record.setRequestType("WX_" + wo.getTrade_type() + "_REQUEST" + (NullUtil.isNullOrEmpty(orderId) ? "_TEST" : ""));
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
        OrderInfo order = userOrderService.selectOrderInfoById(orderId);
        if (null != order) {
            order.setId(orderId);
            order.setPayRelationId(result.getPrepayId());
            order.setPayChannel(record.getRequestType());
            if (bool) {
                userOrderService.updateOrderRelevant(order, record, OrderEnums.OperateStatus.LAUNCH_PAY.getCode());
            } else {
                // 统一下单成功和余额不足以外的状况，则订单不再继续，支付失败且订单失败
                order.setOrderStatus(OrderEnums.OrderStatus.FAIL.getCode());
                userOrderService.updateOrderRelevant(order, record, OrderEnums.OperateStatus.SUBMIT_FAIL.getCode());
            }
        }
        return bool;
    }


    /**
     * 微信订单支付通知处理
     *
     * @param xml
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public WxUnifiedOrderResult orderPayNoticeHandle(String xml) throws Exception {
        WxUnifiedOrderResult result = JaxbUtil.converyToJavaBean(xml, WxUnifiedOrderResult.class);
        if (result.getReturnCode().equals("SUCCESS")) {
            OrderInfo order = userOrderService.selectOrderInfo(result.getOutTradeNo());
            if (null != order
                    && order.getOrderStatus().intValue() == OrderEnums.OrderStatus.WAIT.getCode().intValue()
                    && order.getPayStatus().intValue() == OrderEnums.PayStatus.WAIT.getCode().intValue()) {
                AppletInfo appletInfo = appletService.selectAppletInfo(order.getAppletId(), order.getUserId());
                // 测试订单
                log.info("此次是商家测试订单回调，订单号为：{}", order.getOrderNo());
                appletInfo.setAppId(EncryptionUtil.decryptAppletRSA(appletInfo.getAppId()));
                appletInfo.setMchId(EncryptionUtil.decryptAppletRSA(appletInfo.getMchId()));
                appletInfo.setPayKey(EncryptionUtil.decryptAppletRSA(appletInfo.getPayKey()));
                Integer totalFee = (int) Arith.mul(order.getActualAmount(), 100.0d);
                // 对支付回调的信息进行校验
                if (appletInfo.getAppId().equals(result.getAppid())
                        && appletInfo.getMchId().equals(result.getMchId())
                        && appletInfo.getAppletCode().equals(result.getDeviceInfo())
                        && !order.getPayRelationId().equals(result.getTransactionId())
                        && totalFee.toString().equals(result.getTotalFee())) {
                    // 添加支付请求记录，并更新订单支付状态
                    OrderRequestRecord record = new OrderRequestRecord();
                    record.setOrderId(order.getId());
                    record.setOrderNo(order.getOrderNo());
                    record.setDeviceNo(appletInfo.getAppletCode());
                    record.setRequestType("WX_" + result.getTradeType() + "_BACK");
                    record.setResultCode(result.getResultCode());
                    record.setErrCode(result.getErrCode());
                    record.setErrCodeDes(result.getErrCodeDes());
                    record.setRequestResultMsg(xml);
                    // 设置操作状态默认为删除
                    Integer operateStatus = OrderEnums.OperateStatus.DELETE.getCode();
                    if (result.getResultCode().equals("SUCCESS")) {
                        if (order.getUserRemark().equals(Constants.TEST_ORDER_REMARK)) {
                            // 测试订单交易成功，更新小程序交易开通状态
                            appletService.updateAppletIFPayOpen(order.getAppletId());
                        } else {
                            operateStatus = OrderEnums.OperateStatus.SUBMIT.getCode();
                        }
                    } else {
                        operateStatus = OrderEnums.OperateStatus.SUBMIT_FAIL.getCode();
                    }
                    userOrderService.updateOrderRelevant(order, record, operateStatus);
                    // 校验通过
                    result = new WxUnifiedOrderResult();
                    result.setReturnCode("SUCCESS");
                    result.setReturnMsg("OK");
                } else {
                    // 信息校验不通过
                    result = new WxUnifiedOrderResult();
                    result.setReturnCode("FAIL");
                    result.setReturnMsg("Signature verification failed");
                }
                log.info("已对订单【{}】的支付回调进行处理，处理结果为：{}", order.getOrderNo(), result.getReturnCode());
            } else {
                // 交易已成功处理，直接返回
                log.info("订单状态已更新，此次微信支付回调不做处理！");
                result = new WxUnifiedOrderResult();
                result.setReturnCode("SUCCESS");
                result.setReturnMsg("OK");
            }
            return result;
        }
        return null;
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
