package com.applet.manage.controller;

import com.applet.manage.config.annotation.SessionScope;
import com.applet.manage.entity.*;
import com.applet.manage.service.ManagerService;
import com.applet.manage.util.*;
import com.applet.manage.util.encryption.DesUtil;
import com.applet.manage.util.encryption.MD5Util;
import com.applet.manage.util.qiniu.QiNiuUtil;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: SpringBootDemo
 * @description: 管理员信息控制层
 * @author: Mr.ZhouHuaHu
 * @create: 2019-06-14 14:50
 **/
@RestController
@RequestMapping(value = "/api/manage/manager/")
public class ManagerInfoController {
    private static final Logger log = LoggerFactory.getLogger(ManagerInfoController.class);
    @Autowired
    private ManagerService managerService;

    /**
     * 加载管理员查询信息集合
     *
     * @return
     */
    @RequestMapping(value = "loadManagerList")
    public Object loadManagerList() {
        Map map = new HashMap<>();
        List<Map> parentList = managerService.selectManagerInfoByRoleId(3);
        List<Map> roleList = managerService.selectRoleToMap();
        map.put("parentList", parentList);
        map.put("roleList", roleList);
        return AjaxResponse.success(map);
    }

    /**
     * 分页查询管理员信息集合
     *
     * @param info
     * @param managerInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "queryManagerToPage")
    public Object queryManagerToPage(@SessionScope(Constants.WEB_MANAGER_INFO) ManagerInfo info,
                                     ViewManagerInfo managerInfo, HttpServletRequest request) {
        Page page = PageUtil.initPage(request);
        if (NullUtil.isNotNullOrEmpty(info.getParentId()) && info.getParentId() == 3) {
            managerInfo.setParentId(info.getId());
        }
        page = managerService.selectManagerInfoToPage(managerInfo, page);
        return AjaxResponse.success(page);
    }

    /**
     * 获取管理员信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getManagerInfo")
    public Object getManagerInfo(Integer id) {
        Map map = new HashMap<>();
        List<Map> parentList = managerService.selectManagerInfoByRoleId(3);
        List<Map> roleList = managerService.selectRoleToMap();
        map.put("parentList", parentList);
        map.put("roleList", roleList);
        map.put("regions", new JSONArray(Constants.REGION_MAP_TO_NAME).toString());
        if (NullUtil.isNotNullOrEmpty(id) && id.intValue() != 0) {
            ManagerInfo managerInfo = managerService.selectManagerInfoById(id);
            if (null != managerInfo) {
                managerInfo.setPassword(null);
                managerInfo.setEncrypted(null);
                map.put("manager", managerInfo);
                return AjaxResponse.success(map);
            }
        }
        return AjaxResponse.msg("-1", map);
    }

    /**
     * 更新管理员信息
     *
     * @param managerInfo
     * @return
     */
    @RequestMapping(value = "updateManagerInfo")
    public Object updateManagerInfo(@SessionScope(Constants.WEB_MANAGER_INFO) ManagerInfo info, ManagerInfo managerInfo, HttpServletRequest request) {
        if (null == managerInfo) {
            return AjaxResponse.error("未获取到相关信息");
        }
        // 当ID不为空时，是为修改，反之为新增
        if (NullUtil.isNullOrEmpty(managerInfo.getId())) {
            if (NullUtil.isNullOrEmpty(managerInfo.getUserName())) {
                return AjaxResponse.error("请输入账号");
            }
            if (NullUtil.isNullOrEmpty(managerInfo.getPassword())) {
                return AjaxResponse.error("请输入密码");
            }
            // 进行修改权限校验
            ManagerInfo record = managerService.selectManagerInfoById(managerInfo.getId());
            if (info.getRoleId().intValue() != 1 && info.getId().intValue() != record.getParentId()) {
                return AjaxResponse.error("超出了您的权限");
            }
        } else if (NullUtil.isNotNullOrEmpty(managerInfo.getUserName())) {
            return AjaxResponse.error("禁止修改账户");
        }
        if (NullUtil.isNotNullOrEmpty(managerInfo.getPassword())) {
            managerInfo.setEncrypted(RandomUtil.getRandomStr32());
            String cipher = DesUtil.encrypt(managerInfo.getPassword(), managerInfo.getEncrypted());
            cipher = MD5Util.MD5(cipher);
            managerInfo.setPassword(cipher);
        } else {
            managerInfo.setPassword(null);
        }
        if (NullUtil.isNullOrEmpty(managerInfo.getNickName())) {
            return AjaxResponse.error("请输入昵称");
        }
        if (managerInfo.getNickName().getBytes().length > 30) {
            return AjaxResponse.error("昵称长度过长");
        }
        if (NullUtil.isNullOrEmpty(managerInfo.getMobile())) {
            return AjaxResponse.error("请设置手机号码");
        }
        if (!RegularUtil.checkMobile(managerInfo.getMobile())) {
            return AjaxResponse.error("手机号码格式不正确");
        }
        if (info.getRoleId() == 3) {
            managerInfo.setParentId(info.getId());
            managerInfo.setRoleId(3);
        }
        try {
            managerService.updateManagerInfo(managerInfo, info.getId(), request);
            return AjaxResponse.success("提交成功");
        } catch (Exception e) {
            return AjaxResponse.error("提交失败");
        }
    }

