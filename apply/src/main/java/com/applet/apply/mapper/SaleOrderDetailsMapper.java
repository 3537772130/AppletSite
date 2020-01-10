package com.applet.apply.mapper;

import com.applet.apply.entity.SaleOrderDetails;
import com.applet.apply.entity.SaleOrderDetailsExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
