package com.applet.apply.controller;

import com.applet.apply.config.annotation.SessionScope;
import com.applet.apply.entity.*;
import com.applet.apply.service.AppletService;
import com.applet.apply.service.UserCartService;
import com.applet.apply.service.UserCouponService;
import com.applet.apply.service.UserOrderService;
import com.applet.common.util.AjaxResponse;
import com.applet.common.util.NullUtil;
import com.applet.common.util.Page;
import com.applet.common.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2020/1/10
 * Time: 14:13
 * To change this template use File | Settings | File Templates.
 * Description: 订单处理中心
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/applet/order/")
public class UserOrderController {
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private UserCartService userCartService;
    @Autowired
    private UserCouponService userCouponService;
    @Autowired
    private AppletService appletService;


    /******************************************用户对订单的操作***********************************************/

    /**
     * 更新购物车状态 - 用户
     *
     * @param appletInfo
     * @param weChantInfo
     * @param id
     */
    @RequestMapping(value = "editUserCartStatus")
    public void editUserCartStatus(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                                   @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer id) {
        try {
            SaleOrder order = userOrderService.selectSaleOrderInfoByUser(id, weChantInfo.getUserId());
            if (null != order) {
                List<SaleOrderDetails> list = userOrderService.selectSaleOrderDetailsList(order.getOrderId());
                List<Integer> idList = new ArrayList<>();
                for (SaleOrderDetails details : list) {
                    idList.add(details.getGoodsSpecsId());
                }
                userCartService.updateUserCartStatus(order.getOrderId(), appletInfo.getId(), weChantInfo.getId(), idList);
            }
        } catch (Exception e) {
            log.error("更新购物车状态出错{}", e);
        }
    }


    /**
     * 查询订单信息 - 用户
     *
     * @param weChantInfo
     * @param id
     * @return
     */
    @RequestMapping(value = "queryOrderInfo")
    public Object queryOrderInfo(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                 Integer id) {
        SaleOrder order = userOrderService.selectSaleOrderInfoByUser(id, weChantInfo.getUserId());
        if (null != order) {
            if (!order.getOrderStatus().toString().equals("0")
                    && !order.getOrderStatus().toString().equals("3")
                    && !order.getOrderStatus().toString().equals("6")) {
                AppletInfo appletInfo = appletService.selectAppletInfo(order.getAppletId());
                Map map = new HashMap();
                map.put("appletLogo", appletInfo.getAppletLogo());
                map.put("lon", appletInfo.getLon());
                map.put("lat", appletInfo.getLat());
                map.put("telephone", appletInfo.getTelephone());

                Map map1 = new HashMap();
                map1.put("order", order);
                map1.put("appletInfo", map);
                return AjaxResponse.success(map1);
            }
        }
        return AjaxResponse.error("未找到相关信息");
    }

    /**
     * 取消订单 - 用户
     *
     * @param weChantInfo
     * @param id
     * @return
     */
    @RequestMapping(value = "cancelOrder")
    public Object cancelOrder(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer id) {
        try {
            SaleOrder order = userOrderService.selectSaleOrderInfoByUser(id, weChantInfo.getUserId());
            if (null != order) {
                if (order.getOrderStatus().toString().equals("1")) {
                    userOrderService.updateSaleOrderStatus(order.getOrderId(), 0, null);
                    return AjaxResponse.success("取消成功");
                } else if (order.getOrderStatus().toString().equals("2")) {
                    return AjaxResponse.error("商家已接单，有问题请联系商家");
                } else if (order.getOrderStatus().toString().equals("3")) {
                    return AjaxResponse.error("商家已拒绝，有问题请联系商家");
                }
            }
            return AjaxResponse.error("网络繁忙，请稍后再试");
        } catch (Exception e) {
            log.error("用户取消订单出错{}", e);
            return AjaxResponse.error("取消失败");
        }
    }

    /**
     * 签收订单 - 用户
     *
     * @param weChantInfo
     * @param id
     * @return
     */
    @RequestMapping(value = "signForOrder")
    public Object signForOrder(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer id) {
        try {
            SaleOrder order = userOrderService.selectSaleOrderInfoByUser(id, weChantInfo.getUserId());
            if (null != order) {
                if (order.getOrderStatus().toString().equals("5")) {
                    userOrderService.updateSaleOrderStatus(order.getOrderId(), 6, null);
                    return AjaxResponse.success("签收成功");
                }
            }
            return AjaxResponse.error("网络繁忙，请稍后再试");
        } catch (Exception e) {
            log.error("用户签收订单出错{}", e);
            return AjaxResponse.error("签收失败");
        }
    }

