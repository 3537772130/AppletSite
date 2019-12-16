package com.applet.apply.controller;

import com.applet.apply.config.annotation.CancelAuthentication;
import com.applet.apply.config.annotation.SessionScope;
import com.applet.apply.service.AuthCodeService;
import com.applet.apply.service.FigureCodeService;
import com.applet.apply.service.WeChantService;
import com.applet.apply.util.*;
import com.applet.apply.entity.*;
import com.applet.apply.util.aliyun.SmsUtil;
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
@RequestMapping(value = "/api/applet/")
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
     * @param code
     * @return
     */
    @RequestMapping(value = "/login")
    @CancelAuthentication
    public Object login(@SessionScope("appletInfo") ViewAppletInfo appletInfo, @RequestParam("code") String code, @RequestParam("nickName") String nickName){
        try {
            String openId = WeChatAppletUtil.getOpenId(code, appletInfo.getAppId(), appletInfo.getAppSecret());
            WeChantApplet info = weChantService.selectWeChantApplet(appletInfo.getId(), openId, nickName);
            if (info != null){
                if (NullUtil.isNullOrEmpty(info.getNickName()) || !nickName.equals(info.getNickName())){
                    weChantService.updateNickName(info.getId(), nickName);
                }
                Map<String, Object> map = new HashMap<>();
                map.put("wxLogo", info.getWxLogo());
                map.put("isDealer", false);
                if (NullUtil.isNotNullOrEmpty(info.getUserId())){
                    if (appletInfo.getUserId().intValue() == info.getUserId().intValue()){
                        map.put("isDealer", true);
                    }
                    UserInfo user = weChantService.getUserInfo(info.getUserId());
                    if (user != null){
                        if (user.getStatus()){
                            map.put("userInfo", user.getUserInfo());
                        } else {
                            //自动解绑封禁账号
                            weChantService.updateWeChant(info);
                        }
                    }
                }
                return AjaxResponse.success(map);
            }
        } catch (Exception e) {
            logger.info("授权登录出错{}", e);
        }
        return AjaxResponse.error("授权登录失败");
    }


    /**
     * 加载个人中心
     * @param appletInfo
     * @param info
     * @return
     */
    @RequestMapping(value = "/loadPersonalCenter")
    public Object loadPersonalCenter(@SessionScope("appletInfo") ViewAppletInfo appletInfo, @SessionScope("weChantApplet") WeChantApplet info){
        Map<String, Object> map = new HashMap<>();
        map.put("mobile", null);//当前绑定账号
        if (!NullUtil.isNotNullOrEmpty(info.getUserId())){
            UserInfo userInfo = weChantService.getUserInfo(info.getUserId());
            if (userInfo != null){
                map.put("mobile", userInfo.getMobile());
            }
        }
        return AjaxResponse.success(map);
    }

    /**
     * 发送绑定/解绑微信验证码
     * @param weChantApplet
     * @param newMobile
     * @param request
     * @return
     */
    @RequestMapping(value = "/sendBindWxCode")
    public Object sendBindWxCode(@SessionScope("weChantApplet") WeChantApplet weChantApplet, @RequestParam("newMobile") String newMobile,
                                 @RequestParam("fCode") String fCode, HttpServletRequest request){
        if (NullUtil.isNullOrEmpty(newMobile)){
            return AjaxResponse.error("账号不能为空");
        }
        if (!RegularUtil.checkMobile(newMobile)){
            return AjaxResponse.error("账号格式不正确");
        }
        FigureCode figureCode = figureCodeService.selectFigureCode(weChantApplet.getId(), Constants.BIND_MOBILE_FIGURE_CODE);
        if (figureCode == null){
            return AjaxResponse.error("图形码错误");
        }
        if (!figureCode.getCode().toLowerCase().equals(fCode)){
            return AjaxResponse.error("图形码输入错误");
        }
        UserInfo newInfo = weChantService.getUserInfo(newMobile);
        if (newInfo != null && !newInfo.getStatus()){
            return AjaxResponse.error("账号已禁用");
        } else {
            newInfo = new UserInfo();
            newInfo.setMobile(newMobile);
        }
        String ip = IpUtil.getIpAddr(request);
        String smsCode = RandomUtil.getRandomStr(6);
        String operation = "";
        String mobile = "";
        if (NullUtil.isNotNullOrEmpty(weChantApplet.getUserId())){
            //修改绑定账号时验证码默认发送给绑定账号
            UserInfo oldInfo = weChantService.getUserInfo(weChantApplet.getUserId());
            if (oldInfo == null){
                return AjaxResponse.error("Error: user is null");
            }
            if (newMobile.equals(oldInfo.getMobile())){
                return AjaxResponse.error("手机号码一致！");
            }
            int num = authCodeService.getTodaySendCodeCount(oldInfo.getMobile(), Constants.BIND_MOBILE_TO_UPDATE);
            if (num >= Constants.SMS_CODE_AMOUNT.intValue()){
                return AjaxResponse.error("今日短信发送次数已达上限，请明日再试");
            }
            authCodeService.addAuthCode(oldInfo.getId(), oldInfo.getMobile(), Constants.BIND_MOBILE_TO_UPDATE, smsCode, ip);
            operation = "修改绑定";
            mobile = oldInfo.getMobile();
        } else {
            int num = authCodeService.getTodaySendCodeCount(newInfo.getMobile(), Constants.BIND_MOBILE_TO_ADD);
            if (num >= Constants.SMS_CODE_AMOUNT.intValue()){
                return AjaxResponse.error("今日短信发送次数已达上限，请明日再试");
            }
            authCodeService.addAuthCode(newInfo.getId(), newInfo.getMobile(), Constants.BIND_MOBILE_TO_ADD, smsCode, ip);
            operation = "绑定";
            mobile = newInfo.getMobile();
        }
        SmsTemplate template = authCodeService.selectSmsTemplateByType(Constants.BIND_MOBILE_OPERATION);
        return sendBindWxCode(template, mobile, operation, smsCode);
    }

    /**
     * 发送绑定微信验证码
     * @param template
     * @param mobile
     * @param operate
     * @param code
     * @return
     */
    private Object sendBindWxCode(SmsTemplate template, String mobile, String operate, String code){
        try {
            String param = "{\"userName\":\"" + mobile + "\",\"operate\":\"" + operate + "\",\"code\":\"" + code + "\"}";
            SmsUtil.sendSms(mobile, template.getSingName(), template.getCode(), param);
            return AjaxResponse.success("发送成功，验证码10分钟内有效");
        } catch (Exception e) {
            logger.error("发送验证码出错");
        }
        return AjaxResponse.error("发送失败");
    }

    /**
     * 绑定账号
     * @param weChantApplet
     * @param mobile
     * @param code
     * @return
     */
    @RequestMapping(value = "/bindingAccount")
    public Object bindingAccount(@SessionScope("weChantApplet") WeChantApplet weChantApplet,
                                 @RequestParam("mobile") String mobile, @RequestParam("code") String code){
        try {
            AuthCode authCode = null;
            if (NullUtil.isNotNullOrEmpty(weChantApplet.getUserId())){
                UserInfo userInfo = weChantService.getUserInfo(weChantApplet.getUserId());
                authCode = authCodeService.selectAuthCodeByMobile(userInfo.getMobile());
            } else {
                authCode = authCodeService.selectAuthCodeByMobile(mobile);
            }
            if (authCode == null){
                return AjaxResponse.error("验证码已过期");
            }
            if (!code.equals(authCode.getAuthCode())){
                return AjaxResponse.error("验证码错误");
            }
            weChantService.updateWeChant(weChantApplet, mobile, code);
            return AjaxResponse.success("绑定成功");
        } catch (Exception e) {
            logger.error("微信绑定账号出错{}", e);
        }
        return AjaxResponse.error("绑定失败");
    }

}
