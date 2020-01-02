package com.applet.manage.mapper;

import com.applet.common.mapper.BaseMapper;
import com.applet.manage.entity.SaleOrderDoc;

/**
 * 销售订单
 *
 * @author liangzhong.tan
 * @date 2019-12-31 10:11:00
 */
public interface SaleOrderDocMapper extends BaseMapper<SaleOrderDoc> {
    int deleteByPrimaryKey(Integer orderId);

    int insert(SaleOrderDoc record);

    int insertSelective(SaleOrderDoc record);

    SaleOrderDoc selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(SaleOrderDoc record);

    int updateByPrimaryKey(SaleOrderDoc record);
}