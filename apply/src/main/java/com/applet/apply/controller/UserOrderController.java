package com.applet.apply.controller;

import com.applet.apply.config.annotation.SessionScope;
import com.applet.apply.service.*;
import com.applet.common.entity.*;
import com.applet.common.util.AjaxResponse;
import com.applet.common.util.Page;
import com.applet.common.util.PageUtil;
import jodd.datetime.JDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    private CommentService commentService;


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
        ViewOrderInfo order = userOrderService.selectViewOrderInfoByUser(id, weChantInfo.getUserId());
        if (null != order) {
            if (!order.getOrderStatus().toString().equals("0")
                    && !order.getOrderStatus().toString().equals("3")
                    && !order.getOrderStatus().toString().equals("6")) {
                userOrderService.updateOrderSeeRecord(order.getId(), true, null);
                return AjaxResponse.success(order);
            }
        }
        return AjaxResponse.error("未找到相关信息");
    }

    /**
     * 分页查询订单 - 用户
     *
     * @param weChantInfo
     * @param orderStatus
     * @param request
     * @return
     */
    @RequestMapping(value = "querySaleOrderByUser")
    public Object querySaleOrderByUser(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer orderStatus, HttpServletRequest request) {
        Page page = PageUtil.initPage(request);
        List<Byte> list = new ArrayList<>();
        Integer status;
        switch (orderStatus){
            case 1:
                status = 1;
                list.add(status.byteValue());
                break;
            case 2:
                status = 2;
                list.add(status.byteValue());
                status = 4;
                list.add(status.byteValue());
                status = 5;
                list.add(status.byteValue());
                break;
            case 3:
                status = 6;
                list.add(status.byteValue());
                break;
            case 4:
                status = 0;
                list.add(status.byteValue());
                status = 3;
                list.add(status.byteValue());
                break;
            default:
                return AjaxResponse.error("未找到相关记录");
        }
        page = userOrderService.selectSaleOrderByUserToPage(weChantInfo.getUserId(), list, page);
        if (null != page.getDataSource()) {
            return AjaxResponse.success(page);
        }
        return AjaxResponse.error("未找到相关记录");
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
                    userOrderService.updateOrderSeeRecord(order.getOrderId(), true, false);
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
                    userOrderService.updateOrderSeeRecord(order.getOrderId(), true, false);
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
        ViewOrderInfo order = userOrderService.selectViewOrderInfoByUser(orderId, weChantInfo.getUserId());
        if (null != order) {
            //更新订单查看记录
            userOrderService.updateOrderSeeRecord(order.getId(), true, null);

            // 加载订单详情商品分类集合
            List<Map> goodsList = userOrderService.loadSaleOrderDetailsByGoodsGroup(order.getId());

            // 订单详情列表
            List<SaleOrderDetails> detailsList = userOrderService.selectSaleOrderDetailsList(order.getId());

            // 排查已签收且用户查看后7天内订单未评论的商品，7天后不可评论
            List<Integer> goodsIdList = new ArrayList<>();
            JDateTime oldTime = new JDateTime(order.getUpdateTime());
            JDateTime nowTime = new JDateTime(new Date());
            if (order.getOrderStatus().intValue() == 6 && oldTime.addDay(7).compareDateTo(nowTime) > 0){
                List<Integer> orderIdList = new ArrayList<>();
                for (SaleOrderDetails details : detailsList) {
                    orderIdList.add(details.getOrderId());
                    goodsIdList.add(details.getGoodsId());
                }
                // 去除重复的goodsId
                LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(goodsIdList);
                goodsIdList = new ArrayList<>(hashSet);
                // 去除重复的orderId
                hashSet = new LinkedHashSet<>(orderIdList);
                orderIdList = new ArrayList<>(hashSet);
                // 查询订单商品已评论的记录
                List<ViewCommentInfo> commentList = commentService.loadOrderGoodsCommentListByAlready(orderIdList, goodsIdList);
                goodsIdList = new ArrayList<>();
                for (SaleOrderDetails details : detailsList) {
                    boolean bool = true;
                    for (ViewCommentInfo comment : commentList) {
                        if (details.getGoodsId().intValue() == comment.getGoodsId().intValue()){
                            bool = false;
                            break;
                        }
                    }
                    if (bool){
                        goodsIdList.add(details.getGoodsId());
                    }
                }
                // 去除重复的goodsId
                hashSet = new LinkedHashSet<>(goodsIdList);
                goodsIdList = new ArrayList<>(hashSet);
            }

            // 封装信息到Map
            Map map = new HashMap<>();
            map.put("order", order);
            map.put("goodsList", goodsList);
            map.put("specsList", detailsList);
            map.put("goodsIdList", goodsIdList);
            return AjaxResponse.success(map);
        }
        return AjaxResponse.error("未找到相关记录");
    }


    /******************************************商户对订单的操作***********************************************/

    /**
     * 查询订单信息列表 - 商户
     *
     * @param appletInfo
     * @param weChantInfo
     * @param orderStatus
     * @return
     */
    @RequestMapping(value = "querySaleOrderByStore")
    public Object querySaleOrderByStore(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                                        @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                        Integer orderStatus) {
        Page page = new Page(1, 10);
        page = userOrderService.selectSaleOrderByStoreToList(appletInfo.getId(), weChantInfo.getUserId(), orderStatus, page);
        if (null != page.getDataSource()) {
            return AjaxResponse.success(page);
        }
        return AjaxResponse.error("未找到相关记录");
    }

    /**
     * 分页查询订单信息列表 - 商户
     *
     * @param appletInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "querySaleOrderByStoreToPage")
    public Object querySaleOrderByStoreToPage(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                                              @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                              HttpServletRequest request) {
        Page page = PageUtil.initPage(request);
        page = userOrderService.selectSaleOrderByStoreToPage(appletInfo.getId(), weChantInfo.getUserId(), page);
        if (null != page.getDataSource()) {
            return AjaxResponse.success(page);
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
    @RequestMapping(value = "querySaleOrderDetailsByStore")
    public Object querySaleOrderDetailsByStore(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                                               @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer orderId) {
        ViewOrderInfo order = userOrderService.selectViewOrderInfoByStore(orderId, appletInfo.getId(), weChantInfo.getUserId());
        if (null != order) {
            //更新订单查看记录
            userOrderService.updateOrderSeeRecord(order.getId(), null, true);

            // 加载订单详情商品分类集合
            List<Map> goodsList = userOrderService.loadSaleOrderDetailsByGoodsGroup(order.getId());

            // 订单详情列表
            List<SaleOrderDetails> detailsList = userOrderService.selectSaleOrderDetailsList(order.getId());

            // 封装信息到Map
            Map map = new HashMap<>();
            map.put("order", order);
            map.put("goodsList", goodsList);
            map.put("specsList", detailsList);
            return AjaxResponse.success(map);
        }
        return AjaxResponse.error("未找到相关记录");
    }


    /**
     * 更新订单状态 - 商家
     *
     * @param appletInfo
     * @param weChantInfo
     * @param id
     * @param status
     * @param remark
     * @return
     */
    @RequestMapping(value = "updateOrderStatusByStore")
    public Object updateOrderStatusByStore(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                                           @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                           Integer id, Integer status, String remark) {
        try {
            if (status.intValue() == 2 || status.intValue() == 3 || status.intValue() == 4 || status.intValue() == 5) {
                ViewOrderInfo order = userOrderService.selectViewOrderInfoByStore(id, appletInfo.getId(), weChantInfo.getUserId());
                if (null != order) {
                    if (order.getOrderStatus().toString().equals("0")) {
                        return AjaxResponse.error("买家已取消订单");
                    } else if (order.getOrderStatus().toString().equals("1") && status.intValue() == 2) {
                        userOrderService.updateSaleOrderStatus(order.getId(), 2, null);
                        userCouponService.updateUserCouponStatus(order.getUserCouponId(), 2);
                        return AjaxResponse.success("已成功接受订单，准备好商品去配送吧 ^_^");
                    } else if (order.getOrderStatus().toString().equals("1") && status.intValue() == 3) {
                        userOrderService.updateSaleOrderStatus(order.getId(), 3, remark);
                        userCouponService.updateUserCouponStatus(order.getUserCouponId(), 0);
                        return AjaxResponse.success("已成功取消订单");
                    } else if (order.getOrderStatus().toString().equals("2") && status.intValue() == 4) {
                        userOrderService.updateSaleOrderStatus(order.getId(), 4, null);
                        return AjaxResponse.success("准备准备，咱就开始配送吧 ^_^");
                    } else if (order.getOrderStatus().toString().equals("4") && status.intValue() == 5) {
                        userOrderService.updateSaleOrderStatus(order.getId(), 5, null);
                        return AjaxResponse.success("辛苦啦，又完成了一单 ^_^");
                    }
                }
            }
        } catch (Exception e) {
            log.error("用户签收订单出错{}", e);
        }
        return AjaxResponse.error("网络繁忙，请稍后再试");
    }

}
