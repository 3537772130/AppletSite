package com.applet.user.controller;

import com.applet.common.util.enums.SMSEnum;
import com.applet.user.config.annotation.CancelAuth;
import com.applet.user.config.annotation.SessionScope;
import com.applet.user.service.AuthCodeService;
import com.applet.user.service.ManagerService;
import com.applet.user.service.UserInfoService;
import com.applet.common.entity.*;
import com.applet.common.util.*;
import com.applet.common.util.encryption.EncryptionUtil;
import jodd.datetime.JDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @program: SpringBootDemo
 * @description: vue登录控制层
 * @author: Mr.ZhouHuaHu
 * @create: 2019-07-05 17:10
 **/
@RestController
@RequestMapping(value = "/api/user/")
public class UserLoginController {
    private static final Logger log = LoggerFactory.getLogger(UserLoginController.class);
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private AuthCodeService authCodeService;


    /**
     * 检查登录状态
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "checkLogin")
    @CancelAuth
    public Object checkLogin(HttpServletRequest request) {
        UserInfo user = (UserInfo) SerializeUtil.unserialize((byte[]) request.getSession().getAttribute(Constants.VUE_USER_INFO));
        if (null == user) {
            return AjaxResponse.error("请先登录");
        }
        return AjaxResponse.success();
    }

    /**
     * 登录校验
     *
     * @param info
     * @param ipAddress
     * @param request
     * @return
     */
    @RequestMapping(value = "doLogin", method = RequestMethod.POST)
    @CancelAuth
    public Object doLogin(UserInfo info, @SessionScope(Constants.CLIENT_PUBLIC_IP) String ipAddress,
                          HttpServletRequest request) {
        try {
            if (NullUtil.isNullOrEmpty(info.getMobile())) {
                return AjaxResponse.error("用户名不能为空");
            }
            if (NullUtil.isNullOrEmpty(info.getPassword())) {
                return AjaxResponse.error("密码不能为空");
            }
            UserInfo userInfo = userInfoService.selectUserInfoByMobile(info.getMobile());
            if (null == userInfo) {
                log.error("用户名不存在：{}", info.getMobile());
                return AjaxResponse.error("用户名或密码不匹配");
            }
            if (!userInfo.getStatus()) {
                return AjaxResponse.error("账户已禁用");
            }
            String cipher = EncryptionUtil.encryptPasswordMD5(info.getPassword(), userInfo.getEncrypted());
            if (!cipher.equals(userInfo.getPassword())) {
                log.error("用户：{}，输入的密码错误：{}", info.getMobile(), info.getPassword());
                return AjaxResponse.error("用户名或密码不匹配");
            }
            request.getSession().setAttribute(Constants.VUE_USER_INFO, SerializeUtil.serialize(userInfo.getUserInfo(userInfo)));
            try {
                userInfoService.saveUserLoginLog(userInfo.getId(), ipAddress);
            } catch (Exception e) {
                log.error("添加登录日志出错{}", e);
            }
            return AjaxResponse.success(userInfo.getUserInfo(userInfo));
        } catch (Exception e) {
            log.error("登录出错：{}", e);
            return AjaxResponse.error("登录失败");
        }
    }

    /**
     * 检测手机号码是否已注册
     *
     * @param mobile
     * @return
     */
    @RequestMapping(value = "checkMobilRegistered")
    @CancelAuth
    public Object checkMobilRegistered(String mobile) {
        UserInfo info = userInfoService.selectUserInfoByMobile(mobile);
        if (null == info) {
            return AjaxResponse.success();
        }
        return AjaxResponse.error("该账户已被注册");
    }

