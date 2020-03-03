package com.applet.manage.controller;

import com.applet.manage.config.annotation.CancelAuthentication;
import com.applet.common.entity.*;
import com.applet.manage.service.ManagerService;
import com.applet.manage.service.MenuService;
import com.applet.common.util.AjaxResponse;
import com.applet.common.util.Constants;
import com.applet.common.util.NullUtil;
import com.applet.common.util.SerializeUtil;
import com.applet.common.util.encryption.EncryptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: SpringBootDemo
 * @description: 管理员登录控制层
 * @author: Mr.ZhouHuaHu
 * @create: 2019-07-17 11:03
 **/
@RestController
@RequestMapping(value = "/api/manage/")
public class ManagerLoginController {
    private static final Logger log = LoggerFactory.getLogger(ManagerLoginController.class);
    @Autowired
    private ManagerService managerService;
    @Autowired
    private MenuService menuService;

    /**
     * 登录校验
     *
     * @param userName
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "doLogin")
    @CancelAuthentication
    public Object doLogin(String userName, String password, HttpServletRequest request) {
        try {
            if (NullUtil.isNullOrEmpty(userName)) {
                return AjaxResponse.error("用户名不能为空");
            }
            if (NullUtil.isNullOrEmpty(password)) {
                return AjaxResponse.error("密码不能为空");
            }
            ViewManagerInfo managerInfo = managerService.selectManagerInfoByUserName(userName);
            if (null == managerInfo) {
                log.error("用户名不存在：{}", userName);
                return AjaxResponse.error("用户名或密码不匹配");
            }
            String cipher = EncryptionUtil.encryptPasswordMD5(password, managerInfo.getEncrypted());
            if (!cipher.equals(managerInfo.getPassword())) {
                log.error("用户：{}，输入的密码错误：{}", userName, password);
                return AjaxResponse.error("用户名或密码不匹配");
            }
            if (managerInfo.getStatus().intValue() == 0) {
                return AjaxResponse.error("该账户已禁用");
            }
            ManagerInfo manager = managerService.selectManagerInfoById(managerInfo.getId());
            request.getSession().setAttribute(Constants.WEB_MANAGER_INFO, SerializeUtil.serialize(manager.getManagerInfo(manager)));
            // 格式化菜单
            List<Map> menuList = formatMenu(manager.getRoleId());
            request.getSession().setAttribute(Constants.WEB_MANAGER_MENU_LIST, menuList);
            return AjaxResponse.success(managerInfo.getManagerInfo(managerInfo));
        } catch (Exception e) {
            log.error("登录出错：{}", e);
            return AjaxResponse.error("登录失败");
        }
    }

    /**
     * 格式化菜单
     *
     * @param roleId
     * @return
     */
    public List<Map> formatMenu(Integer roleId) {
        List<Map> mapList1 = new ArrayList<>();
        List<ViewRoleMenuThird> thirdList = menuService.selectMenuListByThird(roleId);
        List<ViewRoleMenuSecond> secondList = menuService.selectMenuListBySecond(roleId);
        List<ViewRoleMenuFirst> firstList = menuService.selectMenuListByFirst(roleId);
        for (ViewRoleMenuSecond record1 : secondList) {
            Map map1 = new HashMap();
            map1.put("index", "M-" + record1.getId());
            map1.put("title", record1.getMenuName());
            map1.put("icon", record1.getMenuIcon());
            map1.put("parentId", record1.getParentId());
            List<Map> mapList2 = new ArrayList<>();
            for (ViewRoleMenuThird record2 : thirdList) {
                if (record2.getParentId().intValue() == record1.getMenuId().intValue()) {
                    Map map2 = new HashMap();
                    map2.put("index", "M-" + record2.getId());
                    map2.put("title", record2.getMenuName());
                    map2.put("logo", record2.getMenuLogo());
                    mapList2.add(map2);
                }
            }
            map1.put("items", mapList2);
            mapList1.add(map1);
        }
        List<Map> mapList2 = new ArrayList<>();
        for (ViewRoleMenuFirst record : firstList) {
            Map map1 = new HashMap();
            map1.put("index", "M-" + record.getId());
            map1.put("title", record.getMenuName());
            List<Map> mapList3 = new ArrayList<>();
            for (Map map2 : mapList1) {
                if (map2.get("parentId").toString().equals(record.getMenuId().toString())) {
                    mapList3.add(map2);
                }
            }
            map1.put("items", mapList3);
            mapList2.add(map1);
        }
        return mapList2;
    }

    /**
     * 检查登录状态
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "checkLogin")
    @CancelAuthentication
    public Object checkLogin(HttpServletRequest request) {
        ManagerInfo manager = (ManagerInfo) SerializeUtil.unserialize((byte[]) request.getSession().getAttribute(Constants.WEB_MANAGER_INFO));
        if (null == manager) {
            return AjaxResponse.error("请先登录");
        }
        List<Map> mapList = (List) request.getSession().getAttribute(Constants.WEB_MANAGER_MENU_LIST);
        return AjaxResponse.success(mapList);
    }

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "exitLogin")
    public Object exitLogin(HttpServletRequest request) {
        try {
            request.getSession().removeAttribute(Constants.WEB_MANAGER_INFO);
            return AjaxResponse.success("退出成功");
        } catch (Exception e) {
            log.error("退出登录出错：{}", e);
            return AjaxResponse.error("退出登录失败");
        }
    }

    /**
     * 检测账号是否已注册
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "checkUserNameRegistered")
    @CancelAuthentication
    public Object checkUserNameRegistered(String userName) {
        ViewManagerInfo info = managerService.selectManagerInfoByUserName(userName);
        if (null == info) {
            return AjaxResponse.success();
        }
        return AjaxResponse.error("该账户已被注册");
    }

}
