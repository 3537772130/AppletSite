package com.applet.apply.controller;

import com.applet.apply.config.annotation.CancelAuth;
import com.applet.apply.config.annotation.SessionScope;
import com.applet.apply.service.*;
import com.applet.common.entity.*;
import com.applet.common.entity.pay.WxUnifiedOrderResult;
import com.applet.common.util.enums.OrderEnums;
import com.applet.common.util.*;
import com.applet.common.util.encryption.EncryptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2020/4/16
 * Time: 9:55
 * To change this template use File | Settings | File Templates.
 * Description:微信支付控制层
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/applet/pay/")
public class WeChantPayController {
    @Autowired
    @Lazy
    private UserOrderService userOrderService;
    @Autowired
    private AppletService appletService;
    @Autowired
    private WeChantService weChantService;
    @Autowired
    private WeChantPayService weChantPayService;
    @Autowired
    private UserCouponService userCouponService;

    /**
     * 发起微信统一下单请求 - 商家测试
     *
     * @param appletInfo
     * @param weChantInfo
     * @param ipAddress
     * @return
     */
    @RequestMapping(value = "sendWeChantUnifiedOrderTest")
    public Object sendWeChantUnifiedOrderTest(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                                              @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                              @SessionScope(Constants.CLIENT_PUBLIC_IP) String ipAddress) {
        try {
            if (appletInfo.getUserId().intValue() != weChantInfo.getUserId().intValue()) {
                return AjaxResponse.error("非法操作");
            }
            if (NullUtil.isNullOrEmpty(appletInfo.getLat()) || NullUtil.isNullOrEmpty(appletInfo.getLon())) {
                return AjaxResponse.error("店铺尚未定位，请先设置定位信息！");
            }
            long count = userOrderService.countTestOrder(weChantInfo.getAppletId(), weChantInfo.getId(), weChantInfo.getUserId());
            if (count > 4) {
                return AjaxResponse.error("操作频繁，请稍后再来");
            }
            AppletInfo a = appletService.selectAppletInfo(appletInfo.getId());
            if (NullUtil.isNullOrEmpty(a.getMchId()) || NullUtil.isNullOrEmpty(a.getPayKey())) {
                return AjaxResponse.msg("0", "您交易所需资料缺失，请上传【微信支付-商家平台】资料。");
            }
            WeChantInfo w = weChantService.selectWeChantInfo(a.getId(), weChantInfo.getUserId());
            //创建测试订单
            OrderInfo order = new OrderInfo();
            order.setOrderNo(RandomUtil.getTimeStamp());
            order.setAppletId(a.getId());
            order.setWxId(w.getId());
            order.setUserId(w.getUserId());
            order.setUserRemark(Constants.TEST_ORDER_REMARK);
            order.setTotalAmount(0.01d);
            order.setActualAmount(0.01d);
            order.setOrderStatus(0);
            order.setCreateTime(new Date());
            order.setPayType(1);
            order.setPayStatus(0);
            order.setPayChannel(OrderEnums.PayChannel.WX_JSAPI.getName());
            userOrderService.updateOrderRelevant(order, OrderEnums.OperateStatus.MAKE.getCode());

            ViewOrderPayData data = new ViewOrderPayData();
            data.setId(order.getId());
            data.setOrderNo(order.getOrderNo());
            data.setActualAmount(order.getActualAmount());
            data.setAppletCode(a.getAppletCode());
            data.setAppletName(NullUtil.isNotNullOrEmpty(a.getAppletSimple()) ? a.getAppletSimple() : a.getAppletName());
            data.setAppId(EncryptionUtil.decryptAppletRSA(a.getAppId()));
            data.setMchId(EncryptionUtil.decryptAppletRSA(a.getMchId()));
            data.setPayKey(EncryptionUtil.decryptAppletRSA(a.getPayKey()));
            data.setPayChannel(OrderEnums.PayChannel.WX_JSAPI.getCode());
            data.setOpenId(w.getOpenId());
            String prepayId = weChantPayService.sendWeChantUnifiedOrder(data, ipAddress);
            if (NullUtil.isNotNullOrEmpty(prepayId)) {
                // 获取小程序支付信息，并进行加密
                String msg = EncryptionUtil.encryptAppletPayInfoAES(data.getAppId(), data.getPayKey(), prepayId);
                Map map = new HashMap();
                map.put("orderId", order.getId());
                map.put("sign", msg);
                return AjaxResponse.success(map);
            }
        } catch (Exception e) {
            log.error("测试--发送微信统一下单请求出错{}", e);
        }
        return AjaxResponse.error("支付失败");
    }

