package com.applet.apply.controller;

import com.applet.apply.config.annotation.CancelAuth;
import com.applet.apply.config.annotation.SessionScope;
import com.applet.apply.entity.*;
import com.applet.apply.service.GoodsService;
import com.applet.common.util.AjaxResponse;
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
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2019/12/12
 * Time: 11:34
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@RestController
@RequestMapping(value = "/api/applet/goods/")
public class GoodsController {
    private final static Logger log = LoggerFactory.getLogger(GoodsController.class);
    @Autowired
    private GoodsService goodsService;

    /**
     * 加载小程序分类页面信息
     * @param appletInfo
     * @return
     */
    @RequestMapping(value = "loadGoodsClassify")
    @CancelAuth
    public Object loadGoodsClassify(@SessionScope("appletInfo") ViewAppletInfo appletInfo) {
        try {
            List<ViewGoodsType> typeList = goodsService.selectGoodsTypeList(appletInfo.getId());
            List<Integer> typeIdList = new ArrayList<>();
            for (ViewGoodsType type : typeList) {
                typeIdList.add(type.getId());
            }
            List<ViewGoodsInfo> infoList = goodsService.selectGoodsInfoList(appletInfo.getId(), typeIdList);
            Map map = new HashMap();
            map.put("typeList", typeList);
            map.put("infoList", infoList);
            return AjaxResponse.success(map);
        } catch (Exception e) {
            log.error("加载小程序分类页面信息出错{}", e);
            return AjaxResponse.error("加载失败");
        }
    }

    /**
     * 加载商品详情信息
     * @param appletInfo
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "loadGoodsDetails")
    @CancelAuth
    public Object loadGoodsDetails(@SessionScope("appletInfo") ViewAppletInfo appletInfo, Integer goodsId){
        try {
            ViewGoodsInfo info = goodsService.selectGoodsInfo(appletInfo.getId(), goodsId);
            if (null != info){
                if (info.getGoodsStatus().intValue() == 0){
                    return AjaxResponse.error("sorry，该宝贝已经下架咯");
                }
                List<ViewGoodsFile> fileList = goodsService.selectGoodsFileList(info.getId());
                List<ViewGoodsSpecs> specsList = goodsService.selectGoodsSpecsList(info.getId());
                Map map = new HashMap();
                map.put("info", info);
                map.put("fileList", fileList);
                map.put("specsList", specsList);
                return AjaxResponse.success(map);
            }
        } catch (Exception e) {
            log.error("加载商品详情信息出错");
        }
        return AjaxResponse.error("加载信息失败");
    }

}
