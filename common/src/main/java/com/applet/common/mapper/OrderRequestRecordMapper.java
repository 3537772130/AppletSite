package com.applet.common.mapper;

import com.applet.common.entity.OrderRequestRecord;
import com.applet.common.entity.OrderRequestRecordExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderRequestRecordMapper {
    long countByExample(OrderRequestRecordExample example);

    int deleteByExample(OrderRequestRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderRequestRecord record);

    int insertSelective(OrderRequestRecord record);

    List<OrderRequestRecord> selectByExample(OrderRequestRecordExample example);

    OrderRequestRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderRequestRecord record, @Param("example") OrderRequestRecordExample example);

    int updateByExample(@Param("record") OrderRequestRecord record, @Param("example") OrderRequestRecordExample example);

    int updateByPrimaryKeySelective(OrderRequestRecord record);

    int updateByPrimaryKey(OrderRequestRecord record);
}
