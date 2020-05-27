package com.applet.apply.controller;

import com.applet.apply.config.annotation.CancelAuth;
import com.applet.apply.config.annotation.SessionScope;
import com.applet.apply.service.RedisService;
import com.applet.common.entity.*;
import com.applet.apply.service.AppletPageService;
import com.applet.common.entity.page.GoodsClassify;
import com.applet.common.util.AjaxResponse;
import com.applet.common.util.NullUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private RedisService redisService;

    /**
     * 小程序基本信息
     * @param appletInfo
     * @return
     */
    @RequestMapping(value = "/queryAppletPageInfo")
    @CancelAuth
    public Object queryAppletPageInfo(@SessionScope("appletInfo") ViewAppletInfo appletInfo, String pageLogo){
        try {
            ViewAppletPageContent content = appletPageService.selectViewAppletPageContent(appletInfo.getId(), pageLogo);
            if (null != content){
                Map map = new HashMap();
                map.put("contentJson", content.getContentJson());
                if (pageLogo.equals("MAIN")){

                }
                return AjaxResponse.success(map);
            }
        } catch (Exception e) {
            log.error("查询小程序页面配置信息出错{}", e);
        }
        return AjaxResponse.error("未找到相关信息");
    }

    /**
     * 加载小程序分类页面信息
     * @param appletInfo
     * @return
     */
    @RequestMapping(value = "loadGoodsClassify")
    @CancelAuth
    public Object loadGoodsClassify(@SessionScope("appletInfo") ViewAppletInfo appletInfo) {
        try {
            GoodsClassify gc = appletPageService.selectGoodsClassify(appletInfo.getId());
            if (null == gc){
                return AjaxResponse.error("未找到相关记录");
            }
            return AjaxResponse.success(gc);
        } catch (Exception e) {
            log.error("加载小程序分类页面信息出错{}", e);
            return AjaxResponse.error("加载失败");
        }
    }
}
