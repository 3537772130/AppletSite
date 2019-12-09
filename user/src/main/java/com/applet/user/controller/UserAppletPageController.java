package com.applet.user.controller;

import com.applet.user.config.annotation.SessionScope;
import com.applet.user.entity.*;
import com.applet.user.service.AppletPageService;
import com.applet.user.util.AjaxResponse;
import com.applet.user.util.Constants;
import com.applet.user.util.NullUtil;
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
        ViewAppletVersion version = appletPageService.selectAppletVersionInfo(user.getId(), appletId);
        if (null != version){
            Map map = new HashMap();
            map.put("appletTypeId", version.getTypeId());
            List<AppletPage> list = appletPageService.selectAppletPageList(version.getFileId());
            if (NullUtil.isNotNullOrEmpty(list)){
                map.put("pageList", list);
                return AjaxResponse.success(map);
            }
        }
        return AjaxResponse.error("未找到相关记录");
    }

    /**
     * 加载页面元素
     * @param pageId
     * @return
     */
    @RequestMapping(value = "loadPageElement")
    public Object loadPageElement(Integer appletId, Integer pageId){
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
        AppletPageContent content = appletPageService.selectAppletPageContent(appletId, pageId);
        if (null == content){
            content = appletPageService.selectAppletPageContent(0, pageId);
        }
        Map map = new HashMap();
        map.put("typeList" , mapList);
        if (null != content && NullUtil.isNotNullOrEmpty(content.getContentJson())){
            map.put("contentJson", content.getContentJson());
            return AjaxResponse.success(map);
        } else {
            return AjaxResponse.msg("-1", map);
        }
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
}
