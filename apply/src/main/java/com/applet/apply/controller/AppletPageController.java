package com.applet.apply.controller;

import com.applet.apply.config.annotation.CancelAuthentication;
import com.applet.apply.config.annotation.SessionScope;
import com.applet.apply.entity.ViewAppletInfo;
import com.applet.apply.entity.ViewAppletPageContent;
import com.applet.apply.service.AppletPageService;
import com.applet.apply.util.AjaxResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2019/12/9
 * Time: 20:19
 * To change this template use File | Settings | File Templates.
 * Description: 小程序页面控制类
 */
@RestController
@RequestMapping(value = "/api/applet/page/")
public class AppletPageController {
    private final static Logger log = LoggerFactory.getLogger(AppletPageController.class);
    @Autowired
    private AppletPageService appletPageService;

    /**
     * 小程序基本信息
     * @param appletInfo
     * @return
     */
    @RequestMapping(value = "/queryAppletPageInfo")
    @CancelAuthentication
    public Object queryAppletPageInfo(@SessionScope("appletInfo") ViewAppletInfo appletInfo, String pageLogo){
        try {
            ViewAppletPageContent content = appletPageService.selectViewAppletPageContent(appletInfo.getId(), pageLogo);
            if (null != content){
                return AjaxResponse.success(content.getContentJson());
            }
        } catch (Exception e) {
            log.error("查询小程序页面配置信息出错{}", e);
        }
        return AjaxResponse.error("未找到相关信息");
    }
}
