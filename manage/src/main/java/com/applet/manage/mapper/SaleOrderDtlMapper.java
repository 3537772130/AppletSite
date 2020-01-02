package com.applet.manage.mapper;


import com.applet.manage.entity.SaleOrderDtl;

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
}