package com.applet.apply.mapper;

import com.applet.apply.entity.OrderSee;
import com.applet.apply.entity.OrderSeeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderSeeMapper {
    long countByExample(OrderSeeExample example);

    int deleteByExample(OrderSeeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderSee record);

    int insertSelective(OrderSee record);

    List<OrderSee> selectByExample(OrderSeeExample example);

    OrderSee selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderSee record, @Param("example") OrderSeeExample example);

    int updateByExample(@Param("record") OrderSee record, @Param("example") OrderSeeExample example);

    int updateByPrimaryKeySelective(OrderSee record);

    int updateByPrimaryKey(OrderSee record);
}