    /**
     * 加载管理员信息
     *
     * @param info
     * @return
     */
    @RequestMapping(value = "loadManagerInfo")
    public Object loadManagerInfo(@SessionScope(Constants.WEB_MANAGER_INFO) ManagerInfo info) {
        ViewManagerInfo manager = managerService.selectViewManagerInfoById(info.getId());
        return AjaxResponse.success(manager.getManagerInfo(manager));
    }

    /**
     * 更新管理员基础信息
     *
     * @param manager
     * @param nickName
     * @param mobile
     * @param email
     * @param qqAccount
     * @param weChatAccount
     * @param request
     * @return
     */
    @RequestMapping(value = "updateManagerBase")
    public Object updateManagerBase(@SessionScope(Constants.WEB_MANAGER_INFO) ManagerInfo manager, String nickName, String mobile,
                                    String email, String qqAccount, String weChatAccount, HttpServletRequest request) {
        try {
            if (NullUtil.isNullOrEmpty(nickName)) {
                return AjaxResponse.error("昵称不能为空");
            }
            if (nickName.trim().length() == 0 || nickName.trim().length() > 50) {
                return AjaxResponse.error("昵称为1-20个字符");
            }
            manager.setNickName(nickName.trim());
            if (NullUtil.isNullOrEmpty(mobile)) {
                return AjaxResponse.error("手机号码不能为空");
            }
            if (!RegularUtil.checkMobile(mobile)) {
                return AjaxResponse.error("手机号码格式不正确");
            }
            manager.setMobile(mobile);
            if (NullUtil.isNotNullOrEmpty(email)) {
                if (!RegularUtil.checkEmail(email)) {
                    return AjaxResponse.error("邮箱格式不正确");
                }
            }
            manager.setEmail(email);
            manager.setQqAccount(qqAccount);
            manager.setWeChatAccount(weChatAccount);
            managerService.updateManagerInfo(manager, manager.getId(), request);
            request.getSession().setAttribute(Constants.WEB_MANAGER_INFO, SerializeUtil.serialize(manager.getManagerInfo(manager)));
            return AjaxResponse.success(manager.getManagerInfo(manager));
        } catch (Exception e) {
            log.error("修改管理员基础信息出错{}", e);
            return AjaxResponse.error("提交失败");
        }
    }