    /**
     * 查询订单详情列表 - 用户
     *
     * @param weChantInfo
     * @param orderId
     * @return
     */
    @RequestMapping(value = "querySaleOrderDetailsByUser")
    public Object querySaleOrderDetailsByUser(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer orderId) {
        SaleOrder order = userOrderService.selectSaleOrderInfoByUser(orderId, weChantInfo.getUserId());
        if (null != order) {
            Map map = new HashMap<>();
            map.put("order", order);
            // 优惠券信息
            if (NullUtil.isNotNullOrEmpty(order.getUserCouponId())) {
                map.put("coupon", userCouponService.selectUserCouponInfo(order.getUserCouponId(), order.getUserId()));
            } else {
                map.put("coupon", "");
            }
            // 订单详情列表
            map.put("list", userOrderService.selectSaleOrderDetailsList(orderId));
            return AjaxResponse.success(map);
        }
        return AjaxResponse.error("未找到相关记录");
    }








    /******************************************商户对订单的操作***********************************************/



    /**
     * 分页查询订单信息 - 商户
     *
     * @param appletInfo
     * @param orderStatus
     * @param request
     * @return
     */
    @RequestMapping(value = "querySaleOrderByBusiness")
    public Object querySaleOrderByBusiness(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                                           @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                           Integer orderStatus, HttpServletRequest request) {
        if (appletInfo.getUserId().intValue() == weChantInfo.getUserId().intValue()) {
            Page page = PageUtil.initPage(request);
            page = userOrderService.selectSaleOrderByBusinessToPage(appletInfo.getId(), orderStatus, page);
            if (null != page.getDataSource()) {
                return AjaxResponse.success(page);
            }
        }
        return AjaxResponse.error("未找到相关记录");
    }

    /**
     * 商家查询订单详情列表
     *
     * @param appletInfo
     * @param weChantInfo
     * @param orderId
     * @return
     */
    @RequestMapping(value = "querySaleOrderDetailsByBusiness")
    public Object querySaleOrderDetailsByBusiness(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                                                  @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer orderId) {
        if (appletInfo.getUserId().intValue() == weChantInfo.getUserId().intValue()) {
            SaleOrder order = userOrderService.selectSaleOrderInfoByUser(orderId, appletInfo.getId());
            if (null != order) {
                Map map = new HashMap<>();
                AppletInfo info = appletService.selectAppletInfo(order.getAppletId());
                map.put("telephone", info.getTelephone());
                map.put("order", order);
                // 优惠券信息
                if (NullUtil.isNotNullOrEmpty(order.getUserCouponId())) {
                    map.put("coupon", userCouponService.selectUserCoupon(order.getUserCouponId(), order.getUserId()));
                } else {
                    CouponInfo couponInfo = new CouponInfo();
                    couponInfo.setDenomination(0.0d);
                    map.put("coupon", couponInfo);
                }
                // 订单详情列表
                map.put("list", userOrderService.selectSaleOrderDetailsList(orderId));
                return AjaxResponse.success(map);
            }
        }
        return AjaxResponse.error("未找到相关记录");
    }


    /**
     * 更新订单状态 - 商家
     * @param appletInfo
     * @param weChantInfo
     * @param id
     * @param status
     * @param remark
     * @return
     */
    @RequestMapping(value = "updateOrderByBusiness")
    public Object updateOrderByBusiness(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                                        @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                        Integer id, Integer status, String remark) {
        try {
            if (appletInfo.getUserId().intValue() == weChantInfo.getUserId().intValue()
                    && (status.intValue() == 2 || status.intValue() == 3 || status.intValue() == 4 || status.intValue() == 5)){
                SaleOrder order = userOrderService.selectSaleOrderInfoByBusiness(id, appletInfo.getId());
                if (null != order) {
                    if (order.getOrderStatus().toString().equals("0")){
                        return AjaxResponse.error("买家已取消订单");
                    } else if (order.getOrderStatus().toString().equals("1") && status.intValue() == 2) {
                        userOrderService.updateSaleOrderStatus(order.getOrderId(), 2, null);
                        userCouponService.updateUserCouponStatus(order.getUserCouponId(), 2);
                        return AjaxResponse.success("已成功接受订单，准备好商品去配送吧 ^_^");
                    } else if (order.getOrderStatus().toString().equals("1") && status.intValue() == 3) {
                        userOrderService.updateSaleOrderStatus(order.getOrderId(), status, remark);
                        userCouponService.updateUserCouponStatus(order.getUserCouponId(), 0);
                        return AjaxResponse.success("已成功取消订单");
                    } else if (order.getOrderStatus().toString().equals("2")) {
                        userOrderService.updateSaleOrderStatus(order.getOrderId(), 4, null);
                        return AjaxResponse.success("准备准备，咱就开始配送吧 ^_^");
                    } else if (order.getOrderStatus().toString().equals("5")) {
                        userOrderService.updateSaleOrderStatus(order.getOrderId(), 6, null);
                        return AjaxResponse.success("签收成功");
                    }
                }
            }
        } catch (Exception e) {
            log.error("用户签收订单出错{}", e);
        }
        return AjaxResponse.error("网络繁忙，请稍后再试");
    }

}
