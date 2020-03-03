package com.applet.apply.controller;

import com.applet.apply.config.annotation.CancelAuth;
import com.applet.apply.config.annotation.SessionScope;
import com.applet.common.entity.*;
import com.applet.apply.service.GoodsService;
import com.applet.apply.service.UserCouponService;
import com.applet.common.util.AjaxResponse;
import com.applet.common.util.NullUtil;
import com.applet.common.util.Page;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping(value = "/api/applet/goods/")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserCouponService userCouponService;

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
            List<ViewCouponInfo> couponList = userCouponService.selectCouponList(appletInfo.getId());
            Map map = new HashMap();
            map.put("typeList", typeList);
            map.put("infoList", infoList);
            map.put("couponList", couponList);
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
        return loadGoodsDetails(appletInfo, null, goodsId);
    }

    /**
     * 加载商品详情信息
     * @param appletInfo
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "loadUserGoodsDetails")
    public Object loadUserGoodsDetails(@SessionScope("appletInfo") ViewAppletInfo appletInfo, @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer goodsId){
        return loadGoodsDetails(appletInfo, weChantInfo, goodsId);
    }

    public Object loadGoodsDetails(ViewAppletInfo appletInfo, ViewWeChantInfo weChantInfo, Integer goodsId){
        try {
            ViewGoodsInfo info = goodsService.selectGoodsInfo(appletInfo.getId(), goodsId);
            if (null != info){
                if (info.getGoodsStatus().intValue() == 0){
                    return AjaxResponse.error("sorry，该宝贝已经下架咯");
                }
                Map map = new HashMap();
                map.put("info", info);
                // 商品文件集合
                map.put("fileList", goodsService.selectGoodsFileList(info.getId()));
                // 商品规格集合
                map.put("specsList", goodsService.selectGoodsSpecsList(info.getId()));
                // 小程序优惠券集合
                map.put("couponList", userCouponService.selectCouponList(appletInfo.getId()));
                // 小程序推荐商品集合
                map.put("recommendGoodsList", goodsService.selectGoodsSellCountList(info.getId(), appletInfo.getId(), appletInfo.getUserId()));
                return AjaxResponse.success(map);
            }
        } catch (Exception e) {
            log.error("加载商品详情信息出错{}", e);
        }
        return AjaxResponse.error("加载信息失败");
    }

    /**
     * 搜索商品
     * @param appletInfo
     * @param goodsName
     * @return
     */
    @RequestMapping(value = "queryGoodsName")
    @CancelAuth
    public Object queryGoodsName(@SessionScope("appletInfo") ViewAppletInfo appletInfo, String goodsName){
        if (NullUtil.isNullOrEmpty(goodsName)){
            return AjaxResponse.error("");
        }
        Page page = new Page(1, 10);
        List<ViewGoodsInfo> list = goodsService.selectGoodsList(appletInfo.getId(), goodsName, page);
        if (NullUtil.isNullOrEmpty(list)) {
            return AjaxResponse.error("");
        }
        return AjaxResponse.success(list);
    }

}
