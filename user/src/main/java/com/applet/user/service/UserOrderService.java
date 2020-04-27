package com.applet.user.service;

import com.applet.common.entity.*;
import com.applet.common.mapper.*;
import com.applet.common.mapper.ViewOrderInfoMapper;
import com.applet.common.util.NullUtil;
import com.applet.common.util.Page;
import jodd.datetime.JDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2020/4/27
 * Time: 14:51
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@SuppressWarnings("ALL")
@Slf4j
@Service
public class UserOrderService {
    @Autowired
    private ViewOrderInfoMapper viewOrderInfoMapper;
    @Autowired
    private OrderDetailsMapper orderDetailsMapper;
    @Autowired
    private OrderOperateRecordMapper orderOperateRecordMapper;
    @Autowired
    private OrderReceiverMapper orderReceiverMapper;

    /**
     * 分页查询用户订单列表
     * @param userId
     * @param orderNo
     * @param startTime
     * @param overTime
     * @param page
     * @return
     */
    public Page selectUserOrderInfoByPage(Integer userId, String orderNo, String startTime, String overTime, Page page){
        ViewOrderInfoExample example = new ViewOrderInfoExample();
        example.setPage(page);
        example.setOrderByClause("create_time");
        ViewOrderInfoExample.Criteria c = example.createCriteria().andUserIdEqualTo(userId);
        if (NullUtil.isNotNullOrEmpty(orderNo)){
            c.andOrderNoLike("%" + orderNo + "%");
        }
        if (NullUtil.isNotNullOrEmpty(startTime)){
            JDateTime time = new JDateTime(startTime);
            c.andCreateTimeGreaterThanOrEqualTo(time.convertToDate());
        }
        if (NullUtil.isNotNullOrEmpty(overTime)){
            JDateTime time = new JDateTime(overTime);
            c.andCreateTimeLessThanOrEqualTo(time.convertToDate());
        }
        long count = viewOrderInfoMapper.countByExample(example);
        if (count > 0){
            page.setTotalCount(count);
            page.setDataSource(viewOrderInfoMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 用户查询订单详情
     * @param id
     * @param userId
     * @return
     */
    public ViewOrderInfo selectUserOrderInfoById(Integer id, Integer userId){
        ViewOrderInfoExample example = new ViewOrderInfoExample();
        example.createCriteria().andIdEqualTo(id).andUserIdEqualTo(userId);
        List<ViewOrderInfo> list = viewOrderInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 分页查询商户订单列表
     * @param userId
     * @param orderNo
     * @param startTime
     * @param overTime
     * @param page
     * @return
     */
    public Page selectStoreOrderInfoByPage(Integer userId, String orderNo, String startTime, String overTime, Page page){
        ViewOrderInfoExample example = new ViewOrderInfoExample();
        example.setPage(page);
        example.setOrderByClause("create_time");
        ViewOrderInfoExample.Criteria c = example.createCriteria().andStoreUserIdEqualTo(userId);
        if (NullUtil.isNotNullOrEmpty(orderNo)){
            c.andOrderNoLike("%" + orderNo + "%");
        }
        if (NullUtil.isNotNullOrEmpty(startTime)){
            JDateTime time = new JDateTime(startTime);
            c.andCreateTimeGreaterThanOrEqualTo(time.convertToDate());
        }
        if (NullUtil.isNotNullOrEmpty(overTime)){
            JDateTime time = new JDateTime(overTime);
            c.andCreateTimeLessThanOrEqualTo(time.convertToDate());
        }
        long count = viewOrderInfoMapper.countByExample(example);
        if (count > 0){
            page.setTotalCount(count);
            page.setDataSource(viewOrderInfoMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 商户查询订单详情
     * @param id
     * @param userId
     * @return
     */
    public ViewOrderInfo selectStoreOrderInfoById(Integer id, Integer userId){
        ViewOrderInfoExample example = new ViewOrderInfoExample();
        example.createCriteria().andIdEqualTo(id).andStoreUserIdEqualTo(userId);
        List<ViewOrderInfo> list = viewOrderInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询订单商品详情集合
     * @param orderId
     * @return
     */
    public List<OrderDetails> selectUserOrderInfoListByOrderId(Integer orderId){
        OrderDetailsExample example = new OrderDetailsExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        return orderDetailsMapper.selectByExample(example);
    }

    /**
     * 查询订单收货人信息
     * @param orderId
     * @return
     */
    public OrderReceiver selectOrderReceiverByOrderId(Integer orderId){
        OrderReceiverExample example = new OrderReceiverExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        List<OrderReceiver> list = orderReceiverMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询订单操作记录
     * @param orderId
     * @return
     */
    public List<OrderOperateRecord> selectOrderOperateRecordByOrderId(Integer orderId){
        OrderOperateRecordExample example = new OrderOperateRecordExample();
        example.setOrderByClause("operate_time desc");
        example.createCriteria().andOrderIdEqualTo(orderId);
        return orderOperateRecordMapper.selectByExample(example);
    }

}