    /**
     * 修改管理员登录密码
     *
     * @param info
     * @param oldPass
     * @param newPass
     * @return
     */
    @RequestMapping(value = "updateManagerPassword")
    public Object updateManagerPassword(@SessionScope(Constants.WEB_MANAGER_INFO) ManagerInfo info, String oldPass, String newPass, HttpServletRequest request) {
        ManagerInfo manager = managerService.selectManagerInfoById(info.getId());
        String cipher = DesUtil.encrypt(oldPass, manager.getEncrypted());
        cipher = MD5Util.MD5(cipher);
        if (!cipher.equals(manager.getPassword())) {
            return AjaxResponse.error("原密码输入错误");
        }
        cipher = DesUtil.encrypt(newPass, manager.getEncrypted());
        cipher = MD5Util.MD5(cipher);
        ManagerInfo newInfo = new ManagerInfo();
        newInfo.setId(info.getId());
        newInfo.setPassword(cipher);
        try {
            managerService.updateManagerInfo(newInfo, info.getId(), request);
            return AjaxResponse.success("修改成功");
        } catch (Exception e) {
            return AjaxResponse.error("修改失败");
        }
    }

    /**
     * 修改管理员头像
     *
     * @param manager
     * @param multipartFile
     * @param request
     * @return
     */
    @RequestMapping(value = "uploadManagerAvatar")
    public Object uploadManagerAvatar(@SessionScope(Constants.WEB_MANAGER_INFO) ManagerInfo manager, @RequestParam("avatar") MultipartFile multipartFile,
                                      HttpServletRequest request) {
        try {
            //校验文件信息
            CheckResult result = CheckFileUtil.checkImageFile(multipartFile);
            if (!result.getBool()) {
                return AjaxResponse.error(result.getMsg());
            }
            String fileKey = NullUtil.isNotNullOrEmpty(manager.getAvatarUrl()) ?
                    manager.getAvatarUrl() : "/api/image/M" + manager.getId() + "-A" + RandomUtil.getTimeStamp();
            QiNiuUtil.uploadFile(multipartFile, fileKey);
            if (NullUtil.isNullOrEmpty(manager.getAvatarUrl())) {
                manager.setAvatarUrl(fileKey);
                managerService.updateManagerInfo(manager, manager.getId(), request);
                request.getSession().setAttribute(Constants.WEB_MANAGER_INFO, SerializeUtil.serialize(manager.getManagerInfo(manager)));
            }
            return AjaxResponse.success(manager.getAvatarUrl() + "&token=" + RandomUtil.getRandomStr32());
        } catch (Exception e) {
            log.error("管理员上传头像出错{}", e);
            return AjaxResponse.error("上传失败");
        }
    }


    /**
     * 分页查询角色信息列表
     *
     * @param info
     * @param request
     * @return
     */
    @RequestMapping(value = "queryManagerRoleToPage")
    public Object queryManagerRoleToPage(ManagerRole info, HttpServletRequest request) {
        Page page = PageUtil.initPage(request);
        page = managerService.selectRoleToPage(info, page);
        return AjaxResponse.success(page);
    }

    /**
     * 获取角色信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getManagerRole")
    public Object getManagerRole(Integer id) {
        ManagerRole managerRole = managerService.selectManagerRoleById(id);
        if (null != managerRole) {
            return AjaxResponse.success(managerRole);
        }
        return AjaxResponse.error("未匹配到相关信息");
    }

    /**
     * 更新角色信息
     *
     * @param info
     * @return
     */
    @RequestMapping(value = "updateManagerRole")
    public Object updateManagerRole(ManagerRole info) {
        try {
            if (null == info) {
                return AjaxResponse.error("未获取到相关信息");
            }
            if (NullUtil.isNullOrEmpty(info.getRoleName())) {
                return AjaxResponse.error("请输入角色名称");
            }
            if (info.getRoleName().length() > 30) {
                return AjaxResponse.error("角色名称长度过长");
            }
            if (NullUtil.isNotNullOrEmpty(info.getDescribeStr()) && info.getDescribeStr().length() > 200) {
                return AjaxResponse.error("角色描述长度过长");
            }
            if (NullUtil.isNullOrEmpty(info.getStatus())) {
                info.setStatus(true);
            }
            managerService.updateManagerRole(info);
            return AjaxResponse.success("提交成功");
        } catch (Exception e) {
            log.error("提交管理员角色信息出错{}", e);
            return AjaxResponse.error("提交失败");
        }
    }
}
