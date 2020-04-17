package com.applet.apply.service;

import com.applet.common.entity.*;
import com.applet.common.mapper.*;
import com.applet.common.enums.OrderEnums;
import com.applet.common.util.EnumUtil;
import com.applet.common.util.NullUtil;
import com.applet.common.util.Page;
import com.applet.common.util.encryption.EncryptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private OrderDetailsMapper orderDetailsMapper;
    @Autowired
    private OrderRequestRecordMapper orderRequestRecordMapper;
    @Autowired
    private OrderSeeRecordMapper orderSeeRecordMapper;
    @Autowired
    private ViewOrderInfoMapper viewOrderInfoMapper;
    @Autowired
    private ViewUserOrderCountMapper viewUserOrderCountMapper;
    @Autowired
    private ViewStoreUserOrderCountMapper viewStoreUserOrderCountMapper;
    @Autowired
    private UserCartService userCartService;
    @Autowired
    private CommonMapper commonMapper;

    /**
     * 添加订单信息，并更新相关记录
     * @param info
     * @param list
     * @param cartIdList
     */
    @Transactional(rollbackFor = Exception.class)
    public void addOrderInfo(OrderInfo info, List<OrderDetails> list, List<Integer> cartIdList) {
        info.setCreateTime(new Date());
        info.setUpdateTime(new Date());
        orderInfoMapper.insertSelective(info);
        // 批量插入订单商品详情信息
        list.forEach(it -> it.setOrderId(info.getId()));
        orderDetailsMapper.batchInsert(list);
        if (NullUtil.isNotNullOrEmpty(info.getUserCouponId())) {
            // 更新优惠券状态
            userCouponService.updateUserCouponStatus(info.getUserCouponId(), OrderEnums.UserCouponStatus.USING.getCode());
        }
        // 更新购物车状态
        userCartService.updateUserCartStatus(info.getId(), info.getAppletId(), info.getWxId(), cartIdList);
    }

    /**
     * 查询订单信息
     * @param id
     * @return
     */
    public OrderInfo selectOrderInfoById(Integer id){
        return orderInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询订单信息
     * @param id
     * @param appletId
     * @param userId
     * @return
     */
    public OrderInfo selectOrderInfo(Integer id, Integer appletId, Integer userId){
        OrderInfoExample example = new OrderInfoExample();
        example.createCriteria().andIdEqualTo(id).andAppletIdEqualTo(appletId).andUserIdEqualTo(userId);
        List<OrderInfo> list = orderInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 根据支付关联标识查询在线支付尚未付款订单
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
     * 更新订单支付状态
     * @param id
     * @param payStatus
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateOrderInfo(Integer id, String payRelationId, String payChannel, boolean bool){
        OrderInfo order = new OrderInfo();
        order.setId(id);
        order.setPayRelationId(payRelationId);
        order.setPayChannel(payChannel);
        // 统一下单成功和余额不足以外的状况，则订单不再继续，支付失败
        if (!bool){
            order.setPayStatus(-1);
            order.setOrderStatus(-1);
        }
        orderInfoMapper.updateByPrimaryKeySelective(order);
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
    @Async
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
    @Async
    public void updateOrderSeeRecord(Integer orderId, Boolean userStatus, Boolean storeStatus) {
        OrderSeeRecord record = selectOrderSeeRecord(orderId);

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
     * 用户查询订单详情
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
     * @param orderNo
     * @param userId
     * @return
     */
    public ViewOrderInfo selectViewOrderInfoByUser(String orderNo, Integer userId) {
        ViewOrderInfoExample example = new ViewOrderInfoExample();
        example.createCriteria().andOrderNoEqualTo(orderNo).andUserIdEqualTo(userId);
        List<ViewOrderInfo> list = viewOrderInfoMapper.selectByExample(example);
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
     * 更新订单状态
     *
     * @param id
     * @param status
     */
    public void updateOrderInfoStatus(Integer id, Integer status, String remark) {
        OrderInfo record = new OrderInfo();
        record.setId(id);
        record.setOrderStatus(status);
        if (status.intValue() == 3){
            record.setStoreRemark(remark);
        }
        record.setUpdateTime(new Date());
        orderInfoMapper.updateByPrimaryKeySelective(record);

        updateOrderSeeRecord(id, false, false);
    }

    public void updateOrderInfoStatus(Integer id, Integer status) {
        updateOrderInfoStatus(id, status, null);
    }

    /**
     * 统计订单数量 - 用户（消费者）
     *
     * @param userId
     * @return
     */
    public ViewUserOrderCount countUserOrder(Integer userId) {
        ViewUserOrderCountExample example = new ViewUserOrderCountExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<ViewUserOrderCount> list = viewUserOrderCountMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 统计订单数量 - 商户
     *
     * @param userId
     * @return
     */
    public ViewStoreUserOrderCount countStoreOrder(Integer userId) {
        ViewStoreUserOrderCountExample example = new ViewStoreUserOrderCountExample();
        example.createCriteria().andStoreUserIdEqualTo(userId);
        List<ViewStoreUserOrderCount> list = viewStoreUserOrderCountMapper.selectByExample(example);
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
                .andOrderStatusEqualTo(status);
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
        ViewOrderInfoExample.Criteria c = example.createCriteria().andStoreUserIdEqualTo(userId).andOrderStatusIn(statusList);
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
     * 添加订单请求记录
     * @param record
     * @throws Exception
     */
    public void addOrderRequestRecord(OrderRequestRecord record) {
        try {
            record.setRequestMsg(EncryptionUtil.encryptAppletRSA(record.getRequestMsg()));
        } catch (Exception e) {
            log.error("订单请求报文加密出错{}", e);
        }
        record.setCreateTime(new Date());
        orderRequestRecordMapper.insertSelective(record);
    }

}
