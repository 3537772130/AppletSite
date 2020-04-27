package com.applet.user.controller;

import com.applet.common.entity.*;
import com.applet.common.util.AjaxResponse;
import com.applet.common.util.Constants;
import com.applet.common.util.Page;
import com.applet.common.util.PageUtil;
import com.applet.user.config.annotation.SessionScope;
import com.applet.user.service.UserOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2020/4/27
 * Time: 14:50
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/user/order/")
public class UserOrderController {
    @Autowired
    private UserOrderService userOrderService;

    /**
     * 用户分页查订单列表
     *
     * @param user
     * @param orderNo
     * @param startTime
     * @param overTime
     * @param request
     * @return
     */
    @RequestMapping(value = "queryUserOrderByPage")
    public Object queryUserOrderByPage(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, String orderNo,
                                       String startTime, String overTime, HttpServletRequest request) {
        Page page = PageUtil.initPage(request);
        page = userOrderService.selectUserOrderInfoByPage(user.getId(), orderNo, startTime, overTime, page);
        return AjaxResponse.success(page);
    }

    /**
     * 加载用户订单详情
     *
     * @param user
     * @param orderId
     * @return
     */
    @RequestMapping(value = "loadUserOrderInfo")
    public Object loadUserOrderInfo(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer orderId) {
        ViewOrderInfo info = userOrderService.selectUserOrderInfoById(orderId, user.getId());
        if (null != info) {
            List<OrderDetails> list = userOrderService.selectUserOrderInfoListByOrderId(info.getId());
            OrderReceiver receiver = userOrderService.selectOrderReceiverByOrderId(info.getId());
            Map map = new HashMap();
            map.put("info", info);
            map.put("list", list);
            map.put("receiver", receiver);
            return AjaxResponse.success(map);
        }
        return AjaxResponse.error("未找到相关记录");
    }

    /**
     * 商户分页查订单列表
     *
     * @param user
     * @param orderNo
     * @param startTime
     * @param overTime
     * @param request
     * @return
     */
    @RequestMapping(value = "queryStoreOrderByPage")
    public Object queryStoreOrderByPage(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, String orderNo,
                                        String startTime, String overTime, HttpServletRequest request) {
        Page page = PageUtil.initPage(request);
        page = userOrderService.selectStoreOrderInfoByPage(user.getId(), orderNo, startTime, overTime, page);
        return AjaxResponse.success(page);
    }

    /**
     * 加载商户订单详情
     *
     * @param user
     * @param orderId
     * @return
     */
    @RequestMapping(value = "loadStoreOrderInfo")
    public Object loadStoreOrderInfo(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer orderId) {
        ViewOrderInfo info = userOrderService.selectStoreOrderInfoById(orderId, user.getId());
        if (null != info) {
            List<OrderDetails> list = userOrderService.selectUserOrderInfoListByOrderId(info.getId());
            OrderReceiver receiver = userOrderService.selectOrderReceiverByOrderId(info.getId());
            Map map = new HashMap();
            map.put("info", info);
            map.put("list", list);
            map.put("receiver", receiver);
            return AjaxResponse.success(map);
        }
        return AjaxResponse.error("未找到相关记录");
    }

    /**
     * 加载订单操作记录
     *
     * @param user
     * @param orderId
     * @return
     */
    @RequestMapping(value = "loadOrderOperateRecord")
    public Object loadOrderOperateRecord(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer orderId) {
        ViewOrderInfo Info = userOrderService.selectStoreOrderInfoById(orderId, user.getId());
        if (null != Info) {
            List<OrderOperateRecord> list = userOrderService.selectOrderOperateRecordByOrderId(Info.getId());
            return AjaxResponse.success(list);
        }
        return AjaxResponse.error("未找到相关记录");
    }
}
