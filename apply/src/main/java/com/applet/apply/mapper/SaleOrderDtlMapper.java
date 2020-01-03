package com.applet.apply.mapper;


import com.applet.apply.entity.SaleOrderDtl;

import java.util.List;

/**
 * 订单详情
 *
 * @author liangzhong.tan
 * @date 2019-12-31 10:11:00
 */
public interface SaleOrderDtlMapper {
    int deleteByPrimaryKey(Integer orderDtlId);

    int insert(SaleOrderDtl record);

    int insertSelective(SaleOrderDtl record);

    SaleOrderDtl selectByPrimaryKey(Integer orderDtlId);

    int updateByPrimaryKeySelective(SaleOrderDtl record);

    int updateByPrimaryKey(SaleOrderDtl record);

    /**
     * 根据订单Id查询
     *
     * @param orderId 订单id
     * @return 订单详情
     */
    List<SaleOrderDtl> findByOrderId(Integer orderId);
}