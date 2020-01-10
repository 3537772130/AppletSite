package com.applet.apply.mapper;


import com.applet.apply.entity.SaleOrderDtl;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 订单详情
 *
 * @author liangzhong.tan
 * @date 2019-12-31 10:11:00
 */
@Mapper
public interface SaleOrderDtlMapper {

    SaleOrderDtl selectByPrimaryKey(Integer orderDtlId);

    /**
     * 根据订单Id查询
     *
     * @param orderId 订单id
     * @return 订单详情
     */
    List<SaleOrderDtl> findByOrderId(Integer orderId);


    /**
     * 批量新增
     *
     * @param list
     * @return
     */
    int batchInsert(List<SaleOrderDtl> list);
}