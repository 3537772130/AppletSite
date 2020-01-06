package com.applet.manage.mapper;


import com.applet.manage.entity.SaleOrderDtl;

import java.util.List;

/**
 * 订单详情
 *
 * @author liangzhong.tan
 * @date 2019-12-31 10:11:00
 */
public interface SaleOrderDtlMapper {

    SaleOrderDtl selectByPrimaryKey(Integer orderDtlId);

    /**
     * 根据订单Id查询
     *
     * @param orderId 订单id
     * @return 订单详情
     */
    List<SaleOrderDtl> findByOrderId(Integer orderId);
}