    /**
     * 发送注册验证码
     *
     * @param mobile
     * @param request
     * @return
     */
    @RequestMapping(value = "sendRegisterCode")
    @CancelAuth
    public Object sendRegisterCode(@RequestParam("mobile") String mobile, @RequestParam("figureCode") String figureCode,
                                   @SessionScope(Constants.CLIENT_PUBLIC_IP) String ipAddress,
                                   HttpServletRequest request) {
        try {
            if (NullUtil.isNullOrEmpty(mobile)) {
                return AjaxResponse.error("账号不能为空");
            }
            if (!RegularUtil.checkMobile(mobile)) {
                return AjaxResponse.error("账号格式不正确");
            }
            if (NullUtil.isNullOrEmpty(figureCode)) {
                return AjaxResponse.error("图形码不能为空");
            }
            String sessionFigureCode = request.getSession().getAttribute(Constants.FIGURE_CODE).toString();
            if (!sessionFigureCode.equals(figureCode.toUpperCase())) {
                return AjaxResponse.error("图形码输入错误");
            }
            int senNum = authCodeService.getTodaySendCodeCount(mobile, SMSEnum.type.REGISTER_ACCOUNT.toString());
            if (senNum >= Constants.SMS_CODE_AMOUNT.intValue()) {
                return AjaxResponse.error("今日短信发送次数已达上限，请明日再试");
            }
            int validityNum = authCodeService.selectVerifyCodeValidityCount(mobile, SMSEnum.type.REGISTER_ACCOUNT.toString());
            if (validityNum > 0) {
                return AjaxResponse.error("操作频繁，请稍后再试");
            }
            String channel = SMSEnum.channel.ALIYUN.toString();

            AuthCode authCode = new AuthCode();
            authCode.setMobile(mobile);
            authCode.setAuthType(SMSEnum.type.REGISTER_ACCOUNT.toString());
            authCode.setAuthCode(RandomUtil.getRandomStr(6));
            JDateTime time = new JDateTime(new Date());
            authCode.setSendTime(time.convertToDate());
            authCode.setOverTime(time.addMinute(10).convertToDate());
            authCode.setRemark("新用户注册");
            authCode.setChannel(channel);
            authCode.setIpAddress(ipAddress);
            boolean bool = authCodeService.sendCode(authCode);
            if (bool) {
                authCodeService.addAuthCode(authCode);
                return AjaxResponse.success("发送成功，验证码10分钟内有效");
            }
        } catch (Exception e) {
            log.error("绑定小程序发送验证码出错{}", e);
        }
        return AjaxResponse.success("发送失败");
    }

    /**
     * 注册用户
     * @param mobile
     * @param nickName
     * @param figureCode
     * @param authCode
     * @param password
     * @param extensionCode
     * @return
     */
    @RequestMapping(value = "doRegister")
    @CancelAuth
    public Object doRegister(@RequestParam("mobile") String mobile, @RequestParam("nickName") String nickName,
                             @RequestParam("figureCode") String figureCode, @RequestParam("authCode") String authCode,
                             @RequestParam("password") String password, @RequestParam("extensionCode") String extensionCode,
                             HttpServletRequest request) {
        try {
            if (NullUtil.isNullOrEmpty(mobile)) {
                return AjaxResponse.error("用户名不能为空");
            }
            if (!RegularUtil.checkMobile(mobile)) {
                return AjaxResponse.error("用户名格式不正确");
            }
            if (NullUtil.isNullOrEmpty(nickName)) {
                return AjaxResponse.error("昵称不能为空");
            }
            if (nickName.trim().getBytes().length > 20) {
                return AjaxResponse.error("昵称长度过长");
            }
            if (!RegularUtil.checkName(nickName.trim())) {
                return AjaxResponse.error("昵称不能含有特殊字符");
            }
            if (NullUtil.isNullOrEmpty(password)) {
                return AjaxResponse.error("登录密码不能为空");
            }
            if (password.length() < 6 || password.length() > 20) {
                return AjaxResponse.error("登录密码长度不符");
            }
            String sessionFigureCode = request.getSession().getAttribute(Constants.FIGURE_CODE).toString();
            if (!sessionFigureCode.equals(figureCode.toUpperCase())) {
                return AjaxResponse.error("图形码输入错误");
            }
            request.getSession().removeAttribute(Constants.FIGURE_CODE);
            AuthCode auth = authCodeService.selectAuthCodeByMobile(mobile, SMSEnum.type.REGISTER_ACCOUNT.toString());
            if (null == authCode) {
                return AjaxResponse.error("验证码已过期，请重新发送");
            }
            if (!auth.getAuthCode().equals(authCode)) {
                return AjaxResponse.error("验证码输入不匹配");
            }
            UserInfo info = new UserInfo();
            info.setMobile(mobile);
            info.setNickName(nickName);
            info.setPassword(password);
            info.setGender(false);
            if (NullUtil.isNotNullOrEmpty(extensionCode)) {
                ManagerInfo manager = managerService.selectManagerInfo(extensionCode);
                if (null == manager) {
                    return AjaxResponse.error("无效推荐码");
                } else {
                    info.setRecommendId(manager.getId());
                }
            }
            userInfoService.saveUserInfo(info);
            return AjaxResponse.success("注册成功");
        } catch (Exception e) {
            log.error("注册失败{}", e);
            return AjaxResponse.error("注册失败");
        }
    }


}