    /**
     * 发起微信统一下单请求
     *
     * @param weChantInfo
     * @param ipAddress
     * @param orderId
     * @return
     */
    @PostMapping(value = "sendWeChantUnifiedOrder")
    public Object sendWeChantUnifiedOrder(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                          @SessionScope(Constants.CLIENT_PUBLIC_IP) String ipAddress,
                                          @RequestParam("orderId") Integer orderId) {
        try {
            ViewOrderPayData data = userOrderService.selectOrderData(orderId, weChantInfo.getAppletId(), weChantInfo.getId());
            if (null != data && data.getPayStatus().intValue() == OrderEnums.PayStatus.WAIT.getCode()) {
                data.setPayChannel(OrderEnums.PayChannel.WX_JSAPI.getCode());
                data.setAppId(EncryptionUtil.decryptAppletRSA(data.getAppId()));
                data.setMchId(EncryptionUtil.decryptAppletRSA(data.getMchId()));
                data.setPayKey(EncryptionUtil.decryptAppletRSA(data.getPayKey()));
                String prepayId = weChantPayService.sendWeChantUnifiedOrder(data, ipAddress);
                if (NullUtil.isNotNullOrEmpty(prepayId)) {
                    String msg = EncryptionUtil.encryptAppletPayInfoAES(data.getAppId(), data.getPayKey(), prepayId);
                    return AjaxResponse.success(msg);
                }
            }
        } catch (Exception e) {
            log.error("发送微信统一下单请求出错{}", e);
        }
        return AjaxResponse.error("发起支付失败");
    }


    /**
     * 支付成功，检测订单支付状态
     *
     * @param weChantInfo
     * @param orderId
     * @return
     */
    @RequestMapping(value = "loadOrderByPaySuccess")
    public Object loadOrderByPaySuccess(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                          @RequestParam("orderId") Integer orderId) {
        try {
            if (NullUtil.isNullOrEmpty(weChantInfo.getAppletId()) || NullUtil.isNullOrEmpty(weChantInfo.getUserId()) || NullUtil.isNullOrEmpty(orderId)) {
                return AjaxResponse.error("参数错误");
            }
            ViewOrderInfo order = userOrderService.selectViewOrderInfoByUser(orderId, weChantInfo.getUserId());
            if (order.getPayStatus().intValue() == OrderEnums.PayStatus.SUCCESS.getCode().intValue()
                    && order.getOrderStatus().intValue() == OrderEnums.OrderStatus.WAIT.getCode().intValue()
                    && order.getOperateStatus().intValue() == OrderEnums.OperateStatus.SUBMIT.getCode().intValue()) {
                long count = userCouponService.selectUserCouponCountByOrder(weChantInfo.getUserId(), orderId);
                if (count > 0) {
                    return AjaxResponse.msg("0", "下单成功");
                }
                return AjaxResponse.success("下单成功");
            }
        } catch (NumberFormatException e) {
            log.error("支付成功，更新订单出错{}", e);
        }
        return AjaxResponse.error("订单状态不符");
    }

    /**
     * 支付失败，订单信息更新
     *
     * @param weChantInfo
     * @param orderId
     */
    @RequestMapping(value = "updateOrderByPayFail")
    public void updateOrderByPayFail(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                       @RequestParam("orderId") Integer orderId) {
        try {
            if (NullUtil.isNullOrEmpty(weChantInfo.getAppletId()) || NullUtil.isNullOrEmpty(weChantInfo.getUserId()) || NullUtil.isNullOrEmpty(orderId)) {
                return;
            }
            OrderInfo order = userOrderService.selectOrderInfo(orderId, weChantInfo.getAppletId(), weChantInfo.getUserId());
            if (order.getPayStatus().intValue() == OrderEnums.PayStatus.WAIT.getCode().intValue()
                    && order.getOrderStatus().intValue() == OrderEnums.OrderStatus.WAIT.getCode().intValue()) {
                userOrderService.updateOrderRelevant(order, OrderEnums.OperateStatus.CANCEL.getCode());
            }
        } catch (NumberFormatException e) {
            log.error("支付失败，更新订单出错{}", e);
        }
    }

    /**
     * 微信订单支付回调通知
     *
     * @param xml
     * @return
     */
    @RequestMapping(value = "orderPayNotice")
    @CancelAuth
    public Object orderPayNotice(@RequestBody String xml) {
        WxUnifiedOrderResult result = null;
        try {
            log.info("微信订单支付回调信息：{}", xml);
            if (NullUtil.isNotNullOrEmpty(xml) && !xml.equals("null")) {
                result = weChantPayService.orderPayNoticeHandle(xml);
            }
        } catch (Exception e) {
            log.error("微信订单支付回调出错{}", e);
            result = new WxUnifiedOrderResult();
            result.setReturnCode("FAIL");
            result.setReturnMsg("Parameter format check error");
        }
        if (null == result) {
            return "";
        }
        String resultXML = JaxbUtil.convertToXmlIgnoreXmlHead(result, "UTF-8");
        return resultXML;
    }
}
