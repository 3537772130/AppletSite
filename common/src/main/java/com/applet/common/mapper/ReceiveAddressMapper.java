package com.applet.common.mapper;

import com.applet.common.entity.ReceiveAddress;
import com.applet.common.entity.ReceiveAddressExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReceiveAddressMapper {
    long countByExample(ReceiveAddressExample example);

    int deleteByExample(ReceiveAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReceiveAddress record);

    int insertSelective(ReceiveAddress record);

    List<ReceiveAddress> selectByExample(ReceiveAddressExample example);

    ReceiveAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReceiveAddress record, @Param("example") ReceiveAddressExample example);

    int updateByExample(@Param("record") ReceiveAddress record, @Param("example") ReceiveAddressExample example);

    int updateByPrimaryKeySelective(ReceiveAddress record);

    int updateByPrimaryKey(ReceiveAddress record);
}
