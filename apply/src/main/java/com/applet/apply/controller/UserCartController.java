package com.applet.apply.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.applet.apply.config.annotation.SessionScope;
import com.applet.common.entity.*;
import com.applet.apply.service.UserCartService;
import com.applet.apply.service.UserCouponService;
import com.applet.apply.service.UserService;
import com.applet.common.util.AjaxResponse;
import com.applet.common.util.NullUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2019/12/31
 * Time: 16:53
 * To change this template use File | Settings | File Templates.
 * Description: 用户购物车管理层
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/applet/user/cart/")
public class UserCartController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserCouponService userCouponService;
    @Autowired
    private UserCartService userCartService;
//    @Autowired
//    private FreightDeployService

    /**
     * 加入购物车
     *
     * @param weChantInfo
     * @param cart
     * @return
     */
    @RequestMapping(value = "saveUserCartInfo")
    public Object saveUserCartInfo(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, @Param("cart") UserCart cart) {
        try {
            if (null == cart) {
                return AjaxResponse.error("参数错误");
            }
            cart.setId(null);
            if (NullUtil.isNullOrEmpty(cart.getGoodsId())) {
                return AjaxResponse.error("商品参数丢失");
            }
            if (NullUtil.isNullOrEmpty(cart.getSpecsId())) {
                return AjaxResponse.error("商品规格参数丢失");
            }
            if (NullUtil.isNullOrEmpty(cart.getAmount())) {
                return AjaxResponse.error("选购数量参数丢失");
            }
            if (cart.getAmount().intValue() < 1 || cart.getAmount().intValue() > 999) {
                return AjaxResponse.error("选购数量只能为1-999");
            }
            ViewUserCart userCart = userCartService.selectUserCartInfo(weChantInfo.getId(), weChantInfo.getAppletId(), cart.getSpecsId());
            if (null != userCart) {
                cart.setId(userCart.getId());
            }
            cart.setWxId(weChantInfo.getId());
            cart.setAppletId(weChantInfo.getAppletId());
            userCartService.addUserCart(cart);
            return AjaxResponse.success("加入成功");
        } catch (Exception e) {
            log.error("加入购物车出错{}", e);
            return AjaxResponse.error("加入失败");
        }
    }

    /**
     * 查询用户购物车列表
     *
     * @param weChantInfo
     * @return
     */
    @RequestMapping(value = "queryUserCartList")
    public Object queryUserCartList(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo) {
        List<ViewUserCart> list = userCartService.selectUserCartList(weChantInfo.getId(), weChantInfo.getAppletId());
        if (NullUtil.isNotNullOrEmpty(list)) {
            return AjaxResponse.success(list);
        }
        return AjaxResponse.error("未找到相关记录");
    }

    /**
     * 更新购物车商品规格数量
     *
     * @param weChantInfo
     * @param id
     * @param amount
     * @return
     */
    @RequestMapping(value = "updateCartGoodsAmount")
    public Object updateCartGoodsAmount(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer id, Integer amount) {
        try {
            if (NullUtil.isNullOrEmpty(id)) {
                return AjaxResponse.error("参数错误");
            }
            if (NullUtil.isNullOrEmpty(amount)) {
                return AjaxResponse.error("选购数量参数丢失");
            }
            if (amount.intValue() < 1 || amount.intValue() > 999) {
                return AjaxResponse.error("选购数量只能为1-999");
            }
            Integer num = userCartService.updateCartGoodsAmount(id, weChantInfo.getId(), weChantInfo.getAppletId(), amount);
            if (num.intValue() > 0) {
                return AjaxResponse.success("更新成功");
            }
        } catch (Exception e) {
            log.error("更新购物车商品规格数量失败{}", e);
        }
        return AjaxResponse.error("更新失败");
    }

    /**
     * 删除购物车商品记录
     *
     * @param weChantInfo
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteUserCart")
    public Object deleteUserCart(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer id) {
        try {
            if (NullUtil.isNullOrEmpty(id)) {
                return AjaxResponse.error("参数错误");
            }
            Integer num = userCartService.updateCartStatus(id, weChantInfo.getId(), weChantInfo.getAppletId());
            if (num.intValue() > 0) {
                return AjaxResponse.success("删除成功");
            }
        } catch (Exception e) {
            log.error("更新购物车商品规格数量失败{}", e);
        }
        return AjaxResponse.error("删除失败");
    }

    /**
     * 加载订单准备信息
     *
     * @param weChantInfo
     * @param mountPrice
     * @return
     */
    @RequestMapping(value = "loadOrderReadyInfo")
    public Object loadOrderReadyInfo(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, String idJson, Double mountPrice) {
        if (NullUtil.isNullOrEmpty(idJson)) {
            return AjaxResponse.error("参数错误");
        }
        List<Integer> idList = JSON.parseObject(idJson, new TypeReference<List<Integer>>() {
        });
        List<ViewUserCart> cartList = userCartService.selectUserCartList(weChantInfo.getId(), weChantInfo.getAppletId(), idList);
        // 检测是否可以使用优惠券
        Boolean ifCoupon = true;
        for (ViewUserCart cart : cartList) {
            if (!cart.getIfDiscount()) {
                ifCoupon = false;
                break;
            }
        }
        ViewUserCoupon coupon = null;
        if (ifCoupon) {
            List<ViewUserCoupon> couponList = userCouponService.selectUserCouponList(weChantInfo.getUserId(), weChantInfo.getAppletId(), mountPrice);
            coupon = NullUtil.isNotNullOrEmpty(couponList) ? couponList.get(0) : null;
        }
        if (null == coupon){
            coupon = new ViewUserCoupon();
            coupon.setDenomination(0.0d);
        }
        ReceiveAddress address = userService.selectReceiveAddressInfo(weChantInfo.getUserId());
        Map map = new HashMap();
        map.put("address", address);
        map.put("coupon", coupon);
        map.put("list", cartList);
        map.put("ifCoupon", ifCoupon);
        return AjaxResponse.success(map);
    }

    /**
     * 查询运费
     *
     * @param appletInfo
     * @param distance
     * @return
     */
    @RequestMapping(value = "getOrderFreight")
    public Object getOrderFreight(@SessionScope("appletInfo") ViewAppletInfo appletInfo, Integer distance) {
        Double freight = userCouponService.countFreight(appletInfo.getId(), distance);
        if (freight.doubleValue() < 0) {
            return AjaxResponse.error("超出配送范围，请重新选择收货地址！");
        }
        return AjaxResponse.success(freight);
    }
}
