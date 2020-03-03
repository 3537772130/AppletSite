package com.applet.common.mapper;

import com.applet.common.entity.SaleOrderDoc;
import org.apache.ibatis.annotations.Mapper;

/**
 * 销售订单
 *
 * @author liangzhong.tan
 * @date 2019-12-31 10:11:00
 */
@Mapper
public interface SaleOrderDocMapper extends BaseMapper<SaleOrderDoc> {
    int deleteByPrimaryKey(Integer orderId);

    int insert(SaleOrderDoc record);

    int insertSelective(SaleOrderDoc record);

    SaleOrderDoc selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(SaleOrderDoc record);

    int updateByPrimaryKey(SaleOrderDoc record);
}
