package com.applet.user.controller;

import com.applet.user.config.annotation.SessionScope;
import com.applet.user.entity.*;
import com.applet.user.service.AppletPageService;
import com.applet.user.util.AjaxResponse;
import com.applet.user.util.Constants;
import com.applet.user.util.NullUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: AppletSite
 * @description: 管理小程序页面控制类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-12-09 11:11
 **/
@RestController
@RequestMapping(value = "/api/user/applet/page/")
public class UserAppletPageController {
    private final static Logger log = LoggerFactory.getLogger(UserAppletPageController.class);
    @Autowired
    private AppletPageService appletPageService;

    /**
     * 加载页面默认信息
     * @param user
     * @param appletId
     * @return
     */
    @RequestMapping(value = "loadPageDefault")
    public Object loadPageDefault(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer appletId){
        List<ViewAppletPage> list = appletPageService.selectAppletPageList(user.getId(), appletId);
        if (NullUtil.isNotNullOrEmpty(list)){
            Map map = new HashMap();
            map.put("appletTypeId", list.get(0).getTypeId());
            map.put("pageList", list);
            return AjaxResponse.success(map);
        }
        return AjaxResponse.error("未找到相关记录");
    }

    /**
     * 加载页面元素
     * @param pageId
     * @return
     */
    @RequestMapping(value = "loadPageElement")
    public Object loadPageElement(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer appletId, Integer pageId){
        List<AppletPageElementType> list1 = appletPageService.selectElementTypeList(pageId);
        List<AppletPageElement> list2 = appletPageService.selectElementList(pageId);
        List<Map> mapList = new ArrayList<>();
        for (AppletPageElementType type: list1) {
            Map map1 = new HashMap();
            map1.put("id", type.getId());
            map1.put("name", type.getTypeName());
            List<Map> list3 = new ArrayList<>();
            for (AppletPageElement element:list2) {
                if (element.getTypeId() == type.getId()){
                    Map map2 = new HashMap();
                    map2.put("id", element.getId());
                    map2.put("logo", element.getElementLogo());
                    map2.put("name", element.getElementName());
                    list3.add(map2);
                }
            }
            map1.put("list", list3);
            mapList.add(map1);
        }
        String contentJson = null;
        ViewAppletPageContent content = appletPageService.selectAppletPageContent(user.getId(), appletId, pageId);
        if (null == content){
            AppletPageContent content1 = appletPageService.selectAppletPageContent(pageId);
            contentJson = content1.getContentJson();
        } else {
            contentJson = content.getContentJson();
        }
        Map map = new HashMap();
        map.put("typeList" , mapList);
        map.put("contentJson", contentJson);
        return AjaxResponse.success(map);
    }

    /**
     * 查询商品列表
     * @param user
     * @param name
     * @return
     */
    @RequestMapping(value = "queryGoodsInfoList")
    public Object queryGoodsInfoList(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, String name){
        Map map = new HashMap();
        map.put("goodsList", appletPageService.selectGoodsInfoList(user.getId(), name));
        map.put("typeList", appletPageService.selectGoodsTypeList(user.getId(), null));
        return AjaxResponse.success(map);
    }

    /**
     * 查询商品详情列表
     * @param user
     * @param name
     * @return
     */
    @RequestMapping(value = "queryGoodsDetailsList")
    public Object queryGoodsDetailsList(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, String name){
        Map map = new HashMap();
        map.put("goodsList", appletPageService.selectGoodsDetailsList(user.getId(), name));
        map.put("typeList", appletPageService.selectGoodsTypeList(user.getId(), null));
        return AjaxResponse.success(map);
    }

    /**
     * 查询商品折扣列表
     * @param user
     * @param name
     * @return
     */
    @RequestMapping(value = "queryGoodsDiscountList")
    public Object queryGoodsDiscountList(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, String name){
        Map map = new HashMap();
        map.put("goodsList", appletPageService.selectGoodsDiscountList(user.getId(), name));
        return AjaxResponse.success(map);
    }

    /**
     * 查询商品类型列表
     * @param user
     * @param name
     * @return
     */
    @RequestMapping(value = "queryGoodsTypeList")
    public Object queryGoodsTypeList(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, String name){
        Map map = new HashMap();
        map.put("typeList", appletPageService.selectGoodsTypeList(user.getId(), name));
        return AjaxResponse.success(map);
    }

    /**
     * 保存页面配置
     * @param pageId
     * @param json
     * @return
     */
    @RequestMapping(value = "savePageContent")
    public Object savePageContent(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer appletId, Integer pageId, String json){
        try {
            log.info("配置的JSON长度为：" + json.getBytes().length + "个字节");
            if (json.getBytes().length > 65000){
                return AjaxResponse.error("展示的商品过多啦");
            }
            ViewAppletPageContent content = appletPageService.selectAppletPageContent(user.getId(), appletId, pageId);
            AppletPageContent content1 = new AppletPageContent();
            if (null == content){
                ViewAppletPage page = appletPageService.selectAppletPageById(user.getId(), appletId, pageId);
                if (null == page){
                    return AjaxResponse.error("未找到相关记录");
                }
                content1.setAppletId(page.getAppletId());
                content1.setPageId(page.getPageId());
                content1.setFileId(page.getFileId());
            } else {
                content1.setId(content.getId());
                content1.setAppletId(content.getAppletId());
                content1.setPageId(content.getPageId());
                content1.setFileId(content.getAppletFileId());
            }
            content1.setContentJson(json);
            appletPageService.updateAppletPageContent(content1);
            return AjaxResponse.success("保存配置成功");
        } catch (Exception e) {
            log.error("保存页面配置出错{}", e);
            return AjaxResponse.error("保存配置失败");
        }
    }
}
