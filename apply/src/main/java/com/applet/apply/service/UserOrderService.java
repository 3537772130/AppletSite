package com.applet.apply.service;

import com.applet.common.entity.*;
import com.applet.common.mapper.*;
import com.applet.common.util.enums.OrderEnums;
import com.applet.common.util.NullUtil;
import com.applet.common.util.Page;
import jodd.datetime.JDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2020/1/10
 * Time: 14:08
 * To change this template use File | Settings | File Templates.
 * Description: 用户订单服务类
 */
@SuppressWarnings("ALL")
@Slf4j
@Service
public class UserOrderService {
    @Autowired
    private UserCouponService userCouponService;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private OrderReceiverMapper orderReceiverMapper;
    @Autowired
    private OrderDetailsMapper orderDetailsMapper;
    @Autowired
    private OrderOperateRecordMapper orderOperateRecordMapper;
    @Autowired
    private OrderRequestRecordMapper orderRequestRecordMapper;
    @Autowired
    private OrderSeeRecordMapper orderSeeRecordMapper;
    @Autowired
    private ViewOrderInfoMapper viewOrderInfoMapper;
    @Autowired
    private ViewUserOrderCountMapper viewUserOrderCountMapper;
    @Autowired
    private ViewAppletUserOrderCountMapper viewAppletUserOrderCountMapper;
    @Autowired
    private UserCartService userCartService;
    @Autowired
    private ViewOrderPayDataMapper viewOrderPayDataMapper;
    @Autowired
    private ViewOrderDetailsMapper viewOrderDetailsMapper;
    @Autowired
    private CommonMapper commonMapper;

    /**
     * 添加订单信息，并更新相关记录
     *
     * @param info
     * @param list
     * @param cartIdList
     */
    @Transactional(rollbackFor = Exception.class)
    public void addOrderInfo(OrderInfo info, List<OrderDetails> list, List<Integer> cartIdList, OrderReceiver receiver) {
        info.setCreateTime(new Date());
        info.setUpdateTime(new Date());
        orderInfoMapper.insertSelective(info);
        // 添加收货人信息
        receiver.setOrderId(info.getId());
        orderReceiverMapper.insertSelective(receiver);
        // 添加订单操作记录
        OrderOperateRecord record = new OrderOperateRecord();
        record.setOrderId(info.getId());
        record.setOperateUserId(info.getUserId());
        record.setOperateTime(new Date());
        record.setOperateStatus(OrderEnums.OperateStatus.MAKE.getCode());
        addOrderOperateRecord(record);
        // 批量插入订单商品详情信息
        list.forEach(it -> it.setOrderId(info.getId()));
        orderDetailsMapper.batchInsert(list);
        // 更新优惠券状态
        if (NullUtil.isNotNullOrEmpty(info.getUserCouponId())) {
            userCouponService.updateUserCouponStatus(info.getUserCouponId(), OrderEnums.UserCouponStatus.USING.getCode());
        }
        // 更新购物车记录信息状态
        userCartService.updateUserCartStatus(info.getId(), info.getAppletId(), info.getWxId(), cartIdList);
        // 添加订单查看记录
        addOrderSeeRecord(info.getId());
    }

