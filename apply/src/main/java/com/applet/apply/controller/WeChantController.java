package com.applet.apply.controller;

import com.applet.apply.config.annotation.CancelAuth;
import com.applet.apply.config.annotation.SessionScope;
import com.applet.apply.service.*;
import com.applet.common.util.*;
import com.applet.common.entity.*;
import com.applet.common.util.enums.SMSEnum;
import com.applet.common.util.wechant.WeChatAppletUtil;
import jodd.datetime.JDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouhuahu on 2018/6/26.
 */
@RestController
@RequestMapping(value = "/api/applet/wechant/")
public class WeChantController {
    private static final Logger logger = LoggerFactory.getLogger(WeChantController.class);
    @Autowired
    private WeChantService weChantService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthCodeService authCodeService;
    @Autowired
    private SmsService smsService;
    @Autowired
    @Lazy
    private UserOrderService userOrderService;
    @Autowired
    private RedisService redisService;

    /**
     * 授权登录
     * @param appletInfo
     * @param loginCode
     * @param nickName
     * @param avatarUrl
     * @param gender
     * @param ipAddress
     * @return
     */
    @PostMapping(value = "login")
    @CancelAuth
    public Object login(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                        @SessionScope(Constants.CLIENT_PUBLIC_IP) String ipAddress,
                        @RequestParam("loginCode") String loginCode, @RequestParam("nickName") String nickName,
                        @RequestParam("avatarUrl") String avatarUrl, @RequestParam("gender") boolean gender,
                        @RequestParam("lon") String lon, @RequestParam("lat") String lat) {
        try {
            String openId = WeChatAppletUtil.getOpenId(loginCode, appletInfo.getAppId(), appletInfo.getAppSecret());
            ViewWeChantInfo weChantInfo = weChantService.selectViewWeChantInfo(appletInfo.getId(), openId, nickName, avatarUrl, gender);
            return getWeChantInfo(appletInfo, weChantInfo, ipAddress, lon, lat);
        } catch (Exception e) {
            logger.info("授权登录出错{}", e);
        }
        return AjaxResponse.error("授权登录失败");
    }

    /**
     * 加载用户登陆信息
     * @param appletInfo
     * @param weChantInfo
     * @param ipAddress
     * @return
     */
    @RequestMapping(value = "loadUserInfo")
    public Object loadUserInfo(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                               @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                               @SessionScope(Constants.CLIENT_PUBLIC_IP) String ipAddress,
                               @RequestParam("lon") String lon, @RequestParam("lat") String lat){
        return getWeChantInfo(appletInfo, weChantInfo, ipAddress, lon, lat);
    }

    private Object getWeChantInfo(ViewAppletInfo appletInfo, ViewWeChantInfo weChantInfo,
                                  String ipAddress, String lon, String lat){
        if (weChantInfo != null) {
            if (weChantInfo.getStatus().intValue() == 0) {
                return AjaxResponse.error("您的账户已经冻结，请联系客服进行处理");
            }
            if (NullUtil.isNotNullOrEmpty(weChantInfo.getUserId()) && NullUtil.isNotNullOrEmpty(ipAddress)
                    && NullUtil.isNotNullOrEmpty(lon) && NullUtil.isNotNullOrEmpty(lat)){
                weChantService.saveUserLoginLog(weChantInfo.getUserId(), ipAddress, lon, lat);
            }
            weChantInfo.setOpenId(null);
            Map<String, Object> map = new HashMap<>();
            map.put("userInfo", weChantInfo);
            map.put("isDealer", false);
            map.put("notice", userService.selectSystemNoticeByPublic());
            map.put("noticeUnreadCount", userService.selectUserRemindRecordByCount(weChantInfo.getUserId(), null));
            map.put("userOrder", userOrderService.countUserOrder(weChantInfo.getUserId()));
            if (NullUtil.isNotNullOrEmpty(weChantInfo.getUserId()) && appletInfo.getUserId().intValue() == weChantInfo.getUserId().intValue()) {
                map.put("isDealer", true);
                // 订单数量统计
                map.put("appletOrder", userOrderService.countAppletOrder(appletInfo.getId()));
            }
            if (NullUtil.isNotNullOrEmpty(weChantInfo.getUserId())) {
                map.put("bindStatus", true);
                return AjaxResponse.success(map);
            } else {
                map.put("bindStatus", false);
                return AjaxResponse.msg("0", map);
            }
        }
        return AjaxResponse.error("登陆失败");
    }

