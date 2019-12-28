package com.applet.apply.controller;

import com.applet.apply.config.annotation.CancelAuth;
import com.applet.apply.config.annotation.SessionScope;
import com.applet.apply.entity.AuthCode;
import com.applet.apply.entity.UserInfo;
import com.applet.apply.entity.ViewAppletInfo;
import com.applet.apply.entity.ViewWeChantInfo;
import com.applet.apply.service.*;
import com.applet.common.entity.CheckResult;
import com.applet.common.util.*;
import com.applet.common.util.encryption.DesUtil;
import com.applet.common.util.encryption.MD5Util;
import com.applet.common.util.enums.SMSChannel;
import com.applet.common.util.enums.SMSType;
import com.applet.common.util.http.IpUtil;
import com.applet.common.util.qiniu.QiNiuUtil;
import jodd.datetime.JDateTime;
import lombok.extern.slf4j.Slf4j;
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
 * @author zhouhuahu
 * @date 2018/9/19
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/applet/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthCodeService authCodeService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private WeChantService weChantService;
    @Autowired
    private RedisService redisService;

    /**
     * 检查手机号
     *
     * @param mobile
     * @return
     */
    @RequestMapping(value = "checkMobile")
    @CancelAuth
    public Object checkMobile(@RequestParam("mobile") String mobile) {
        if (NullUtil.isNullOrEmpty(mobile)) {
            return AjaxResponse.error("账号不能为空");
        }
        if (!RegularUtil.checkMobile(mobile)) {
            return AjaxResponse.error("账号格式不正确");
        }
        UserInfo userInfo = userService.getUserInfo(mobile);
        if (null == userInfo) {
            return AjaxResponse.msg("0", "");
        }
        if (!userInfo.getStatus()) {
            return AjaxResponse.error("账号已禁用");
        }
        return AjaxResponse.success();
    }

    /**
     * 发送修改密码验证码
     *
     * @param weChantInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "sendUpdatePassVerifyCode")
    public Object sendUpdatePassVerifyCode(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                           HttpServletRequest request) {
        try {
            UserInfo userInfo = userService.getUserInfo(weChantInfo.getUserId());
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
            String ipAddress = IpUtil.getForIp(request);

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
            log.error("修改密码发送验证码出错{}", e);
        }
        return AjaxResponse.error("发送失败");
    }

    /**
     * 更新用户密码
     *
     * @param weChantInfo
     * @param pass
     * @param code
     * @return
     */
    @RequestMapping(value = "updateUserPassToCode")
    public Object updateUserPassToCode(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                       String pass, String code) {
        try {
            if (NullUtil.isNullOrEmpty(code)) {
                return AjaxResponse.success("请输入验证码");
            }
            if (NullUtil.isNullOrEmpty(pass)) {
                return AjaxResponse.success("请输入密码");
            }
            if (pass.length() < 6 && pass.length() > 20) {
                return AjaxResponse.error("密码长度为6-20位");
            }
            UserInfo userInfo = userService.getUserInfo(weChantInfo.getUserId());
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
            userService.updateUserInfo(userInfo);
            return AjaxResponse.success("修改成功");
        } catch (Exception e) {
            log.error("CODE-更新用户密码出错{}", e);
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
    public Object updateUserPassToPass(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                       String oldPass, String newPass) {
        try {
            if (NullUtil.isNullOrEmpty(oldPass) || NullUtil.isNullOrEmpty(newPass)) {
                return AjaxResponse.error("参数缺失");
            }
            UserInfo userInfo = userService.getUserInfo(weChantInfo.getUserId());
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
            userService.updateUserInfo(userInfo);
            return AjaxResponse.success("修改成功");
        } catch (Exception e) {
            log.error("PASS-更新用户密码出错{}", e);
            return AjaxResponse.error("修改失败");
        }
    }


    /**
     * 上传用户头像
     *
     * @param weChantInfo
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "uploadUserAvatar")
    public Object uploadUserAvatar(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                                   @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                   @RequestParam("avatar") MultipartFile multipartFile) {
        try {
            //校验文件信息
            CheckResult result = CheckFileUtil.checkImageFile(multipartFile);
            if (!result.getBool()) {
                return AjaxResponse.error(result.getMsg());
            }
            UserInfo userInfo = userService.getUserInfo(weChantInfo.getUserId());
            if (null == userInfo) {
                return AjaxResponse.error("没有权限");
            }
            if (!userInfo.getStatus()) {
                return AjaxResponse.error("账号已禁用");
            }
            if (null == multipartFile) {
                return AjaxResponse.error("参数错误");
            }
            if (NullUtil.isNullOrEmpty(userInfo.getAvatarUrl())) {
                userInfo.setAvatarUrl("/api/image/USER-A" + RandomUtil.getTimeStamp());
            }
            QiNiuUtil.uploadFile(multipartFile, userInfo.getAvatarUrl());
            Map map = new HashMap();
            map.put("avatarUrl", userInfo.getAvatarUrl());

            // 更新redis信息
            ViewWeChantInfo wxInfo = weChantService.selectViewWeChantInfo(weChantInfo.getId(), appletInfo.getId());
            redisService.setValue(wxInfo.getWxCode(), wxInfo);
            return AjaxResponse.success(map);
        } catch (Exception e) {
            log.error("小程序上传用户头像出错{}", e);
            return AjaxResponse.error("上传失败");
        }
    }

    /**
     * 更新用户信息
     *
     * @param weChantInfo
     * @param nickName
     * @param gender
     * @param birthday
     * @param email
     * @return
     */
    @RequestMapping(value = "updateUserInfo")
    public Object updateUserInfo(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                                 @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                 String nickName, Integer gender, String birthday, String email) {
        try {
            if (NullUtil.isNullOrEmpty(nickName)) {
                return AjaxResponse.error("请输入昵称");
            }
            if (nickName.getBytes().length > 60) {
                return AjaxResponse.error("昵称长度为1-20个字符");
            }
            if (NullUtil.isNotNullOrEmpty(email) && !RegularUtil.checkEmail(email)) {
                return AjaxResponse.error("邮箱格式不正确");
            }
            UserInfo userInfo = userService.getUserInfo(weChantInfo.getUserId());
            if (null == userInfo) {
                return AjaxResponse.error("没有权限");
            }
            if (!userInfo.getStatus()) {
                return AjaxResponse.error("账号已禁用");
            }
            userInfo.setNickName(nickName);
            userInfo.setGender(gender.intValue() == 1);
            userInfo.setBirthday(birthday);
            userInfo.setEmail(email);
            userService.updateUserInfo(userInfo);

            // 更新redis信息
            ViewWeChantInfo wxInfo = weChantService.selectViewWeChantInfo(weChantInfo.getId(), appletInfo.getId());
            redisService.setValue(wxInfo.getWxCode(), wxInfo);
            return AjaxResponse.success("提交成功");
        } catch (Exception e) {
            log.error("小程序更新用户信息出错{}", e);
            return AjaxResponse.error("提交失败");
        }
    }
}
