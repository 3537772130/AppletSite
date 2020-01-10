package com.applet.apply.controller;

import com.applet.apply.config.annotation.SessionScope;
import com.applet.apply.entity.*;
import com.applet.apply.service.UserCartService;
import com.applet.apply.service.UserOrderService;
import com.applet.common.util.AjaxResponse;
import com.applet.common.util.Page;
import com.applet.common.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2020/1/10
 * Time: 14:13
 * To change this template use File | Settings | File Templates.
 * Description: 用户订单处理中心
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/applet/order/")
public class UserOrderController {
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private UserCartService userCartService;

    /**
     * 更新购物车状态
     * @param appletInfo
     * @param weChantInfo
     * @param id
     */
    @RequestMapping(value = "editUserCartStatus")
    public void editUserCartStatus(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                                     @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer id){
        try {
            SaleOrder order = userOrderService.selectSaleOrderInfo(id, appletInfo.getId(), weChantInfo.getUserId());
            if (null != order) {
                List<SaleOrderDetails> list = userOrderService.selectSaleOrderDetailsList(order.getOrderId());
                List<Integer> idList = new ArrayList<>();
                for (SaleOrderDetails details: list) {
                    idList.add(details.getGoodsSpecsId());
                }
                userCartService.updateUserCartStatus(appletInfo.getId(), weChantInfo.getId(), idList);
            }
        } catch (Exception e) {
            log.error("更新购物车状态出错{}", e);
        }
    }


    /**
     * 查询订单信息
     *
     * @param appletInfo
     * @param weChantInfo
     * @param id
     * @return
     */
    @RequestMapping(value = "queryOrderInfo")
    public Object queryOrderInfo(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                                 @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer id) {
        SaleOrder order = userOrderService.selectSaleOrderInfo(id, appletInfo.getId(), weChantInfo.getUserId());
        if (null != order) {
            if (!order.getOrderStatus().toString().equals("0")
                    && !order.getOrderStatus().toString().equals("3")
                    && !order.getOrderStatus().toString().equals("6")) {
                return AjaxResponse.success(order);
            }
        }
        return AjaxResponse.error("未找到相关信息");
    }

    /**
     * 取消订单
     * @param appletInfo
     * @param weChantInfo
     * @param id
     * @return
     */
    @RequestMapping(value = "cancelOrder")
    public Object cancelOrder(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                              @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer id) {
        try {
            SaleOrder order = userOrderService.selectSaleOrderInfo(id, appletInfo.getId(), weChantInfo.getUserId());
            if (null != order) {
                if (order.getOrderStatus().toString().equals("1")) {
                    userOrderService.updateSaleOrderStatus(order.getOrderId(), 0);
                    return AjaxResponse.success("取消成功");
                }
                return AjaxResponse.error("商家已接单，有问题请联系商家");
            }
            return AjaxResponse.error("未找到相关信息");
        } catch (Exception e) {
            log.error("用户取消订单出错{}", e);
            return AjaxResponse.error("取消失败");
        }
    }

    /**
     * 签收订单
     * @param appletInfo
     * @param weChantInfo
     * @param id
     * @return
     */
    @RequestMapping(value = "signForOrder")
    public Object signForOrder(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                               @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer id) {
        try {
            SaleOrder order = userOrderService.selectSaleOrderInfo(id, appletInfo.getId(), weChantInfo.getUserId());
            if (null != order) {
                if (order.getOrderStatus().toString().equals("5")) {
                    userOrderService.updateSaleOrderStatus(order.getOrderId(), 6);
                    return AjaxResponse.success("签收成功");
                }
                return AjaxResponse.error("商家已接单，有问题请联系商家");
            }
            return AjaxResponse.error("未找到相关信息");
        } catch (Exception e) {
            log.error("用户签收订单出错{}", e);
            return AjaxResponse.error("签收失败");
        }
    }

    /**
     * 分页查询订单信息 - 商户
     * @param appletInfo
     * @param orderStatus
     * @param request
     * @return
     */
    @RequestMapping(value = "querySaleOrderByBusiness")
    public Object querySaleOrderByBusiness(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                                           @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                           Integer orderStatus, HttpServletRequest request){
        if (appletInfo.getUserId().intValue() == weChantInfo.getUserId().intValue()){
            Page page = PageUtil.initPage(request);
            page = userOrderService.selectSaleOrderByBusinessToPage(appletInfo.getId(), orderStatus, page);
            if (null != page.getDataSource()){
                return AjaxResponse.success(page);
            }
        }
        return AjaxResponse.error("未找到相关记录");
    }
}
