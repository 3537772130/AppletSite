package com.applet.apply.service;

import com.applet.apply.entity.SaleOrder;
import com.applet.apply.entity.SaleOrderDetails;
import com.applet.apply.entity.SaleOrderDetailsExample;
import com.applet.apply.entity.SaleOrderExample;
import com.applet.apply.mapper.SaleOrderDetailsMapper;
import com.applet.apply.mapper.SaleOrderMapper;
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

    /**
     * 查询订单信息
     * @param orderId
     * @param appletId
     * @param userId
     * @return
     */
    public SaleOrder selectSaleOrderInfo(Integer orderId, Integer appletId, Integer userId){
        SaleOrderExample example = new SaleOrderExample();
        example.createCriteria().andOrderIdEqualTo(orderId).andAppletIdEqualTo(appletId).andUserIdEqualTo(userId);
        List<SaleOrder> list = saleOrderMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 更新订单状态
     * @param id
     * @param status
     */
    public void updateSaleOrderStatus(Integer id, Integer status){
        SaleOrder record = new SaleOrder();
        record.setOrderId(id);
        record.setOrderStatus(status.byteValue());
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
