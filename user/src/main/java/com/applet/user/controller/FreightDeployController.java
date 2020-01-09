package com.applet.user.controller;

import com.applet.common.util.*;
import com.applet.user.config.annotation.SessionScope;
import com.applet.user.entity.AppletInfo;
import com.applet.user.entity.FreightDeploy;
import com.applet.user.entity.UserInfo;
import com.applet.user.service.FreightDeployService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: com.applet.user.controller
 * @description: 小程序运费配置控制类
 * @author: hugh.liu
 * @create: 2020/1/2
 **/
@RestController
@RequestMapping(value = "/api/user/applet/freightDeploy")
public class FreightDeployController {

    private static final Logger log = LoggerFactory.getLogger(FreightDeployController.class);

    @Resource
    private FreightDeployService freightDeployService;

    /**
     * 根据小程序ID查询运费配置信息
     * @param user 用户
     * @param freightDeploy 运费配置对象
     * @param request
     * @return
     */
    @RequestMapping(value = "queryFreightDeploysByAppletId")
    public Object queryFreightDeploysByAppletId(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, FreightDeploy freightDeploy, HttpServletRequest request) {
        Page page = PageUtil.initPage(request);
        page = freightDeployService.selectFreightDeploysByAppletId(freightDeploy.getAppletId(), page);
        return AjaxResponse.success(page);
    }

    /**
     * 根据ID查询运费配置信息
     * @param user 用户
     * @param freightDeploy 运费配置对象
     * @return
     */
    @RequestMapping(value = "queryFreightDeployById")
    public Object queryFreightDeployById(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, FreightDeploy freightDeploy) {
        freightDeploy = freightDeployService.selectFreightDeployById(freightDeploy);
        return AjaxResponse.success(freightDeploy);
    }

    /**
     * 保存运费配置
     * @param user 用户
     * @param freightDeploy 运费配置
     * @return
     */
    @RequestMapping(value = "saveFreightDeploy", method = RequestMethod.POST)
    public Object saveFreightDeploy(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, FreightDeploy freightDeploy) {
        return freightDeployService.saveFreightDeploy(freightDeploy);
    }

    /**
     * 删除运费配置
     * @param user 用户
     * @param freightDeploy 运费配置
     * @return
     */
    @RequestMapping(value = "deleteFreightDeploy", method = RequestMethod.POST)
    public Object deleteFreightDeploy(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, FreightDeploy freightDeploy) {
        return freightDeployService.deleteFreightDeploy(freightDeploy);
    }

}
