package com.applet.user.controller;

import com.applet.common.util.*;
import com.applet.common.entity.*;
import com.applet.user.config.annotation.SessionScope;
import com.applet.user.service.AppletService;
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
    @Autowired
    private AppletService appletService;

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
        page = freightDeployService.selectFreightDeploysByAppletId(freightDeploy.getAppletId(), user.getId(), page);
        return AjaxResponse.success(page);
    }

    /**
     * 根据ID查询运费配置信息
     * @param user 用户
     * @param id 运费配置对象
     * @return
     */
    @RequestMapping(value = "queryFreightDeployById")
    public Object queryFreightDeployById(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer id) {
        FreightDeploy freightDeploy = freightDeployService.selectFreightDeployById(id, user.getId());
        if (null != freightDeploy){
            return AjaxResponse.success(freightDeploy);
        }
        return AjaxResponse.error("未找到相关记录");
    }

    /**
     * 保存运费配置
     * @param user 用户
     * @param freightDeploy 运费配置
     * @return
     */
    @RequestMapping(value = "saveFreightDeploy", method = RequestMethod.POST)
    public Object saveFreightDeploy(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, FreightDeploy freightDeploy) {
        // 校验参数不能为空
        if (null == freightDeploy) {
            return AjaxResponse.error("参数错误");
        }
        if (NullUtil.isNullOrEmpty(freightDeploy.getAppletId())) {
            return AjaxResponse.error("小程序不能为空");
        }
        if (NullUtil.isNullOrEmpty(freightDeploy.getTitle())){
            return AjaxResponse.error("标题不能为空");
        }
        if (freightDeploy.getTitle().getBytes().length > 60){
            return AjaxResponse.error("标题输入过长");
        }
        if (NullUtil.isNullOrEmpty(freightDeploy.getMinimum())) {
            return AjaxResponse.error("开始距离不能为空");
        }
        if (freightDeploy.getMinimum().intValue() < 0 || freightDeploy.getMinimum().intValue() > 9999999){
            return AjaxResponse.error("开始距离为0-9999999");
        }
        if (NullUtil.isNullOrEmpty(freightDeploy.getMaximum())) {
            return AjaxResponse.error("截止距离不能为空");
        }
        if (freightDeploy.getMaximum().intValue() < 0 || freightDeploy.getMaximum().intValue() > 9999999){
            return AjaxResponse.error("截止距离为0-9999999");
        }
        if (freightDeploy.getMinimum().intValue() >= freightDeploy.getMaximum().intValue()) {
            return AjaxResponse.error("截止距离不能小于等于开始距离");
        }
        if (NullUtil.isNullOrEmpty(freightDeploy.getFreight())) {
            return AjaxResponse.error("快递费不能为空");
        }
        if (freightDeploy.getFreight().intValue() < 0 || freightDeploy.getFreight().intValue() > 9999){
            return AjaxResponse.error("快递费为0-9999");
        }
        if (NullUtil.isNullOrEmpty(freightDeploy.getExemptAmount())){
            return AjaxResponse.error("免额限制不能为空");
        }
        if (freightDeploy.getExemptAmount().doubleValue() < 0 || freightDeploy.getExemptAmount().doubleValue() > 999999){
            return AjaxResponse.error("免额限制为0-999999");
        }
        AppletInfo appletInfo = appletService.selectAppletInfo(freightDeploy.getAppletId(), user.getId());
        if (null == appletInfo){
            return AjaxResponse.error("信息不符");
        }
        freightDeploy.setUserId(user.getId());
        return freightDeployService.updateFreightDeploy(freightDeploy);
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
