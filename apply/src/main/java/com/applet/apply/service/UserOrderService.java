package com.applet.apply.service;

import com.applet.common.entity.*;
import com.applet.common.mapper.*;
import com.applet.common.enums.OrderEnums;
import com.applet.common.util.EnumUtil;
import com.applet.common.util.NullUtil;
import com.applet.common.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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
@Service
public class UserOrderService {
    @Autowired
    private SaleOrderMapper saleOrderMapper;
    @Autowired
    private SaleOrderDetailsMapper saleOrderDetailsMapper;
    @Autowired
    private OrderSeeRecordMapper orderSeeRecordMapper;
    @Autowired
    private ViewOrderInfoMapper viewOrderInfoMapper;
    @Autowired
    private ViewStoreUserOrderCountMapper viewStoreUserOrderCountMapper;
    @Autowired
    private CommonMapper commonMapper;

    /**
     * 查询订单查看记录
     * @param orderId
     * @return
     */
    public OrderSeeRecord selectOrderSeeRecord(Integer orderId){
        OrderSeeRecordExample example = new OrderSeeRecordExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        List<OrderSeeRecord> list = orderSeeRecordMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 添加订单查看记录
     * @param orderId
     */
    @Async
    public void addOrderSeeRecord(Integer orderId){
        OrderSeeRecord record = selectOrderSeeRecord(orderId);
        if (null == record){
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
     * @param orderId
     * @param userStatus
     * @param storeStatus
     */
    @Async
    public void updateOrderSeeRecord(Integer orderId, Boolean userStatus, Boolean storeStatus){
        OrderSeeRecord record = selectOrderSeeRecord(orderId);

        OrderSeeRecordExample example = new OrderSeeRecordExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        OrderSeeRecord record1 = new OrderSeeRecord();
        record1.setUserSeeStatus(userStatus);
        if (NullUtil.isNotNullOrEmpty(userStatus) && !record.getUserSeeStatus()){
            record1.setUserSeeTime(new Date());
        }
        record1.setStoreSeeStatus(storeStatus);
        if (NullUtil.isNotNullOrEmpty(storeStatus) && !record.getStoreSeeStatus()){
            record1.setStoreSeeTime(new Date());
        }
        orderSeeRecordMapper.updateByExampleSelective(record1, example);
    }

    /**
     * 用户查询订单信息
     * @param orderId
     * @param userId
     * @return
     */
    public SaleOrder selectSaleOrderInfoByUser(Integer orderId, Integer userId){
        SaleOrderExample example = new SaleOrderExample();
        example.createCriteria().andOrderIdEqualTo(orderId).andUserIdEqualTo(userId);
        List<SaleOrder> list = saleOrderMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 用户查询订单详情
     * @param orderId
     * @param userId
     * @return
     */
    public ViewOrderInfo selectViewOrderInfoByUser(Integer orderId, Integer userId){
        ViewOrderInfoExample example = new ViewOrderInfoExample();
        example.createCriteria().andIdEqualTo(orderId).andUserIdEqualTo(userId);
        List<ViewOrderInfo> list = viewOrderInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 商家查询订单详情
     * @param orderId
     * @param appletId
     * @param userId
     * @return
     */
    public ViewOrderInfo selectViewOrderInfoByStore(Integer orderId, Integer appletId, Integer userId){
        ViewOrderInfoExample example = new ViewOrderInfoExample();
        example.createCriteria().andIdEqualTo(orderId).andAppletIdEqualTo(appletId).andStoreUserIdEqualTo(userId);
        List<ViewOrderInfo> list = viewOrderInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 商家查询订单信息
     * @param orderId
     * @param appletId
     * @return
     */
    public SaleOrder selectSaleOrderInfoByStore(Integer orderId, Integer appletId){
        SaleOrderExample example = new SaleOrderExample();
        SaleOrderExample.Criteria c = example.createCriteria().andOrderIdEqualTo(orderId).andAppletIdEqualTo(appletId);
        List<SaleOrder> list = saleOrderMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 更新订单状态
     * @param id
     * @param status
     */
    public void updateSaleOrderStatus(Integer id, Integer status, String remark){
        OrderEnums.OrderStatus orderStatus = EnumUtil.getEnumByCode(status.byteValue(), OrderEnums.OrderStatus.class);
        SaleOrder record = new SaleOrder();
        record.setOrderId(id);
        record.setOrderStatus(status.byteValue());
        record.setOrderStatusCn(orderStatus.getName());
        record.setDenialReason(remark);
        record.setGmtModified(new Date());
        saleOrderMapper.updateByPrimaryKeySelective(record);

        updateOrderSeeRecord(id, false, false);
    }

    /**
     * 统计订单数量
     * @param appletId
     * @return
     */
    public ViewStoreUserOrderCount countOrder(Integer userId){
        ViewStoreUserOrderCountExample example = new ViewStoreUserOrderCountExample();
        example.createCriteria().andStoreUserIdEqualTo(userId);
        List<ViewStoreUserOrderCount> list = viewStoreUserOrderCountMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询订单列表 - 商户
     * @param appletId
     * @param userId
     * @param status
     * @param page
     * @return
     */
    public Page selectSaleOrderByStoreToList(Integer appletId, Integer userId, Integer status, Page page){
        ViewOrderInfoExample example = new ViewOrderInfoExample();
        example.setOrderByClause("store_see_status,store_see_time DESC");
        example.createCriteria()
                .andAppletIdEqualTo(appletId)
                .andStoreUserIdEqualTo(userId)
                .andOrderStatusEqualTo(status.byteValue());
        long count = viewOrderInfoMapper.countByExample(example);
        if (count > 0){
            page.setTotalCount(count);
            page.setDataSource(viewOrderInfoMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 分页查询完成订单列表 - 商户
     * @param appletId
     * @param status
     * @param page
     * @return
     */
    public Page selectSaleOrderByStoreToPage(Integer appletId, Integer userId, Page page){
        List<Byte> list = new ArrayList<>();
        Integer status = 5;
        list.add(status.byteValue());
        status = 6;
        list.add(status.byteValue());
        ViewOrderInfoExample example = new ViewOrderInfoExample();
        example.setPage(page);
        example.setOrderByClause("store_see_status,store_see_time DESC");
        example.createCriteria()
                .andAppletIdEqualTo(appletId)
                .andStoreUserIdEqualTo(userId)
                .andOrderStatusIn(list);
        long count = viewOrderInfoMapper.countByExample(example);
        if (count > 0){
            page.setTotalCount(count);
            page.setDataSource(viewOrderInfoMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 分页查询订单 - 用户
     * @param userId
     * @param page
     * @return
     */
    public Page selectSaleOrderByUserToPage(Integer userId, Page page){
        ViewOrderInfoExample example = new ViewOrderInfoExample();
        example.setPage(page);
        example.setOrderByClause("user_see_status,user_see_time DESC");
        ViewOrderInfoExample.Criteria c = example.createCriteria().andStoreUserIdEqualTo(userId);
        long count = viewOrderInfoMapper.countByExample(example);
        if (count > 0){
            page.setTotalCount(count);
            page.setDataSource(viewOrderInfoMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 加载订单详情商品分类集合
     * @param orderId
     * @return
     */
    public List<Map> loadSaleOrderDetailsByGoodsGroup(Integer orderId){
        String sql = "SELECT goods_id AS goodsId,goods_name AS goodsName FROM sale_order_dtl WHERE order_id = " + orderId + " GROUP BY goods_id,goods_name";
        return commonMapper.selectListMap(sql);
    }

    /**
     * 查询订单详情集合
     * @param orderId
     * @return
     */
    public List<SaleOrderDetails> selectSaleOrderDetailsList(Integer orderId){
        SaleOrderDetailsExample example = new SaleOrderDetailsExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        return saleOrderDetailsMapper.selectByExample(example);
    }

}
