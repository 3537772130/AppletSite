package com.applet.common.mapper;

import com.applet.common.entity.OrderReceiver;
import com.applet.common.entity.OrderReceiverExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderReceiverMapper {
    long countByExample(OrderReceiverExample example);

    int deleteByExample(OrderReceiverExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderReceiver record);

    int insertSelective(OrderReceiver record);

    List<OrderReceiver> selectByExample(OrderReceiverExample example);

    OrderReceiver selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderReceiver record, @Param("example") OrderReceiverExample example);

    int updateByExample(@Param("record") OrderReceiver record, @Param("example") OrderReceiverExample example);

    int updateByPrimaryKeySelective(OrderReceiver record);

    int updateByPrimaryKey(OrderReceiver record);
}
