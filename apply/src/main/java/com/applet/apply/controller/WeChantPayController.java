package com.applet.apply.controller;

import com.alibaba.fastjson.JSONObject;
import com.applet.apply.config.annotation.CancelAuth;
import com.applet.apply.config.annotation.SessionScope;
import com.applet.apply.service.AppletService;
import com.applet.apply.service.UserOrderService;
import com.applet.apply.service.WeChantPayService;
import com.applet.apply.service.WeChantService;
import com.applet.common.entity.*;
import com.applet.common.util.AjaxResponse;
import com.applet.common.util.Constants;
import com.applet.common.util.NullUtil;
import com.applet.common.util.RandomUtil;
import com.applet.common.util.encryption.EncryptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserOrderService userOrderService;
    @Autowired
    private AppletService appletService;
    @Autowired
    private WeChantService weChantService;
    @Autowired
    private WeChantPayService weChantPayService;

    @RequestMapping(value = "payBack")
    @CancelAuth
    public void payBack(Object object) {
        String result = JSONObject.toJSONString(object);
        log.info("支付回调获取到信息{}", result);
    }

    /**
     * 发起微信统一下单请求 - 商家测试
     *
     * @param appletInfo
     * @param weChantInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "sendWeChantUnifiedOrderTest")
    public Object sendWeChantUnifiedOrderTest(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                                              @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                              HttpServletRequest request) {
        try {
            if (appletInfo.getUserId().intValue() != weChantInfo.getUserId().intValue()) {
                return AjaxResponse.error("非法操作");
            }
            AppletInfo a = appletService.selectAppletInfo(appletInfo.getId());
            if (NullUtil.isNullOrEmpty(a.getMchId()) || NullUtil.isNullOrEmpty(a.getPayKey())) {
                return AjaxResponse.msg("0","您交易所需资料缺失，请登录PC端上传【微信支付-商家平台】资料。");
            }
            WeChantInfo w = weChantService.selectWeChantInfo(a.getId(), weChantInfo.getUserId());
            OrderInfo o = new OrderInfo();
            o.setOrderNo(RandomUtil.getTimeStamp());
            o.setActualAmount(0.01d);
            String appletName = NullUtil.isNotNullOrEmpty(a.getAppletSimple()) ? a.getAppletSimple() : a.getAppletName();
            String appid = EncryptionUtil.decryptAppletRSA(a.getAppId());
            String mchId = EncryptionUtil.decryptAppletRSA(a.getMchId());
            String payKey = EncryptionUtil.decryptAppletRSA(a.getPayKey());
            String tradeType = Constants.TRADE_TYPE_JSAPI;
            String prepayId = weChantPayService.sendWeChantUnifiedOrder(a.getAppletCode(), appletName, appid,
                    mchId, payKey, w.getOpenId(), o.getId(), o.getOrderNo(), o.getActualAmount(), tradeType,
                    null, request);
            if (NullUtil.isNotNullOrEmpty(prepayId)) {
                String msg = EncryptionUtil.encryptAppletPayInfoAES(appid, payKey, prepayId);
                return AjaxResponse.success(msg);
            }
        } catch (Exception e) {
            log.error("测试--发送微信统一下单请求出错{}", e);
        }
        return AjaxResponse.error("发起支付失败");
    }

    /**
     * 发起微信统一下单请求
     *
     * @param appletInfo
     * @param weChantInfo
     * @param id
     * @param request
     * @return
     */
    @PostMapping(value = "sendWeChantUnifiedOrder")
    public Object sendWeChantUnifiedOrder(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                                          @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                          @RequestParam("id") String id, HttpServletRequest request) {
        try {
            Integer orderId = NullUtil.isNotNullOrEmpty(id) ? Integer.parseInt(id) : null;
            AppletInfo a = appletService.selectAppletInfo(appletInfo.getId());
            if (!a.getIfOpenPay()) {
                return AjaxResponse.error("尚未开通在线支付");
            }
            WeChantInfo w = weChantService.selectWeChantInfo(a.getId(), weChantInfo.getUserId());
            OrderInfo o = userOrderService.selectOrderInfo(orderId, a.getId(), w.getUserId());
            if (null == a || w == null || o == null) {
                return AjaxResponse.error("发起支付出错");
            }

            String appletName = NullUtil.isNotNullOrEmpty(a.getAppletSimple()) ? a.getAppletSimple() : a.getAppletName();
            String appid = EncryptionUtil.decryptAppletRSA(a.getAppId());
            String mchId = EncryptionUtil.decryptAppletRSA(a.getMchId());
            String payKey = EncryptionUtil.decryptAppletRSA(a.getPayKey());
            String tradeType = Constants.TRADE_TYPE_JSAPI;
            String prepayId = weChantPayService.sendWeChantUnifiedOrder(a.getAppletCode(), appletName, appid, mchId,
                    payKey, w.getOpenId(), o.getId(), o.getOrderNo(), o.getActualAmount(), tradeType,
                    null, request);
            if (NullUtil.isNotNullOrEmpty(prepayId)) {
                String msg = EncryptionUtil.encryptAppletPayInfoAES(appid, payKey, prepayId);
                return AjaxResponse.success(msg);
            }
        } catch (Exception e) {
            log.error("发送微信统一下单请求出错{}", e);
        }
        return AjaxResponse.error("发起支付失败");
    }


}
