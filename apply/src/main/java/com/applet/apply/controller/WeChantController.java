package com.applet.apply.controller;

import com.applet.apply.config.annotation.CancelAuth;
import com.applet.apply.config.annotation.SessionScope;
import com.applet.apply.service.AuthCodeService;
import com.applet.apply.service.SmsService;
import com.applet.apply.service.WeChantService;
import com.applet.common.entity.CheckResult;
import com.applet.common.util.*;
import com.applet.apply.entity.*;
import com.applet.common.util.encryption.DesUtil;
import com.applet.common.util.encryption.MD5Util;
import com.applet.common.util.enums.SMSChannel;
import com.applet.common.util.enums.SMSType;
import com.applet.common.util.http.IpUtil;
import com.applet.common.util.qiniu.QiNiuUtil;
import jodd.datetime.JDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
    private AuthCodeService authCodeService;
    @Autowired
    private SmsService smsService;

    /**
     * 授权登录
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "login")
    @CancelAuth
    public Object login(@SessionScope("appletInfo") ViewAppletInfo appletInfo, @RequestParam("code") String code,
                        @RequestParam("nickName") String nickName, @RequestParam("avatarUrl") String avatarUrl,
                        @RequestParam("gender") boolean gender) {
        try {
            String openId = WeChatAppletUtil.getOpenId(code, appletInfo.getAppId(), appletInfo.getAppSecret());
            ViewWeChantInfo info = weChantService.selectViewWeChantInfo(appletInfo.getId(), openId, nickName, avatarUrl, gender);
            if (info != null) {
                if (info.getStatus().intValue() == 0) {
                    return AjaxResponse.error("您的账户已经冻结，请联系客服进行处理");
                }
                info.setOpenId(null);
                Map<String, Object> map = new HashMap<>();
                map.put("userInfo", info);
                map.put("isDealer", false);
                if (NullUtil.isNotNullOrEmpty(info.getUserId()) && appletInfo.getUserId().intValue() == info.getUserId().intValue()) {
                    map.put("isDealer", true);
                }
                if (NullUtil.isNotNullOrEmpty(info.getUserId())) {
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
     * 检查手机号
     * @param mobile
     * @return
     */
    @RequestMapping(value = "checkMobile")
    @CancelAuth
    public Object checkMobile(@RequestParam("mobile") String mobile){
        if (NullUtil.isNullOrEmpty(mobile)) {
            return AjaxResponse.error("账号不能为空");
        }
        if (!RegularUtil.checkMobile(mobile)) {
            return AjaxResponse.error("账号格式不正确");
        }
        UserInfo userInfo = weChantService.getUserInfo(mobile);
        if (null == userInfo) {
            return AjaxResponse.msg("0", "");
        }
        if (!userInfo.getStatus()) {
            return AjaxResponse.error("账号已禁用");
        }
        return AjaxResponse.success();
    }


    /**
     * 发送绑定微信验证码
     *
     * @param weChantInfo
     * @param mobile
     * @param request
     * @return
     */
    @RequestMapping(value = "sendBindAppletCode")
    public Object sendBindAppletCode(@SessionScope("weChantInfo") WeChantInfo weChantInfo, @RequestParam("mobile") String mobile, HttpServletRequest request) {
        try {
            if (NullUtil.isNullOrEmpty(mobile)) {
                return AjaxResponse.error("账号不能为空");
            }
            if (!RegularUtil.checkMobile(mobile)) {
                return AjaxResponse.error("账号格式不正确");
            }
            UserInfo userInfo = weChantService.getUserInfo(mobile);
            if (null == userInfo) {
                userInfo = new UserInfo();
                userInfo.setMobile(mobile);
                userInfo.setStatus(true);
            }
            if (!userInfo.getStatus()) {
                return AjaxResponse.error("账号已禁用");
            }
            int senNum = authCodeService.getTodaySendCodeCount(userInfo.getMobile(), SMSType.BIND_APPLET.toString());
            if (senNum >= Constants.SMS_CODE_AMOUNT.intValue()) {
                return AjaxResponse.error("今日短信发送次数已达上限，请明日再试");
            }
            int validityNum = authCodeService.selectVerifyCodeValidityCount(userInfo.getMobile(), SMSType.BIND_APPLET.toString());
            if (validityNum > 0) {
                return AjaxResponse.error("操作频繁，请稍后再试");
            }
            String channel = SMSChannel.ALIYUN.toString();
            String remark = "绑定小程序";
            if (NullUtil.isNotNullOrEmpty(weChantInfo.getUserId())) {
                remark = "换绑小程序";
                if (NullUtil.isNotNullOrEmpty(userInfo.getId()) && userInfo.getId().intValue() == weChantInfo.getUserId().intValue()){
                    return AjaxResponse.error("已绑定该账户");
                }
            }
            String ipAddress = IpUtil.getIpAddr(request);

            AuthCode authCode = new AuthCode();
            authCode.setUserId(userInfo.getId());
            authCode.setMobile(userInfo.getMobile());
            authCode.setAuthType(SMSType.BIND_APPLET.toString());
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
    public Object bindApplet(@SessionScope("appletInfo") ViewAppletInfo appletInfo, @SessionScope("weChantInfo") WeChantInfo weChantInfo,
                             String code, String mobile, String rmdMobile) {
        try {
            if (NullUtil.isNullOrEmpty(mobile)) {
                return AjaxResponse.error("手机号不能为空");
            }
            if (!RegularUtil.checkMobile(mobile)) {
                return AjaxResponse.error("手机号格式不正确");
            }
            UserInfo userInfo = weChantService.getUserInfo(mobile);
            if (null != userInfo && !userInfo.getStatus()) {
                return AjaxResponse.error("账号已禁用");
            }
            UserInfo rmdInfo = null;
            if (null == userInfo && NullUtil.isNotNullOrEmpty(rmdMobile)) {
                if (!RegularUtil.checkMobile(mobile)) {
                    return AjaxResponse.error("推荐号码格式不正确");
                }
                rmdInfo = weChantService.getUserInfo(rmdMobile);
                if (null == rmdInfo) {
                    return AjaxResponse.error("推荐号码不存在");
                }
                if (!rmdInfo.getStatus()) {
                    return AjaxResponse.error("推荐号码不可用");
                }
            }
            AuthCode authCode = authCodeService.selectAuthCodeByMobile(mobile, SMSType.BIND_APPLET.toString());
            if (null == authCode) {
                return AjaxResponse.error("验证码已过期，请重新发送");
            }
            if (!authCode.getAuthCode().equals(code)) {
                return AjaxResponse.error("验证码输入不匹配");
            }
            ViewWeChantInfo wxInfo = weChantService.selectViewWeChantInfo(weChantInfo.getId(), appletInfo.getId());
            weChantService.updateWeChantUserId(appletInfo, wxInfo, userInfo, mobile, rmdInfo);
            return AjaxResponse.success("绑定成功");
        } catch (Exception e) {
            logger.error("绑定手机号出错{}", e);
            return AjaxResponse.error("绑定失败");
        }
    }


    /**
     * 发送修改密码验证码
     *
     * @param weChantInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "sendUpdatePassVerifyCode")
    public Object sendUpdatePassVerifyCode(@SessionScope("weChantInfo") WeChantInfo weChantInfo, HttpServletRequest request) {
        try {
            UserInfo userInfo = weChantService.getUserInfo(weChantInfo.getUserId());
            if (null == userInfo) {
                return AjaxResponse.error("参数错误");
            }
            if (!userInfo.getStatus()) {
                return AjaxResponse.error("账号已禁用");
            }
            int senNum = authCodeService.getTodaySendCodeCount(userInfo.getMobile(), SMSType.UPDATE_PASS.toString());
            if (senNum >= Constants.SMS_CODE_AMOUNT.intValue()) {
                return AjaxResponse.error("今日短信发送次数已达上限，请明日再试");
            }
            int validityNum = authCodeService.selectVerifyCodeValidityCount(userInfo.getMobile(), SMSType.UPDATE_PASS.toString());
            if (validityNum > 0) {
                return AjaxResponse.error("操作频繁，请稍后再试");
            }
            String channel = SMSChannel.ALIYUN.toString();
            String remark = "修改密码";
            String ipAddress = IpUtil.getIpAddr(request);

            AuthCode authCode = new AuthCode();
            authCode.setUserId(userInfo.getId());
            authCode.setMobile(userInfo.getMobile());
            authCode.setAuthType(SMSType.UPDATE_PASS.toString());
            authCode.setAuthCode(RandomUtil.getRandomStr(6));
            JDateTime time = new JDateTime(new Date());
            authCode.setSendTime(time.convertToDate());
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
            logger.error("修改密码发送验证码出错{}", e);
        }
        return AjaxResponse.error("发送失败");
    }

    /**
     * 更新用户密码
     * @param weChantInfo
     * @param pass
     * @param code
     * @return
     */
    @RequestMapping(value = "updateUserPassToCode")
    public Object updateUserPassToCode(@SessionScope("weChantInfo") WeChantInfo weChantInfo, String pass, String code){
        try {
            if (NullUtil.isNullOrEmpty(code)){
                return AjaxResponse.success("请输入验证码");
            }
            if (NullUtil.isNullOrEmpty(pass)){
                return AjaxResponse.success("请输入密码");
            }
            if (pass.length() < 6 && pass.length() > 20){
                return AjaxResponse.error("密码长度为6-20位");
            }
            UserInfo userInfo = weChantService.getUserInfo(weChantInfo.getUserId());
            if (null == userInfo) {
                return AjaxResponse.error("参数错误");
            }
            if (!userInfo.getStatus()) {
                return AjaxResponse.error("账号已禁用");
            }
            AuthCode authCode = authCodeService.selectAuthCodeByMobile(userInfo.getMobile(), SMSType.UPDATE_PASS.toString());
            if (null == authCode) {
                return AjaxResponse.error("验证码已过期，请重新发送");
            }
            if (!authCode.getAuthCode().equals(code)) {
                return AjaxResponse.error("验证码输入不匹配");
            }
            String cipher = DesUtil.encrypt(pass, userInfo.getEncrypted());
            cipher = MD5Util.MD5(cipher);
            userInfo.setPassword(cipher);
            weChantService.updateUserInfo(userInfo);
            return AjaxResponse.success("修改成功");
        } catch (Exception e) {
            logger.error("CODE-更新用户密码出错{}", e);
            return AjaxResponse.error("修改失败");
        }
    }


    /**
     * 通过旧密码更新密码
     *
     * @param weChantInfo
     * @param oldPass
     * @param newPass
     * @return
     */
    @RequestMapping(value = "updateUserPassToPass")
    public Object updateUserPassToPass(@SessionScope("weChantInfo") WeChantInfo weChantInfo, String oldPass, String newPass) {
        try {
            if (NullUtil.isNullOrEmpty(oldPass) || NullUtil.isNullOrEmpty(newPass)) {
                return AjaxResponse.error("参数缺失");
            }
            UserInfo userInfo = weChantService.getUserInfo(weChantInfo.getUserId());
            if (null == userInfo) {
                return AjaxResponse.error("参数错误");
            }
            if (!userInfo.getStatus()) {
                return AjaxResponse.error("账号已禁用");
            }
            String cipher = DesUtil.encrypt(oldPass, userInfo.getEncrypted());
            cipher = MD5Util.MD5(cipher);
            if (!cipher.equals(userInfo.getPassword())) {
                return AjaxResponse.error("修改失败，旧密码不匹配");
            }
            cipher = DesUtil.encrypt(newPass, userInfo.getEncrypted());
            cipher = MD5Util.MD5(cipher);
            userInfo.setPassword(cipher);
            weChantService.updateUserInfo(userInfo);
            return AjaxResponse.success("修改成功");
        } catch (Exception e) {
            logger.error("PASS-更新用户密码出错{}", e);
            return AjaxResponse.error("修改失败");
        }
    }

    /**
     * 上传用户头像
     * @param weChantInfo
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "uploadUserAvatar")
    public Object uploadUserAvatar(@SessionScope("weChantInfo") WeChantInfo weChantInfo, @RequestParam("avatar") MultipartFile multipartFile){
        try {
            //校验文件信息
            CheckResult result = CheckFileUtil.checkImageFile(multipartFile);
            if (!result.getBool()) {
                return AjaxResponse.error(result.getMsg());
            }
            UserInfo userInfo = weChantService.getUserInfo(weChantInfo.getUserId());
            if (null == userInfo){
                return AjaxResponse.error("没有权限");
            }
            if (!userInfo.getStatus()) {
                return AjaxResponse.error("账号已禁用");
            }
            if (null == multipartFile){
                return AjaxResponse.error("参数错误");
            }
            if (NullUtil.isNullOrEmpty(userInfo.getAvatarUrl())){
                userInfo.setAvatarUrl("/api/image/USER-A" + RandomUtil.getTimeStamp());
            }
            QiNiuUtil.uploadFile(multipartFile, userInfo.getAvatarUrl());
            Map map = new HashMap();
            map.put("avatarUrl", userInfo.getAvatarUrl());
            return AjaxResponse.success(map);
        } catch (Exception e) {
            logger.error("小程序上传用户头像出错{}", e);
            return AjaxResponse.error("上传失败");
        }
    }

    /**
     * 更新用户信息
     * @param weChantInfo
     * @param nickName
     * @param gender
     * @param birthday
     * @param email
     * @return
     */
    @RequestMapping(value = "updateUserInfo")
    public Object updateUserInfo(@SessionScope("weChantInfo") WeChantInfo weChantInfo, String nickName, Integer gender, String birthday, String email){
        try {
            if (NullUtil.isNullOrEmpty(nickName)){
                return AjaxResponse.error("请输入昵称");
            }
            if (nickName.getBytes().length > 60){
                return AjaxResponse.error("昵称长度为1-20个字符");
            }
            if (NullUtil.isNotNullOrEmpty(email) && !RegularUtil.checkEmail(email)){
                return AjaxResponse.error("邮箱格式不正确");
            }
            UserInfo userInfo = weChantService.getUserInfo(weChantInfo.getUserId());
            if (null == userInfo){
                return AjaxResponse.error("没有权限");
            }
            if (!userInfo.getStatus()) {
                return AjaxResponse.error("账号已禁用");
            }
            userInfo.setNickName(nickName);
            userInfo.setGender(gender.intValue() == 1);
            userInfo.setBirthday(birthday);
            userInfo.setEmail(email);
            weChantService.updateUserInfo(userInfo);
            return AjaxResponse.success("提交成功");
        } catch (Exception e) {
            logger.error("小程序更新用户信息出错{}", e);
            return AjaxResponse.error("提交失败");
        }
    }
}