    /**
     * 发送绑定微信验证码
     *
     * @param weChantInfo
     * @param ipAddress
     * @param mobile
     * @return
     */
    @RequestMapping(value = "sendBindAppletCode")
    public Object sendBindAppletCode(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                     @SessionScope(Constants.CLIENT_PUBLIC_IP) String ipAddress,
                                     @RequestParam("mobile") String mobile) {
        try {
            if (NullUtil.isNullOrEmpty(mobile)) {
                return AjaxResponse.error("账号不能为空");
            }
            if (!RegularUtil.checkMobile(mobile)) {
                return AjaxResponse.error("账号格式不正确");
            }
            UserInfo userInfo = userService.getUserInfo(mobile);
            if (null == userInfo) {
                userInfo = new UserInfo();
                userInfo.setMobile(mobile);
                userInfo.setStatus(true);
            }
            if (!userInfo.getStatus()) {
                return AjaxResponse.error("账号已禁用");
            }
            int senNum = authCodeService.getTodaySendCodeCount(userInfo.getMobile(), SMSEnum.type.BIND_APPLET.toString());
            if (senNum >= Constants.SMS_CODE_AMOUNT.intValue()) {
                return AjaxResponse.error("今日短信发送次数已达上限，请明日再试");
            }
            int validityNum = authCodeService.selectVerifyCodeValidityCount(userInfo.getMobile(), SMSEnum.type.BIND_APPLET.toString());
            if (validityNum > 0) {
                return AjaxResponse.error("操作频繁，请稍后再试");
            }
            String channel = SMSEnum.channel.ALIYUN.toString();
            String remark = "绑定小程序";
            if (NullUtil.isNotNullOrEmpty(weChantInfo.getUserId())) {
                remark = "换绑小程序";
                if (NullUtil.isNotNullOrEmpty(userInfo.getId()) && userInfo.getId().intValue() == weChantInfo.getUserId().intValue()){
                    return AjaxResponse.error("已绑定该账户");
                }
            }

            AuthCode authCode = new AuthCode();
            authCode.setUserId(userInfo.getId());
            authCode.setMobile(userInfo.getMobile());
            authCode.setAuthType(SMSEnum.type.BIND_APPLET.toString());
            authCode.setAuthCode(RandomUtil.getRandomStr(6));
            JDateTime time = new JDateTime(new Date());
            authCode.setSendTime(time.convertToDate());
            logger.info("当前时间为： " + time.toString(Constants.DATE_TIME_JODD));
            authCode.setOverTime(time.addMinute(10).convertToDate());
            authCode.setRemark(remark);
            authCode.setChannel(channel);
            authCode.setIpAddress(ipAddress);
            boolean bool = smsService.sendCode(authCode);
            if (bool) {
                authCodeService.addAuthCode(authCode);
                return AjaxResponse.success("发送成功，验证码10分钟内有效");
            }
        } catch (Exception e) {
            logger.error("绑定小程序发送验证码出错{}", e);
        }
        return AjaxResponse.success("发送失败");
    }

    /**
     * 绑定小程序
     * @param appletInfo
     * @param weChantInfo
     * @param code
     * @param mobile
     * @param rmdMobile
     * @return
     */
    @RequestMapping(value = "bindApplet")
    public Object bindApplet(@SessionScope("appletInfo") ViewAppletInfo appletInfo, @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                             String code, String mobile, String rmdMobile) {
        try {
            if (NullUtil.isNullOrEmpty(mobile)) {
                return AjaxResponse.error("手机号不能为空");
            }
            if (!RegularUtil.checkMobile(mobile)) {
                return AjaxResponse.error("手机号格式不正确");
            }
            UserInfo userInfo = userService.getUserInfo(mobile);
            if (null != userInfo && !userInfo.getStatus()) {
                return AjaxResponse.error("账号已禁用");
            }
            UserInfo rmdInfo = null;
            if (null == userInfo && NullUtil.isNotNullOrEmpty(rmdMobile)) {
                if (!RegularUtil.checkMobile(mobile)) {
                    return AjaxResponse.error("推荐号码格式不正确");
                }
                rmdInfo = userService.getUserInfo(rmdMobile);
                if (null == rmdInfo) {
                    return AjaxResponse.error("推荐号码不存在");
                }
                if (!rmdInfo.getStatus()) {
                    return AjaxResponse.error("推荐号码不可用");
                }
            }
            AuthCode authCode = authCodeService.selectAuthCodeByMobile(mobile, SMSEnum.type.BIND_APPLET.toString());
            if (null == authCode) {
                return AjaxResponse.error("验证码已过期，请重新发送");
            }
            if (!authCode.getAuthCode().equals(code)) {
                return AjaxResponse.error("验证码输入不匹配");
            }
            weChantService.updateWeChantUserId(appletInfo, weChantInfo, userInfo, mobile, rmdInfo);
            // 更新redis信息
            ViewWeChantInfo wxInfo = weChantService.selectViewWeChantInfo(weChantInfo.getId(), appletInfo.getId());
            redisService.setValue(wxInfo.getWxCode(), wxInfo);
            return AjaxResponse.success("绑定成功");
        } catch (Exception e) {
            logger.error("绑定手机号出错{}", e);
            return AjaxResponse.error("绑定失败");
        }
    }
}
