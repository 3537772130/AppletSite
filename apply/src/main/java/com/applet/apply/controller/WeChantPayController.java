package com.applet.apply.controller;

import com.applet.apply.config.annotation.CancelAuth;
import com.applet.apply.config.annotation.SessionScope;
import com.applet.apply.service.*;
import com.applet.common.entity.*;
import com.applet.common.entity.pay.WxUnifiedOrderResult;
import com.applet.common.enums.OrderEnums;
import com.applet.common.util.*;
import com.applet.common.util.encryption.EncryptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
            long count = userOrderService.countOrderRequestRecordByHour(OrderEnums.PayChannel.WX_JSAPI.getName() + "_REQUEST_TEST");
            if (count > 5){
                return AjaxResponse.error("操作频繁，请稍后再来");
            }
            AppletInfo a = appletService.selectAppletInfo(appletInfo.getId());
            if (NullUtil.isNullOrEmpty(a.getMchId()) || NullUtil.isNullOrEmpty(a.getPayKey())) {
                return AjaxResponse.msg("0", "您交易所需资料缺失，请上传【微信支付-商家平台】资料。");
            }
            WeChantInfo w = weChantService.selectWeChantInfo(a.getId(), weChantInfo.getUserId());
            ViewOrderPayData data = new ViewOrderPayData();
            data.setOrderNo(RandomUtil.getTimeStamp());
            data.setActualAmount(0.01d);
            data.setAppletCode(a.getAppletCode());
            data.setAppletName(NullUtil.isNotNullOrEmpty(a.getAppletSimple()) ? a.getAppletSimple() : a.getAppletName());
            data.setAppId(EncryptionUtil.decryptAppletRSA(a.getAppId()));
            data.setMchId(EncryptionUtil.decryptAppletRSA(a.getMchId()));
            data.setPayKey(EncryptionUtil.decryptAppletRSA(a.getPayKey()));
            data.setPayChannel(OrderEnums.PayChannel.WX_JSAPI.getCode());
            data.setOpenId(w.getOpenId());
            String prepayId = weChantPayService.sendWeChantUnifiedOrder(data, ipAddress);
            if (NullUtil.isNotNullOrEmpty(prepayId)) {
                String msg = EncryptionUtil.encryptAppletPayInfoAES(a.getAppId(), a.getPayKey(), prepayId);
                return AjaxResponse.success(msg);
            }
        } catch (Exception e) {
            log.error("测试--发送微信统一下单请求出错{}", e);
        }
        return AjaxResponse.error("支付失败");
    }

    /**
     * 发起微信统一下单请求
     *
     * @param appletInfo
     * @param weChantInfo
     * @param ipAddress
     * @param id
     * @return
     */
    @PostMapping(value = "sendWeChantUnifiedOrder")
    public Object sendWeChantUnifiedOrder(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                                          @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                          @SessionScope(Constants.CLIENT_PUBLIC_IP) String ipAddress,
                                          @RequestParam("id") String id) {
        try {
            Integer orderId = NullUtil.isNotNullOrEmpty(id) ? Integer.parseInt(id) : null;
            ViewOrderPayData data = userOrderService.selectOrderData(orderId, appletInfo.getId(), weChantInfo.getId());
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
     * 微信订单支付回调通知
     *
     * @param object
     * @return
     */
    @RequestMapping(value = "orderPayNotice")
    @CancelAuth
    public Object orderPayNotice(Object object) {
        WxUnifiedOrderResult result = null;
        try {
            String xml = object.toString();
            log.info("微信订单支付回调信息\n{}", xml);
            result = weChantPayService.orderPayNoticeHandle(xml);
        } catch (Exception e) {
            log.error("微信订单支付回调出错{}", e);
            result = new WxUnifiedOrderResult();
            result.setReturnCode("FAIL");
            result.setReturnMsg("参数格式校验错误");
        }
        if (null == result){
            return null;
        }
        String resultXML = JaxbUtil.convertToXmlIgnoreXmlHead(result, "UTF-8");
        return resultXML;
    }
}