    /**
     * 查询订单信息
     *
     * @param id
     * @param appletId
     * @param userId
     * @return
     */
    public OrderInfo selectOrderInfo(Integer id, Integer appletId, Integer userId) {
        OrderInfoExample example = new OrderInfoExample();
        example.createCriteria().andIdEqualTo(id).andAppletIdEqualTo(appletId).andUserIdEqualTo(userId);
        List<OrderInfo> list = orderInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询订单信息
     *
     * @param orderNo
     * @return
     */
    public OrderInfo selectOrderInfo(String orderNo) {
        OrderInfoExample example = new OrderInfoExample();
        example.createCriteria().andOrderNoEqualTo(orderNo);
        List<OrderInfo> list = orderInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询订单信息
     *
     * @param id
     * @return
     */
    public OrderInfo selectOrderInfoById(Integer id) {
        return orderInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询订单信息
     *
     * @param id
     * @return
     */
    public ViewOrderDetails selectOrderInfoByOrderNo(String orderNo) {
        ViewOrderDetailsExample example = new ViewOrderDetailsExample();
        example.createCriteria().andOrderNoEqualTo(orderNo).andPayStatusEqualTo(OrderEnums.PayStatus.WAIT.getCode());
        List<ViewOrderDetails> list = viewOrderDetailsMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }


    /**
     * 查询订单支付参数信息
     *
     * @param id
     * @param orderNo
     * @return
     */
    public ViewOrderPayData selectOrderData(Integer id, String orderNo, Integer appletId, Integer wxId) {
        ViewOrderPayDataExample example = new ViewOrderPayDataExample();
        ViewOrderPayDataExample.Criteria c = example.createCriteria();
        if (NullUtil.isNotNullOrEmpty(id)) {
            c.andIdEqualTo(id);
        }
        if (NullUtil.isNotNullOrEmpty(orderNo)) {
            c.andOrderNoEqualTo(orderNo);
        }
        if (NullUtil.isNotNullOrEmpty(appletId)) {
            c.andAppletIdEqualTo(appletId);
        }
        if (NullUtil.isNotNullOrEmpty(wxId)) {
            c.andWxIdEqualTo(wxId);
        }
        List<ViewOrderPayData> list = viewOrderPayDataMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    public ViewOrderPayData selectOrderData(Integer id, Integer appletId, Integer wxId) {
        return selectOrderData(id, null, appletId, wxId);
    }

    public ViewOrderPayData selectOrderData(String orderNo) {
        return selectOrderData(null, orderNo, null, null);
    }

    /**
     * 根据支付关联标识查询在线支付尚未付款订单
     *
     * @param payRelationId
     * @return
     */
    public OrderInfo selectOrderInfoByPayRelationId(String payRelationId, String payChannel) {
        OrderInfoExample example = new OrderInfoExample();
        example.createCriteria().andPayTypeEqualTo(1).andPayStatusEqualTo(0).andPayRelationIdEqualTo(payRelationId).andPayChannelEqualTo(payChannel);
        List<OrderInfo> list = orderInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询订单查看记录
     *
     * @param orderId
     * @return
     */
    public OrderSeeRecord selectOrderSeeRecord(Integer orderId) {
        OrderSeeRecordExample example = new OrderSeeRecordExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        List<OrderSeeRecord> list = orderSeeRecordMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 添加订单查看记录
     *
     * @param orderId
     */
    @Async("taskExecutor")
    public void addOrderSeeRecord(Integer orderId) {
        OrderSeeRecord record = selectOrderSeeRecord(orderId);
        if (null == record) {
            record = new OrderSeeRecord();
            record.setOrderId(orderId);
            record.setUserSeeStatus(false);
            record.setUserSeeTime(new Date());
            record.setStoreSeeStatus(false);
            record.setStoreSeeTime(new Date());
            orderSeeRecordMapper.insertSelective(record);
        }
    }

    /**
     * 更新订单查看记录
     *
     * @param orderId
     * @param userStatus
     * @param storeStatus
     */
    @Async("taskExecutor")
    public void updateOrderSeeRecord(Integer orderId, Boolean userStatus, Boolean storeStatus) {
        OrderSeeRecord record = selectOrderSeeRecord(orderId);
        if (null != record) {
            OrderSeeRecordExample example = new OrderSeeRecordExample();
            example.createCriteria().andOrderIdEqualTo(orderId);
            OrderSeeRecord record1 = new OrderSeeRecord();
            record1.setUserSeeStatus(userStatus);
            if (NullUtil.isNotNullOrEmpty(userStatus) && !record.getUserSeeStatus()) {
                record1.setUserSeeTime(new Date());
            }
            record1.setStoreSeeStatus(storeStatus);
            if (NullUtil.isNotNullOrEmpty(storeStatus) && !record.getStoreSeeStatus()) {
                record1.setStoreSeeTime(new Date());
            }
            orderSeeRecordMapper.updateByExampleSelective(record1, example);
        }
    }

    /**
     * 用户查询订单信息
     *
     * @param orderId
     * @param userId
     * @return
     */
    public OrderInfo selectOrderInfoInfoByUser(Integer orderId, Integer userId) {
        OrderInfoExample example = new OrderInfoExample();
        example.createCriteria().andIdEqualTo(orderId).andUserIdEqualTo(userId);
        List<OrderInfo> list = orderInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 用户查询订单信息
     *
     * @param orderId
     * @param userId
     * @return
     */
    public ViewOrderInfo selectViewOrderInfoByUser(Integer orderId, Integer userId) {
        ViewOrderInfoExample example = new ViewOrderInfoExample();
        example.createCriteria().andIdEqualTo(orderId).andUserIdEqualTo(userId);
        List<ViewOrderInfo> list = viewOrderInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 用户查询订单详情
     *
     * @param orderId
     * @param orderNo
     * @param userId
     * @return
     */
    public ViewOrderDetails selectViewOrderDetailsByUser(Integer appletId, Integer userId, Integer orderId, String orderNo) {
        ViewOrderDetailsExample example = new ViewOrderDetailsExample();
        ViewOrderDetailsExample.Criteria c = example.createCriteria().andUserIdEqualTo(userId);
        if (NullUtil.isNotNullOrEmpty(appletId)) {
            c.andAppletIdEqualTo(appletId);
        }
        if (NullUtil.isNotNullOrEmpty(orderId)) {
            c.andIdEqualTo(orderId);
        }
        if (NullUtil.isNotNullOrEmpty(orderNo)) {
            c.andOrderNoEqualTo(orderNo);
        }
        List<ViewOrderDetails> list = viewOrderDetailsMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    public ViewOrderDetails selectViewOrderDetailsByUser(Integer userId, Integer orderId) {
        return selectViewOrderDetailsByUser(null, userId, orderId, null);
    }

    public ViewOrderDetails selectViewOrderDetailsByUser(Integer userId, String orderNo) {
        return selectViewOrderDetailsByUser(null, userId, null, orderNo);
    }

    /**
     * 查询预备订单
     *
     * @param appletId
     * @param userId
     * @param orderId
     * @return
     */
    public ViewOrderDetails selectViewOrderDetailsByReady(Integer appletId, Integer userId, Integer orderId) {
        ViewOrderDetailsExample example = new ViewOrderDetailsExample();
        example.createCriteria()
                .andIdEqualTo(orderId)
                .andAppletIdEqualTo(appletId)
                .andUserIdEqualTo(userId)
                .andOrderStatusEqualTo(0)
                .andOperateStatusEqualTo(0);
        List<ViewOrderDetails> list = viewOrderDetailsMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }


    /**
     * 商家查询订单详情
     *
     * @param orderId
     * @param appletId
     * @param userId
     * @return
     */
    public ViewOrderInfo selectViewOrderInfoByStore(Integer orderId, Integer appletId, Integer userId) {
        ViewOrderInfoExample example = new ViewOrderInfoExample();
        example.createCriteria().andIdEqualTo(orderId).andAppletIdEqualTo(appletId).andStoreUserIdEqualTo(userId);
        List<ViewOrderInfo> list = viewOrderInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 商家查询订单信息
     *
     * @param orderId
     * @param appletId
     * @return
     */
    public OrderInfo selectOrderInfoInfoByStore(Integer orderId, Integer appletId) {
        OrderInfoExample example = new OrderInfoExample();
        OrderInfoExample.Criteria c = example.createCriteria().andIdEqualTo(orderId).andAppletIdEqualTo(appletId);
        List<OrderInfo> list = orderInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 更新订单信息
     *
     * @param record
     * @param info
     */
    public void updateOrder(OrderRequestRecord record, OrderInfo info) {
        record.setCreateTime(new Date());
        orderRequestRecordMapper.insertSelective(record);
        if (null != info) {
            orderInfoMapper.updateByPrimaryKeySelective(info);
        }
    }

    /**
     * 更新订单信息
     *
     * @param info
     */
    public void updateOrderInfo(OrderInfo info) {
        orderInfoMapper.updateByPrimaryKeySelective(info);
    }

    /**
     * 添加订单操作记录
     *
     * @param record
     */
    public void addOrderOperateRecord(OrderOperateRecord record) {
        orderOperateRecordMapper.insertSelective(record);
    }

    /**
     * 更新订单相关
     *
     * @param order
     * @param record
     * @param operateStatus
     * @param remark
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateOrderRelevant(OrderInfo order, OrderRequestRecord record, Integer operateStatus, String remark) {
        order.setUpdateTime(new Date());
        if (operateStatus.intValue() == OrderEnums.OperateStatus.MAKE.getCode()) {
            // 预下单处理
            if (NullUtil.isNullOrEmpty(order.getId())) {
                order.setCreateTime(new Date());
                orderInfoMapper.insertSelective(order);
            }
        } else if (operateStatus.intValue() == OrderEnums.OperateStatus.LAUNCH_PAY.getCode()) {
            // 发起支付处理
            updateOrderInfo(order);
            // 添加订单支付请求记录
            record.setCreateTime(new Date());
            orderRequestRecordMapper.insertSelective(record);
        } else if (operateStatus.intValue() == OrderEnums.OperateStatus.SUBMIT.getCode()) {
            // 支付成功，订单提交成功处理
            order.setPayStatus(OrderEnums.PayStatus.SUCCESS.getCode());
            updateOrderInfo(order);
            // 添加订单支付回调记录
            record.setCreateTime(new Date());
            orderRequestRecordMapper.insertSelective(record);
            // 更新用户优惠券状态
            if (NullUtil.isNotNullOrEmpty(order.getUserCouponId())) {
                userCouponService.updateUserCouponStatus(order.getUserCouponId(), OrderEnums.UserCouponStatus.USED.getCode());
            }
            // 更新订单查看记录
            updateOrderSeeRecord(order.getId(), true, false);
        } else if (operateStatus.intValue() == OrderEnums.OperateStatus.SUBMIT_FAIL.getCode()) {
            // 支付失败，订单提交失败处理
            order.setPayStatus(OrderEnums.PayStatus.FAIL.getCode());
            updateOrderInfo(order);
            // 添加订单支付回调记录
            record.setCreateTime(new Date());
            orderRequestRecordMapper.insertSelective(record);
        } else if (operateStatus.intValue() == OrderEnums.OperateStatus.MERCHANT_CONFIRM.getCode()) {
            // 商家接单处理
            order.setOrderStatus(OrderEnums.OrderStatus.UNDERWAY.getCode());
            updateOrderInfo(order);
            // 更新订单查看记录
            updateOrderSeeRecord(order.getId(), false, true);
        } else if (operateStatus.intValue() == OrderEnums.OperateStatus.MERCHANT_DELIVERY.getCode()
                || operateStatus.intValue() == OrderEnums.OperateStatus.INSTANT_DELIVERY.getCode()
                || operateStatus.intValue() == OrderEnums.OperateStatus.LOGISTICS_DELIVERY.getCode()
                || operateStatus.intValue() == OrderEnums.OperateStatus.CONFIRM_ARRIVE.getCode()) {
            // 商家配送、即时配送、物流配送、确认送达处理
            // 更新订单查看记录
            updateOrderSeeRecord(order.getId(), false, true);
        } else if (operateStatus.intValue() == OrderEnums.OperateStatus.SIGN_FOR.getCode()) {
            // 订单签收处理
            order.setOrderStatus(OrderEnums.OrderStatus.SUCCESS.getCode());
            updateOrderInfo(order);
            // 更新订单查看记录
            updateOrderSeeRecord(order.getId(), true, false);
            // 检测用户此订单可获取的优惠券
            userCouponService.userGainCoupon(order);
        } else if (operateStatus.intValue() == OrderEnums.OperateStatus.CANCEL.getCode()) {
            // 订单取消处理
            order.setOrderStatus(OrderEnums.OrderStatus.FAIL.getCode());
            updateOrderInfo(order);
            if (NullUtil.isNotNullOrEmpty(remark)){
                // 更新订单查看记录
                updateOrderSeeRecord(order.getId(), false, true);
            }
            // 更新用户优惠券状态
            if (NullUtil.isNotNullOrEmpty(order.getUserCouponId())) {
                userCouponService.updateUserCouponStatus(order.getUserCouponId(), OrderEnums.UserCouponStatus.UNUSED.getCode());
            }

            // 退款功能尚未开发！！！
        } else if (operateStatus.intValue() != OrderEnums.OperateStatus.DELETE.getCode().intValue()) {
            // 订单删除处理
            order.setOrderStatus(OrderEnums.OrderStatus.FAIL.getCode());
            updateOrderInfo(order);
            if (null != record){
                // 添加订单支付请求记录，并更新订单信息
                record.setCreateTime(new Date());
                orderRequestRecordMapper.insertSelective(record);
            }
        }

        OrderOperateRecord operate = new OrderOperateRecord();
        operate.setOrderId(order.getId());
        operate.setOperateUserId(order.getUserId());
        operate.setOperateTime(new Date());
        operate.setOperateRemark(remark);
        operate.setOperateStatus(operateStatus);
        addOrderOperateRecord(operate);
    }

    public void updateOrderRelevant(OrderInfo order, Integer operateStatus, String remark) {
        updateOrderRelevant(order, null, operateStatus, remark);
    }

    public void updateOrderRelevant(OrderInfo order, OrderRequestRecord record, Integer operateStatus) {
        updateOrderRelevant(order, record, operateStatus, null);
    }

    public void updateOrderRelevant(OrderInfo order, Integer operateStatus) {
        updateOrderRelevant(order, null, operateStatus, null);
    }

    /**
     * 统计订单数量 - 用户（消费者）
     *
     * @param userId
     * @return
     */
    public ViewUserOrderCount countUserOrder(Integer userId) {
        if (NullUtil.isNotNullOrEmpty(userId)) {
            ViewUserOrderCountExample example = new ViewUserOrderCountExample();
            example.createCriteria().andUserIdEqualTo(userId);
            List<ViewUserOrderCount> list = viewUserOrderCountMapper.selectByExample(example);
            return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
        } else {
            ViewUserOrderCount count = new ViewUserOrderCount();
            count.setFinishCount(BigDecimal.ZERO);
            count.setIncompleteCount(BigDecimal.ZERO);
            count.setWaitCollectCount(BigDecimal.ZERO);
            count.setWaitMeetCount(BigDecimal.ZERO);
            return count;
        }
    }

    /**
     * 统计订单数量 - 小程序
     *
     * @param appletId
     * @return
     */
    public ViewAppletUserOrderCount countAppletOrder(Integer appletId) {
        ViewAppletUserOrderCountExample example = new ViewAppletUserOrderCountExample();
        example.createCriteria().andAppletIdEqualTo(appletId);
        List<ViewAppletUserOrderCount> list = viewAppletUserOrderCountMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询订单列表 - 商户
     *
     * @param appletId
     * @param userId
     * @param status
     * @param page
     * @return
     */
    public Page selectOrderInfoByStoreToList(Integer appletId, Integer userId, Integer status, Page page) {
        ViewOrderInfoExample example = new ViewOrderInfoExample();
        example.setOrderByClause("store_see_status,store_see_time DESC");
        example.createCriteria()
                .andAppletIdEqualTo(appletId)
                .andStoreUserIdEqualTo(userId)
                .andOperateIdEqualTo(status)
                .andPayStatusEqualTo(1);
        long count = viewOrderInfoMapper.countByExample(example);
        if (count > 0) {
            page.setTotalCount(count);
            page.setDataSource(viewOrderInfoMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 分页查询完成订单列表 - 商户
     *
     * @param appletId
     * @param status
     * @param page
     * @return
     */
    public Page selectOrderInfoByStoreToPage(Integer appletId, Integer userId, Page page) {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6);
        ViewOrderInfoExample example = new ViewOrderInfoExample();
        example.setPage(page);
        example.setOrderByClause("store_see_status,store_see_time DESC");
        example.createCriteria()
                .andAppletIdEqualTo(appletId)
                .andStoreUserIdEqualTo(userId)
                .andOrderStatusIn(list);
        long count = viewOrderInfoMapper.countByExample(example);
        if (count > 0) {
            page.setTotalCount(count);
            page.setDataSource(viewOrderInfoMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 分页查询订单 - 用户
     *
     * @param userId
     * @param statusList
     * @param page
     * @return
     */
    public Page selectOrderInfoByUserToPage(Integer userId, List<Integer> statusList, Page page) {
        ViewOrderInfoExample example = new ViewOrderInfoExample();
        example.setPage(page);
        example.setOrderByClause("user_see_status,user_see_time DESC");
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andOperateStatusIn(statusList);
        long count = viewOrderInfoMapper.countByExample(example);
        if (count > 0) {
            page.setTotalCount(count);
            page.setDataSource(viewOrderInfoMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 加载订单详情商品分类集合
     *
     * @param orderId
     * @return
     */
    public List<Map> loadOrderDetailsByGoodsGroup(Integer orderId) {
        String sql = "SELECT goods_id AS goodsId,goods_name AS goodsName FROM order_details WHERE order_id = " + orderId + " GROUP BY goods_id,goods_name";
        return commonMapper.selectListMap(sql);
    }

    /**
     * 查询订单详情集合
     *
     * @param orderId
     * @return
     */
    public List<OrderDetails> selectOrderDetailsList(Integer orderId) {
        OrderDetailsExample example = new OrderDetailsExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        return orderDetailsMapper.selectByExample(example);
    }

    /**
     * 统计一个小时以内，微信支付请求（同类型请求）发送的次数
     *
     * @param requestType
     * @return
     */
    public long countOrderRequestRecordByHour(String requestType) {
        JDateTime time = new JDateTime(new Date());
        time.addHour(-1);
        OrderRequestRecordExample example = new OrderRequestRecordExample();
        example.createCriteria().andRequestTypeEqualTo(requestType).andCreateTimeGreaterThan(time.convertToDate());
        return orderRequestRecordMapper.countByExample(example);
    }
}
