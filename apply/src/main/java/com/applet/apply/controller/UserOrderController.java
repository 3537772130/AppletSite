package com.applet.apply.controller;

import com.alibaba.fastjson.JSON;
import com.applet.apply.config.annotation.SessionScope;
import com.applet.apply.service.*;
import com.applet.common.entity.*;
import com.applet.common.util.enums.OrderEnums;
import com.applet.common.util.*;
import jodd.datetime.JDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

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
    private UserService userService;
    @Autowired
    @Lazy
    private UserOrderService userOrderService;
    @Autowired
    private UserCartService userCartService;
    @Autowired
    private UserCouponService userCouponService;
    @Autowired
    private CommentService commentService;

    /**
     * 查询测试订单总数（当天）
     * @param weChantInfo
     * @return
     */
    @RequestMapping(value = "queryTestOrderCountToDay")
    public Object queryTestOrderCountToDay(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo){
        long count = userOrderService.countTestOrder(weChantInfo.getAppletId(), weChantInfo.getId(), weChantInfo.getUserId());
        return AjaxResponse.success(count);
    }

    /**
     * 小程序创建订单
     *
     * @param appletInfo
     * @param weChantInfo
     * @param cartIdJson
     * @param addressIdStr
     * @param payTypeStr
     * @param couponIdStr
     * @param userRemark
     * @param distanceStr
     * @return
     */
    @PostMapping(value = "createOrder")
    public Object createOrder(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                              @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                              @RequestParam("cartIdJson") String cartIdJson, @RequestParam("addressIdStr") String addressIdStr,
                              @RequestParam("payTypeStr") String payTypeStr, @RequestParam("couponIdStr") String couponIdStr,
                              @RequestParam("userRemark") String userRemark, @RequestParam("distanceStr") String distanceStr) {
        try {
            if (NullUtil.isNullOrEmpty(cartIdJson) || NullUtil.isNullOrEmpty(addressIdStr) || NullUtil.isNullOrEmpty(payTypeStr)) {
                return AjaxResponse.error("参数缺失");
            }
            List<Integer> cartIdList = JSON.parseArray(cartIdJson, Integer.class);
            Integer addressId = Integer.parseInt(addressIdStr);
            Integer payType = Integer.parseInt(payTypeStr);
            Integer couponId = NullUtil.isNotNullOrEmpty(couponIdStr) ? Integer.parseInt(couponIdStr) : null;
            Integer distance = NullUtil.isNotNullOrEmpty(distanceStr) ? Integer.parseInt(distanceStr) : null;
            userRemark = userRemark.equals("null") ? "" : userRemark;
            // 订单详情
            List<ViewUserCart> cartList = userCartService.selectUserCartList(cartIdList, appletInfo.getId(), weChantInfo.getId());
            if (NullUtil.isNullOrEmpty(cartList)) {
                return AjaxResponse.error("未找到相关商品记录");
            }
            List<OrderDetails> detailsList = new ArrayList<>();
            Double goodsAmount = 0.0d;
            for (ViewUserCart cart : cartList) {
                OrderDetails details = new OrderDetails();
                details.setCartId(cart.getId());
                details.setGoodsId(cart.getGoodsId());
                details.setGoodsName(cart.getGoodsName());
                details.setGoodsDiscount(cart.getDiscount());
                details.setGoodsSpecsId(cart.getSpecsId());
                details.setGoodsSpecsName(cart.getSpecsName());
                details.setGoodsSpecsType(cart.getSpecsType());
                details.setGoodsSpecsPic(cart.getSpecsSrc());
                details.setGoodsNumber(cart.getAmount());
                details.setSellPrice(cart.getSellPrice());
                Double price = Arith.mul(cart.getSellPrice(), cart.getDiscount().doubleValue());
                details.setActualPrice(Arith.div(price, 100.0d, 2));
                detailsList.add(details);
                // 统计商品金额总值
                goodsAmount = Arith.add(goodsAmount, details.getActualPrice());
            }

            // 收货地址
            ReceiveAddress address = userService.selectReceiveAddressInfo(addressId, weChantInfo.getUserId());
            if (null == address) {
                return AjaxResponse.error("地址已失效，请重新选择");
            }

            // 优惠券
            ViewUserCoupon userCoupon = null;
            if (NullUtil.isNotNullOrEmpty(couponId)) {
                userCoupon = userCouponService.selectUserCouponInfo(couponId, weChantInfo.getUserId());
                if (null == userCoupon) {
                    return AjaxResponse.error("未找到相关优惠券");
                }
                Date now = new Date();
                if (userCoupon.getActivityOver().getTime() < now.getTime()) {
                    return AjaxResponse.error("优惠卷已过期");
                }
            }

            // 运费
            Double freightFee = 0.0d;
            if (NullUtil.isNotNullOrEmpty(distance)) {
                freightFee = userCouponService.countFreight(appletInfo.getId(), distance, goodsAmount);
                if (freightFee < 0) {
                    return AjaxResponse.error("超出配送范围");
                }
            }

            // 订单信息
            OrderInfo info = new OrderInfo();
            info.setOrderNo(RandomUtil.getTimeStamp());
            info.setAppletId(appletInfo.getId());
            info.setUserId(weChantInfo.getUserId());
            info.setWxId(weChantInfo.getId());
            info.setUserRemark(userRemark);
            info.setOrderStatus(OrderEnums.OrderStatus.WAIT.getCode());
            info.setCouponAmount(0.0d);
            if (null != userCoupon) {
                info.setUserCouponId(userCoupon.getId());
                info.setCouponAmount(userCoupon.getDenomination());
            }
            info.setFreightAmount(freightFee);
            info.setTotalAmount(goodsAmount);
            Double actualAmount = Arith.sub(Arith.sub(goodsAmount, freightFee), info.getCouponAmount());
            info.setActualAmount(actualAmount);
            info.setPayStatus(OrderEnums.PayStatus.WAIT.getCode());
            info.setPayType(payType);
            info.setPayChannel(OrderEnums.PayChannel.WX_JSAPI.getName());

            OrderReceiver receiver = new OrderReceiver();
            receiver.setReceiverName(address.getName());
            receiver.setReceiverMobile(address.getMobile());
            receiver.setReceiverProvince(address.getProvince());
            receiver.setReceiverCity(address.getCity());
            receiver.setReceiverCounty(address.getCounty());
            receiver.setReceiverAddress(address.getAddress());
            receiver.setReceiverLat(address.getLat());
            receiver.setReceiverLon(address.getLon());

            userOrderService.addOrderInfo(info, detailsList, receiver);
            return AjaxResponse.success(info.getId());
        } catch (NumberFormatException e) {
            log.error("创建订单出错{}", e);
        }
        return AjaxResponse.error("创建订单失败");
    }


    /******************************************用户对订单的操作***********************************************/

    /**
     * 查询订单信息 - 用户
     *
     * @param weChantInfo
     * @param id
     * @return
     */
    @RequestMapping(value = "queryOrderInfo")
    public Object queryOrderInfo(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer id) {
        ViewOrderDetails order = userOrderService.selectViewOrderDetailsByUser(weChantInfo.getUserId(), id);
        if (null != order) {
            if (order.getOperateStatus().intValue() != 0
                    && order.getOperateStatus().intValue() != 3
                    && order.getOperateStatus().intValue() != 6) {
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
    @RequestMapping(value = "queryOrderInfoByUser")
    public Object queryOrderInfoByUser(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer orderStatus, HttpServletRequest request) {
        Page page = PageUtil.initPage(request);
        List<Integer> list = new ArrayList<>();
        switch (orderStatus) {
            case 1:
                list.add(1);
                break;
            case 2:
                list.add(2);
                list.add(4);
                list.add(5);
                break;
            case 3:
                list.add(6);
                break;
            case 4:
                list.add(-1);
                list.add(0);
                list.add(3);
                break;
            default:
                return AjaxResponse.error("未找到相关记录");
        }
        page = userOrderService.selectOrderInfoByUserToPage(weChantInfo.getUserId(), list, page);
        if (null == page.getDataSource()) {
            return AjaxResponse.error("未找到相关记录");
        }
        return AjaxResponse.success(page);
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
            ViewOrderInfo order = userOrderService.selectViewOrderInfoByUser(id, weChantInfo.getUserId());
            if (null != order && order.getOrderStatus().intValue() == OrderEnums.OrderStatus.WAIT.getCode()) {
                if (order.getOrderStatus().intValue() == OrderEnums.OperateStatus.SUBMIT.getCode()) {
                    OrderInfo info = userOrderService.selectOrderInfoById(order.getId());
                    userOrderService.updateOrderRelevant(info, OrderEnums.OperateStatus.CANCEL.getCode());
                    return AjaxResponse.success("取消成功");
                } else if (order.getOrderStatus().intValue() == OrderEnums.OperateStatus.MERCHANT_CONFIRM.getCode()) {
                    return AjaxResponse.error("商家已接单，有问题请联系商家");
                } else if (order.getOrderStatus().intValue() == OrderEnums.OperateStatus.CANCEL.getCode()) {
                    return AjaxResponse.error("商家已取消订单，有问题请联系商家");
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
            ViewOrderInfo order = userOrderService.selectViewOrderInfoByUser(id, weChantInfo.getUserId());
            if (null != order && order.getOrderStatus().intValue() == OrderEnums.OrderStatus.WAIT.getCode()) {
                OrderInfo info = userOrderService.selectOrderInfoById(order.getId());
                if (order.getOperateStatus().intValue() == OrderEnums.OperateStatus.CONFIRM_ARRIVE.getCode()) {
                    userOrderService.updateOrderRelevant(info, OrderEnums.OperateStatus.SIGN_FOR.getCode());
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
    @RequestMapping(value = "queryOrderDetailsByUser")
    public Object queryOrderDetailsByUser(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer orderId) {
        ViewOrderInfo order = userOrderService.selectViewOrderInfoByUser(orderId, weChantInfo.getUserId());
        if (null != order) {
            //更新订单查看记录
            userOrderService.updateOrderSeeRecord(order.getId(), true, null);

            // 加载订单详情商品分类集合
            List<Map> goodsList = userOrderService.loadOrderDetailsByGoodsGroup(order.getId());

            // 订单详情列表
            List<OrderDetails> detailsList = userOrderService.selectOrderDetailsList(order.getId());

            // 排查已签收且用户查看后7天内订单未评论的商品，7天后不可评论
            List<Integer> goodsIdList = new ArrayList<>();
            JDateTime oldTime = new JDateTime(order.getUpdateTime());
            JDateTime nowTime = new JDateTime(new Date());
            if (order.getOrderStatus().intValue() == 6 && oldTime.addDay(7).compareDateTo(nowTime) > 0) {
                List<Integer> orderIdList = new ArrayList<>();
                for (OrderDetails details : detailsList) {
                    orderIdList.add(details.getId());
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
                for (OrderDetails details : detailsList) {
                    boolean bool = true;
                    for (ViewCommentInfo comment : commentList) {
                        if (details.getGoodsId().intValue() == comment.getGoodsId().intValue()) {
                            bool = false;
                            break;
                        }
                    }
                    if (bool) {
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
    @RequestMapping(value = "queryOrderInfoByStore")
    public Object queryOrderInfoByStore(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                                        @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                        Integer orderStatus) {
        Page page = new Page(1, 10);
        page = userOrderService.selectOrderInfoByStoreToList(appletInfo.getId(), weChantInfo.getUserId(), orderStatus, page);
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
    @RequestMapping(value = "queryOrderInfoByStoreToPage")
    public Object queryOrderInfoByStoreToPage(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                                              @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                              HttpServletRequest request) {
        Page page = PageUtil.initPage(request);
        page = userOrderService.selectOrderInfoByStoreToPage(appletInfo.getId(), weChantInfo.getUserId(), page);
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
    @RequestMapping(value = "queryOrderDetailsByStore")
    public Object queryOrderDetailsByStore(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                                           @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer orderId) {
        ViewOrderInfo order = userOrderService.selectViewOrderInfoByStore(orderId, appletInfo.getId(), weChantInfo.getUserId());
        if (null != order) {
            //更新订单查看记录
            userOrderService.updateOrderSeeRecord(order.getId(), null, true);

            // 加载订单详情商品分类集合
            List<Map> goodsList = userOrderService.loadOrderDetailsByGoodsGroup(order.getId());

            // 订单详情列表
            List<OrderDetails> detailsList = userOrderService.selectOrderDetailsList(order.getId());

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
     * @param remark
     * @return
     */
    @RequestMapping(value = "updateOrderStatusByStore")
    public Object updateOrderStatusByStore(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                                           @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                           Integer id, String remark) {
        try {
            ViewOrderInfo order = userOrderService.selectViewOrderInfoByStore(id, appletInfo.getId(), weChantInfo.getUserId());
            if (null != order) {
                if (order.getOrderStatus().intValue() != OrderEnums.OrderStatus.WAIT.getCode() ||
                        order.getOperateStatus().intValue() == OrderEnums.OperateStatus.CANCEL.getCode()) {
                    return AjaxResponse.error("消费者已取消订单");
                } else {
                    OrderInfo info = userOrderService.selectOrderInfoById(order.getId());
                    if (order.getOperateStatus().intValue() == OrderEnums.OperateStatus.SUBMIT.getCode()) {
                        userOrderService.updateOrderRelevant(info, OrderEnums.OperateStatus.MERCHANT_CONFIRM.getCode());
                        return AjaxResponse.success("已成功接受订单，准备好商品去配送吧 ^_^");
                    } else if (order.getOperateStatus().intValue() == OrderEnums.OperateStatus.SUBMIT.getCode()) {
                        if (NullUtil.isNullOrEmpty(remark)) {
                            return AjaxResponse.success("请填写拒绝订单的原由");
                        }
                        userOrderService.updateOrderRelevant(info, OrderEnums.OperateStatus.CANCEL.getCode(), remark);
                        return AjaxResponse.success("已成功取消订单");
                    } else if (order.getOperateStatus().intValue() == OrderEnums.OperateStatus.MERCHANT_CONFIRM.getCode()) {
                        userOrderService.updateOrderRelevant(info, OrderEnums.OperateStatus.MERCHANT_DELIVERY.getCode());
                        return AjaxResponse.success("准备准备，咱就开始配送吧 ^_^");
                    } else if ((order.getOperateStatus().intValue() == OrderEnums.OperateStatus.MERCHANT_DELIVERY.getCode()
                            || order.getOperateStatus().intValue() == OrderEnums.OperateStatus.INSTANT_DELIVERY.getCode()
                            || order.getOperateStatus().intValue() == OrderEnums.OperateStatus.LOGISTICS_DELIVERY.getCode())) {
                        userOrderService.updateOrderRelevant(info, OrderEnums.OperateStatus.CONFIRM_ARRIVE.getCode());
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
