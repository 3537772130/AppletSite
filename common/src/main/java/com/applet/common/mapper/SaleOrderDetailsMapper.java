package com.applet.common.mapper;

import com.applet.common.entity.SaleOrderDetails;
import com.applet.common.entity.SaleOrderDetailsExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SaleOrderDetailsMapper {
    long countByExample(SaleOrderDetailsExample example);

    int deleteByExample(SaleOrderDetailsExample example);

    int deleteByPrimaryKey(Integer orderDtlId);

    int insert(SaleOrderDetails record);

    int insertSelective(SaleOrderDetails record);

    List<SaleOrderDetails> selectByExample(SaleOrderDetailsExample example);

    SaleOrderDetails selectByPrimaryKey(Integer orderDtlId);

    int updateByExampleSelective(@Param("record") SaleOrderDetails record, @Param("example") SaleOrderDetailsExample example);

    int updateByExample(@Param("record") SaleOrderDetails record, @Param("example") SaleOrderDetailsExample example);

    int updateByPrimaryKeySelective(SaleOrderDetails record);

    int updateByPrimaryKey(SaleOrderDetails record);
}
