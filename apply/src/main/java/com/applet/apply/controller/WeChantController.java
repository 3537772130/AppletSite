package com.applet.apply.controller;

import com.applet.apply.config.annotation.CancelAuthentication;
import com.applet.apply.config.annotation.SessionScope;
import com.applet.apply.service.AuthCodeService;
import com.applet.apply.service.FigureCodeService;
import com.applet.apply.service.WeChantService;
import com.applet.apply.util.*;
import com.applet.apply.entity.*;
import com.applet.apply.util.aliyun.SmsUtil;
import com.applet.apply.util.encryption.DesUtil;
import com.applet.apply.util.encryption.MD5Util;
import com.applet.apply.util.http.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouhuahu on 2018/6/26.
 */
@RestController
@RequestMapping(value = "/api/applet/wechat/")
public class WeChantController {
    private static final Logger logger = LoggerFactory.getLogger(WeChantController.class);
    @Autowired
    private WeChantService weChantService;
    @Autowired
    private FigureCodeService figureCodeService;
    @Autowired
    private AuthCodeService authCodeService;

    /**
     * 授权登录
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "login")
    @CancelAuthentication
    public Object login(@SessionScope("appletInfo") ViewAppletInfo appletInfo, @RequestParam("code") String code,
                        @RequestParam("nickName") String nickName, @RequestParam("avatarUrl") String avatarUrl,
                        @RequestParam("gender") boolean gender) {
        try {
            String openId = WeChatAppletUtil.getOpenId(code, appletInfo.getAppId(), appletInfo.getAppSecret());
            ViewWeChantInfo info = weChantService.selectWeChantInfo(appletInfo.getId(), openId, nickName, avatarUrl, gender);
            if (info != null) {
                if (info.getStatus().intValue() == 0){
                    return AjaxResponse.error("您的账户已经冻结，请联系客服进行处理");
                }
                info.setOpenId(null);
                Map<String, Object> map = new HashMap<>();
                map.put("userInfo", info);
                map.put("isDealer", false);
                if (NullUtil.isNotNullOrEmpty(info.getUserId()) && appletInfo.getUserId().intValue() == info.getUserId().intValue()) {
                    map.put("isDealer", true);
                }
                if (NullUtil.isNotNullOrEmpty(info.getUserId())){
                    map.put("bindStatus", true);
                    return AjaxResponse.success(map);
                } else {
                    map.put("bindStatus", false);
                    return AjaxResponse.msg("0", map);
                }
            }
        } catch (Exception e) {
            logger.info("授权登录出错{}", e);
        }
        return AjaxResponse.error("授权登录失败");
    }


    /**
     * 发送绑定/解绑微信验证码
     *
     * @param weChantInfo
     * @param newMobile
     * @param request
     * @return
     */
//    @RequestMapping(value = "sendBindWxCode")
//    public Object sendBindWxCode(@SessionScope("weChantInfo") WeChantInfo weChantInfo, @RequestParam("newMobile") String newMobile,
//                                 @RequestParam("fCode") String fCode, HttpServletRequest request) {
//        if (NullUtil.isNullOrEmpty(newMobile)) {
//            return AjaxResponse.error("账号不能为空");
//        }
//        if (!RegularUtil.checkMobile(newMobile)) {
//            return AjaxResponse.error("账号格式不正确");
//        }
//        FigureCode figureCode = figureCodeService.selectFigureCode(weChantInfo.getId(), Constants.BIND_MOBILE_FIGURE_CODE);
//        if (figureCode == null) {
//            return AjaxResponse.error("图形码错误");
//        }
//        if (!figureCode.getCode().toLowerCase().equals(fCode)) {
//            return AjaxResponse.error("图形码输入错误");
//        }
//        UserInfo newInfo = weChantService.getUserInfo(newMobile);
//        if (newInfo != null && !newInfo.getStatus()) {
//            return AjaxResponse.error("账号已禁用");
//        } else {
//            newInfo = new UserInfo();
//            newInfo.setMobile(newMobile);
//        }
//        String ip = IpUtil.getIpAddr(request);
//        String smsCode = RandomUtil.getRandomStr(6);
//        String operation = "";
//        String mobile = "";
//        if (NullUtil.isNotNullOrEmpty(weChantInfo.getUserId())) {
//            //修改绑定账号时验证码默认发送给绑定账号
//            UserInfo oldInfo = weChantService.getUserInfo(weChantInfo.getUserId());
//            if (oldInfo == null) {
//                return AjaxResponse.error("Error: user is null");
//            }
//            if (newMobile.equals(oldInfo.getMobile())) {
//                return AjaxResponse.error("手机号码一致！");
//            }
//            int num = authCodeService.getTodaySendCodeCount(oldInfo.getMobile(), EnumUtil.SMSType.BIND_MOBILE.toString());
//            if (num >= Constants.SMS_CODE_AMOUNT.intValue()) {
//                return AjaxResponse.error("今日短信发送次数已达上限，请明日再试");
//            }
//            operation = "修改绑定";
//            mobile = oldInfo.getMobile();
//        } else {
//            int num = authCodeService.getTodaySendCodeCount(newInfo.getMobile(), EnumUtil.SMSType.BIND_MOBILE.toString());
//            if (num >= Constants.SMS_CODE_AMOUNT.intValue()) {
//                return AjaxResponse.error("今日短信发送次数已达上限，请明日再试");
//            }
//            operation = "绑定";
//            mobile = newInfo.getMobile();
//        }
//        SmsTemplate template = authCodeService.selectSmsTemplateByType(EnumUtil.SMSType.BIND_MOBILE.toString());
//        return sendBindWxCode(template, mobile, operation, smsCode);
//    }

