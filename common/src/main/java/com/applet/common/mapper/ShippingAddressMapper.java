package com.applet.common.mapper;

import com.applet.common.entity.ShippingAddress;
import com.applet.common.entity.ShippingAddressExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ShippingAddressMapper {
    long countByExample(ShippingAddressExample example);

    int deleteByExample(ShippingAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShippingAddress record);

    int insertSelective(ShippingAddress record);

    List<ShippingAddress> selectByExample(ShippingAddressExample example);

    ShippingAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShippingAddress record, @Param("example") ShippingAddressExample example);

    int updateByExample(@Param("record") ShippingAddress record, @Param("example") ShippingAddressExample example);

    int updateByPrimaryKeySelective(ShippingAddress record);

    int updateByPrimaryKey(ShippingAddress record);
}
