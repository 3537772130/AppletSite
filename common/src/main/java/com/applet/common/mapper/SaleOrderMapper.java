package com.applet.common.mapper;

import com.applet.common.entity.SaleOrder;
import com.applet.common.entity.SaleOrderExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SaleOrderMapper {
    long countByExample(SaleOrderExample example);

    int deleteByExample(SaleOrderExample example);

    int deleteByPrimaryKey(Integer orderId);

    int insert(SaleOrder record);

    int insertSelective(SaleOrder record);

    List<SaleOrder> selectByExample(SaleOrderExample example);

    SaleOrder selectByPrimaryKey(Integer orderId);

    int updateByExampleSelective(@Param("record") SaleOrder record, @Param("example") SaleOrderExample example);

    int updateByExample(@Param("record") SaleOrder record, @Param("example") SaleOrderExample example);

    int updateByPrimaryKeySelective(SaleOrder record);

    int updateByPrimaryKey(SaleOrder record);
}