    /**
     * 发送绑定微信验证码
     *
     * @param template
     * @param mobile
     * @param operate
     * @param code
     * @return
     */
//    private Object sendBindWxCode(SmsTemplate template, Integer userId, String mobile, String operate, String code) {
//        try {
//            String param = "{\"userName\":\"" + mobile + "\",\"operate\":\"" + operate + "\",\"code\":\"" + code + "\"}";
//            SmsUtil.sendSms(mobile, template.getSingName(), template.getCode(), param);
//            authCodeService.addAuthCode(userId, mobile, EnumUtil.SMSType.BIND_MOBILE.toString(), smsCode, ip);
//            return AjaxResponse.success("发送成功，验证码10分钟内有效");
//        } catch (Exception e) {
//            logger.error("发送验证码出错");
//        }
//        return AjaxResponse.error("发送失败");
//    }

    /**
     * 绑定账号
     *
     * @param weChantInfo
     * @param mobile
     * @param code
     * @return
     */
    @RequestMapping(value = "bindingAccount")
    public Object bindingAccount(@SessionScope("weChantInfo") WeChantInfo weChantInfo,
                                 @RequestParam("mobile") String mobile, @RequestParam("code") String code) {
        try {
            AuthCode authCode = null;
            if (NullUtil.isNotNullOrEmpty(weChantInfo.getUserId())) {
                UserInfo userInfo = weChantService.getUserInfo(weChantInfo.getUserId());
                authCode = authCodeService.selectAuthCodeByMobile(userInfo.getMobile());
            } else {
                authCode = authCodeService.selectAuthCodeByMobile(mobile);
            }
            if (authCode == null) {
                return AjaxResponse.error("验证码已过期");
            }
            if (!code.equals(authCode.getAuthCode())) {
                return AjaxResponse.error("验证码错误");
            }
            weChantService.updateWeChant(weChantInfo, mobile, code);
            return AjaxResponse.success("绑定成功");
        } catch (Exception e) {
            logger.error("微信绑定账号出错{}", e);
        }
        return AjaxResponse.error("绑定失败");
    }


    @RequestMapping(value = "updateUserPass")
    public Object updateUserPass(@SessionScope("weChantInfo") WeChantInfo weChantInfo, String oldPass, String newPass){
        try {
            if (NullUtil.isNullOrEmpty(oldPass) || NullUtil.isNullOrEmpty(newPass)){
                return AjaxResponse.error("参数缺失");
            }
            UserInfo userInfo = weChantService.getUserInfo(weChantInfo.getUserId());
            String cipher = DesUtil.encrypt(oldPass, userInfo.getEncrypted());
            cipher = MD5Util.MD5(cipher);
            if (!cipher.equals(userInfo.getPassword())){
                return AjaxResponse.error("旧密码输入错误");
            }
            cipher = DesUtil.encrypt(newPass, userInfo.getEncrypted());
            cipher = MD5Util.MD5(cipher);
            userInfo.setPassword(cipher);
            weChantService.updateUserInfo(userInfo);
            return AjaxResponse.error("提交成功");
        } catch (Exception e) {
            logger.error("更新用户密码出错{}", e);
            return AjaxResponse.error("提交失败");
        }
    }

    @RequestMapping(value = "sendVerifyCode")
    public Object sendVerifyCode(@SessionScope("weChantInfo") WeChantInfo weChantInfo, String figureCode, HttpServletRequest request){
        String verifyFigureCode = request.getSession().getAttribute(Constants.FIGURE_CODE).toString();
        if (NullUtil.isNullOrEmpty(verifyFigureCode)){
            return AjaxResponse.error("参数错误");
        }
        if (!verifyFigureCode.equals(figureCode)){
            return AjaxResponse.error("图形码输入有误");
        }
        UserInfo userInfo = weChantService.getUserInfo(weChantInfo.getUserId());
        if (userInfo != null && !userInfo.getStatus()) {
            return AjaxResponse.error("账号已禁用");
        }
        int num = authCodeService.getTodaySendCodeCount(userInfo.getMobile(), EnumUtil.SMSType.UPDATE_PASS.toString());
        if (num >= Constants.SMS_CODE_AMOUNT.intValue()) {
            return AjaxResponse.error("今日短信发送次数已达上限，请明日再试");
        }

        String ip = IpUtil.getIpAddr(request);
        String smsCode = RandomUtil.getRandomStr(6);
        String channel = EnumUtil.SMSChannel.ALIYUN.toString();
        SmsTemplate template = authCodeService.selectSmsTemplateByType(EnumUtil.SMSType.UPDATE_PASS.toString(), EnumUtil.SMSChannel.ALIYUN.toString());
        authCodeService.addAuthCode(userInfo.getId(), userInfo.getMobile(), EnumUtil.SMSType.UPDATE_PASS.toString(), smsCode, EnumUtil.SMSChannel.ALIYUN.toString(), ip);
//        return sendBindWxCode(template, userInfo.getMobile(), "修改密码", smsCode);
        return AjaxResponse.error("发送失败");
    }
}
