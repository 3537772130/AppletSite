package com.applet.user.controller;

import com.applet.user.config.annotation.SessionScope;
import com.applet.user.entity.CheckResult;
import com.applet.user.entity.UserInfo;
import com.applet.user.entity.UserLoginLog;
import com.applet.user.service.UserInfoService;
import com.applet.user.util.*;
import com.applet.user.util.qiniu.QiNiuUtil;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: SpringBootDemo
 * @description: vue用户信息控制层
 * @author: Mr.ZhouHuaHu
 * @create: 2019-07-03 16:03
 **/
@RestController
@RequestMapping(value = "/api/user/")
public class UserInfoController {
    private static final Logger log = LoggerFactory.getLogger(UserInfoController.class);
    @Autowired
    public UserInfoService userInfoService;


    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "exitLogin")
    public Object exitLogin(HttpServletRequest request) {
        try {
            request.getSession().removeAttribute(Constants.VUE_USER_INFO);
            return AjaxResponse.success();
        } catch (Exception e) {
            log.error("退出登录出错{}", e);
            return AjaxResponse.error("退出登录失败");
        }
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @param info
     * @return
     */
    @RequestMapping(value = "updateUserInfo")
    public Object updateUserInfo(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, UserInfo info, HttpServletRequest request) {
        try {
            if (null == info) {
                return AjaxResponse.error("提交信息失败");
            }
            if (NullUtil.isNullOrEmpty(info.getNickName())) {
                return AjaxResponse.error("用户昵称不能为空");
            }
            if (info.getNickName().getBytes().length > 20) {
                return AjaxResponse.error("昵称长度过长");
            }
            if (!RegularUtil.checkName(info.getNickName().trim())) {
                return AjaxResponse.error("昵称不能含有特殊字符");
            }
            if (NullUtil.isNotNullOrEmpty(info.getEmail())) {
                if (!RegularUtil.checkEmail(info.getEmail())) {
                    return AjaxResponse.error("邮箱格式错误");
                }
            }
            info.setId(user.getId());
            info = userInfoService.addOrUpdateUserInfo(info.getUserInfoToUpdate(info));
            if (NullUtil.isNotNullOrEmpty(info.getAvatarUrl())) {
                info.setAvatarUrl("api\\" + info.getAvatarUrl());
            }
            request.getSession().setAttribute(Constants.VUE_USER_INFO, SerializeUtil.serialize(info.getUserInfo(info)));
            return AjaxResponse.success(info.getUserInfo(info));
        } catch (Exception e) {
            log.error("修改用户信息出错{}", e);
            return AjaxResponse.error("修改信息失败");
        }
    }

    /**
     * 修改用户密码(登录状态下)
     *
     * @param userInfo
     * @param oldPass
     * @param newPass
     * @return
     */
    @RequestMapping(value = "updateUserInfoByPassword")
    public Object updateUserInfoByPassword(@SessionScope(Constants.VUE_USER_INFO) UserInfo userInfo, String oldPass, String newPass, HttpServletRequest request) {
        try {
            if (NullUtil.isNullOrEmpty(oldPass)) {
                return AjaxResponse.error("请输入原密码");
            }
            if (oldPass.length() < 6 || oldPass.length() > 20) {
                return AjaxResponse.error("原密码长度不符");
            }
            if (NullUtil.isNullOrEmpty(newPass)) {
                return AjaxResponse.error("请输入新密码");
            }
            if (newPass.length() < 6 || newPass.length() > 20) {
                return AjaxResponse.error("新密码长度不符");
            }
            return userInfoService.updateUserInfoByPassword(userInfo.getId(), oldPass, newPass, request);
        } catch (Exception e) {
            log.error("修改密码出错{}", e);
            return AjaxResponse.error("修改密码失败");
        }
    }

    /**
     * 上传用户头像
     *
     * @param user
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "uploadUserAvatar")
    public Object uploadUserAvatar(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, @RequestParam("avatar") MultipartFile multipartFile,
                                   HttpServletRequest request) {
        try {
            //校验文件信息
            CheckResult result = CheckFileUtil.checkImageFile(multipartFile);
            if (!result.getBool()) {
                return AjaxResponse.error(result.getMsg());
            }
            String fileKey = NullUtil.isNotNullOrEmpty(user.getAvatarUrl()) ?
                    user.getAvatarUrl() : "/api/image/U" + user.getId() + "-A" + RandomUtil.getTimeStamp();
            QiNiuUtil.uploadFile(multipartFile, fileKey);
            userInfoService.updateUserInfoByAvatarUrl(user.getId(), fileKey);
            user.setAvatarUrl(fileKey);
            request.getSession().setAttribute(Constants.VUE_USER_INFO, SerializeUtil.serialize(user.getUserInfo(user)));
            return AjaxResponse.success("上传成功");
        } catch (Exception e) {
            log.error("上传头像出错{}", e);
            return AjaxResponse.success("上传失败");
        }
    }

    /**
     * 查询用户登录记录
     *
     * @param userInfo
     * @param log
     * @param startDate
     * @param endDate
     * @param request
     * @return
     */
    @RequestMapping(value = "selectUserLoginLogToPage")
    public Object selectUserLoginLogToPage(@SessionScope(Constants.VUE_USER_INFO) UserInfo userInfo, UserLoginLog log,
                                           String startDate, String endDate, HttpServletRequest request) {
        Page page = PageUtil.initPage(request);
        log.setUserId(userInfo.getId());
        page = userInfoService.selectUserLoginLogToPage(log, startDate, endDate, page);
        return AjaxResponse.success(page);
    }

    /**
     * 查询用户近期活跃情况
     *
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "selectUserActivity")
    public Object selectUserActivity(@SessionScope(Constants.VUE_USER_INFO) UserInfo userInfo) {
        List<Map> list = userInfoService.selectUserActivity(userInfo.getId(), 6);
        if (NullUtil.isNullOrEmpty(list)) {
            return AjaxResponse.error("为查询到相关记录");
        }
        List<String> monthList = new ArrayList<>();
        List<String> activityList = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            Map map = list.get(i);
            String month = map.get("login_time").toString();
            monthList.add(Constants.MONTH_MAP.get(month.substring(5, 7)));
            activityList.add(map.get("activity").toString());
        }
        Map map = new HashMap();
        map.put("monthJson", new JSONArray(monthList).toString());
        map.put("activityJson", new JSONArray(activityList).toString());
        return AjaxResponse.success(map);
    }
}
