package com.applet.apply.service;

import com.applet.apply.entity.*;
import com.applet.apply.mapper.OrderSeeMapper;
import com.applet.apply.mapper.SaleOrderDetailsMapper;
import com.applet.apply.mapper.SaleOrderMapper;
import com.applet.apply.mapper.ViewOrderInfoMapper;
import com.applet.common.enums.OrderEnums;
import com.applet.common.util.EnumUtil;
import com.applet.common.util.NullUtil;
import com.applet.common.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    private OrderSeeMapper orderSeeMapper;
    @Autowired
    private ViewOrderInfoMapper viewOrderInfoMapper;

    /**
     * 查询订单查看记录
     * @param orderId
     * @return
     */
    public OrderSee selectOrderSee(Integer orderId){
        OrderSeeExample example = new OrderSeeExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        List<OrderSee> list = orderSeeMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 添加订单查看记录
     * @param orderId
     */
    public void addOrderSee(Integer orderId){
        OrderSee record = selectOrderSee(orderId);
        if (null == record){
            record = new OrderSee();
            record.setOrderId(orderId);
            record.setUserStatus(false);
            record.setStoreStatus(false);
            orderSeeMapper.insertSelective(record);
        }
    }

    /**
     * 更新订单查看记录
     * @param orderId
     * @param userStatus
     * @param storeStatus
     */
    public void updateOrderSee(Integer orderId, Boolean userStatus, Boolean storeStatus){
        OrderSeeExample example = new OrderSeeExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        OrderSee record = new OrderSee();
        record.setUserStatus(userStatus);
        record.setStoreStatus(storeStatus);
        orderSeeMapper.updateByExampleSelective(record, example);
    }

    /**
     * 用户查询订单信息
     * @param orderId
     * @param userId
     * @return
     */
    public SaleOrder selectSaleOrderInfoByUser(Integer orderId, Integer userId){
        SaleOrderExample example = new SaleOrderExample();
        SaleOrderExample.Criteria c = example.createCriteria().andOrderIdEqualTo(orderId).andUserIdEqualTo(userId);
        List<SaleOrder> list = saleOrderMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 商家查询订单信息
     * @param orderId
     * @param appletId
     * @return
     */
    public SaleOrder selectSaleOrderInfoByBusiness(Integer orderId, Integer appletId){
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
        saleOrderMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 统计订单数量
     * @param appletId
     * @return
     */
    public long countOrder(Integer appletId, Integer status){
        SaleOrderExample example = new SaleOrderExample();
        example.createCriteria().andAppletIdEqualTo(appletId).andOrderStatusEqualTo(status.byteValue());
        return saleOrderMapper.countByExample(example);
    }

    /**
     * 分页查询订单 - 商户
     * @param appletId
     * @param status
     * @param page
     * @return
     */
    public Page selectSaleOrderByBusinessToPage(Integer appletId, Integer status, Page page){
        SaleOrderExample example = new SaleOrderExample();
        example.setPage(page);
        if (status.intValue() == 1){
            example.setOrderByClause("gmt_created desc");
        } else {
            example.setOrderByClause("gmt_modified desc");
        }
        SaleOrderExample.Criteria c = example.createCriteria().andAppletIdEqualTo(appletId);
        if (status.intValue() == 2){
            List<Byte> list = new ArrayList<>();
            status = 2;
            list.add(status.byteValue());
            status = 4;
            list.add(status.byteValue());
            c.andOrderStatusIn(list);
        } else if (status.intValue() == 3){
            List<Byte> list = new ArrayList<>();
            status = 5;
            list.add(status.byteValue());
            status = 6;
            list.add(status.byteValue());
            c.andOrderStatusIn(list);
        } else {
            c.andOrderStatusEqualTo(status.byteValue());
        }
        long count = saleOrderMapper.countByExample(example);
        if (count > 0){
            page.setTotalCount(count);
            page.setDataSource(saleOrderMapper.selectByExample(example));
        }
        return page;
    }

    public Page selectSaleOrderByStoreToPage(Integer appletId, Integer userId, Integer status, Page page){
        ViewOrderInfoExample example = new ViewOrderInfoExample();
        example.setPage(page);
        example.setOrderByClause("store_status,create_time DESC");
        ViewOrderInfoExample.Criteria c = example.createCriteria().andAppletIdEqualTo(appletId).andStoreUserIdEqualTo(userId);
        if (status.intValue() == 2){
            List<Byte> list = new ArrayList<>();
            status = 2;
            list.add(status.byteValue());
            status = 4;
            list.add(status.byteValue());
            c.andOrderStatusIn(list);
        } else if (status.intValue() == 3){
            List<Byte> list = new ArrayList<>();
            status = 5;
            list.add(status.byteValue());
            status = 6;
            list.add(status.byteValue());
            c.andOrderStatusIn(list);
        } else {
            c.andOrderStatusEqualTo(status.byteValue());
        }
        long count = viewOrderInfoMapper.countByExample(example);
        if (count > 0){
            page.setTotalCount(count);
            page.setDataSource(viewOrderInfoMapper.selectByExample(example));
        }
        return page;
    }

    public Page selectSaleOrderByUserToPage(Integer userId, Page page){
        ViewOrderInfoExample example = new ViewOrderInfoExample();
        example.setPage(page);
        example.setOrderByClause("user_status,update_time DESC");
        ViewOrderInfoExample.Criteria c = example.createCriteria().andStoreUserIdEqualTo(userId);
        long count = viewOrderInfoMapper.countByExample(example);
        if (count > 0){
            page.setTotalCount(count);
            page.setDataSource(viewOrderInfoMapper.selectByExample(example));
        }
        return page;
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
