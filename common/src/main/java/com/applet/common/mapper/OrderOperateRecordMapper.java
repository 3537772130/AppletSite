package com.applet.common.mapper;

import com.applet.common.entity.OrderOperateRecord;
import com.applet.common.entity.OrderOperateRecordExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderOperateRecordMapper {
    long countByExample(OrderOperateRecordExample example);

    int deleteByExample(OrderOperateRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderOperateRecord record);

    int insertSelective(OrderOperateRecord record);

    List<OrderOperateRecord> selectByExample(OrderOperateRecordExample example);

    OrderOperateRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderOperateRecord record, @Param("example") OrderOperateRecordExample example);

    int updateByExample(@Param("record") OrderOperateRecord record, @Param("example") OrderOperateRecordExample example);

    int updateByPrimaryKeySelective(OrderOperateRecord record);

    int updateByPrimaryKey(OrderOperateRecord record);
}
