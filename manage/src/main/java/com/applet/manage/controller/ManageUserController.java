package com.applet.manage.controller;

import com.applet.manage.service.ManagerService;
import com.applet.manage.service.UserInfoService;
import com.applet.common.util.AjaxResponse;
import com.applet.common.util.NullUtil;
import com.applet.common.util.Page;
import com.applet.common.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @program: SpringBootDemo
 * @description: 用户管理控制层
 * @author: Mr.ZhouHuaHu
 * @create: 2019-09-05 16:13
 **/
@RestController
@RequestMapping(value = "/api/manage/user/")
public class ManageUserController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private ManagerService managerService;

    /**
     * 查询用户列表
     *
     * @param mobile
     * @param nickName
     * @param startDate
     * @param endDate
     * @param request
     * @return
     */
    @RequestMapping(value = "queryUserToPage")
    public Object queryUserToPage(String mobile, String nickName, String startDate, String endDate, String status, HttpServletRequest request) {
        Page page = PageUtil.initPage(request);
        Boolean bool = NullUtil.isNotNullOrEmpty(status) ? status.equals("1") : null;
        page = userInfoService.selectUserToPage(mobile, nickName, startDate, endDate, bool, page);
        return AjaxResponse.success(page);
    }

    /**
     * 加载商户列表
     *
     * @return
     */
    @RequestMapping(value = "loadMerchantList")
    public Object loadMerchantList() {
        List<Map> list = managerService.selectExtensionByMap();
        return AjaxResponse.success(list);
    }

    /**
     * 查询商户列表
     *
     * @param mobile
     * @param nickName
     * @param extensionCode
     * @param startDate
     * @param endDate
     * @param request
     * @return
     */
    @RequestMapping(value = "queryMerchantToPage")
    public Object queryMerchantToPage(String mobile, String nickName, String extensionCode, String startDate, String endDate, String status, HttpServletRequest request) {
        Page page = PageUtil.initPage(request);
        Boolean bool = NullUtil.isNotNullOrEmpty(status) ? status.equals("1") : null;
        page = userInfoService.selectMerchantToPage(mobile, nickName, extensionCode, startDate, endDate, bool, page);
        return AjaxResponse.success(page);
    }
}
