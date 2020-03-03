package com.applet.common.mapper;

import com.applet.common.entity.OrderSeeRecord;
import com.applet.common.entity.OrderSeeRecordExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderSeeRecordMapper {
    long countByExample(OrderSeeRecordExample example);

    int deleteByExample(OrderSeeRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderSeeRecord record);

    int insertSelective(OrderSeeRecord record);

    List<OrderSeeRecord> selectByExample(OrderSeeRecordExample example);

    OrderSeeRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderSeeRecord record, @Param("example") OrderSeeRecordExample example);

    int updateByExample(@Param("record") OrderSeeRecord record, @Param("example") OrderSeeRecordExample example);

    int updateByPrimaryKeySelective(OrderSeeRecord record);

    int updateByPrimaryKey(OrderSeeRecord record);
}
