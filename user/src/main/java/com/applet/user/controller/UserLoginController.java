package com.applet.user.controller;

import com.applet.user.config.annotation.CancelAuthentication;
import com.applet.user.entity.ManagerInfo;
import com.applet.user.entity.UserInfo;
import com.applet.user.service.ManagerService;
import com.applet.user.service.UserInfoService;
import com.applet.common.util.*;
import com.applet.common.util.encryption.EncryptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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


    /**
     * 检查登录状态
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "user/checkLogin")
    @CancelAuthentication
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
     * @param request
     * @return
     */
    @RequestMapping(value = "doLogin", method = RequestMethod.POST)
    @CancelAuthentication
    public Object doLogin(UserInfo info, HttpServletRequest request) {
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
                userInfoService.saveUserLoginLog(userInfo.getId(), request);
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
     * @param mobile
     * @return
     */
    @RequestMapping(value = "checkMobilRegistered")
    @CancelAuthentication
    public Object checkMobilRegistered(String mobile) {
        UserInfo info = userInfoService.selectUserInfoByMobile(mobile);
        if (null == info) {
            return AjaxResponse.success();
        }
        return AjaxResponse.error("该账户已被注册");
    }

    /**
     * 注册用户
     *
     * @param info
     * @return
     */
    @RequestMapping(value = "doRegister")
    @CancelAuthentication
    public Object doRegister(UserInfo info) {
        try {
            if (null == info) {
                return AjaxResponse.error("未获取到信息");
            }
            if (NullUtil.isNullOrEmpty(info.getMobile())) {
                return AjaxResponse.error("用户名不能为空");
            }
            if (!RegularUtil.checkMobile(info.getMobile())) {
                return AjaxResponse.error("用户名格式不正确");
            }
            if (NullUtil.isNullOrEmpty(info.getNickName())) {
                return AjaxResponse.error("昵称不能为空");
            }
            if (info.getNickName().trim().getBytes().length > 20) {
                return AjaxResponse.error("昵称长度过长");
            }
            if (!RegularUtil.checkName(info.getNickName().trim())) {
                return AjaxResponse.error("昵称不能含有特殊字符");
            }
            if (NullUtil.isNullOrEmpty(info.getGender())) {
                return AjaxResponse.error("请选择性别");
            }
            if (NullUtil.isNullOrEmpty(info.getPassword())) {
                return AjaxResponse.error("登录密码不能为空");
            }
            if (info.getPassword().length() < 6 || info.getPassword().length() > 20) {
                return AjaxResponse.error("登录密码长度不符");
            }
            if (NullUtil.isNotNullOrEmpty(info.getExtensionCode())) {
                ManagerInfo manager = managerService.selectManagerInfo(info.getExtensionCode());
                if (null == manager) {
                    return AjaxResponse.error("无效推荐码");
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
