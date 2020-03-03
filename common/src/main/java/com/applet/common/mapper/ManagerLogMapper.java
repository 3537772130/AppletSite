package com.applet.common.mapper;

import com.applet.common.entity.ManagerLog;
import com.applet.common.entity.ManagerLogExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ManagerLogMapper {
    long countByExample(ManagerLogExample example);

    int deleteByExample(ManagerLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ManagerLog record);

    int insertSelective(ManagerLog record);

    List<ManagerLog> selectByExample(ManagerLogExample example);

    ManagerLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ManagerLog record, @Param("example") ManagerLogExample example);

    int updateByExample(@Param("record") ManagerLog record, @Param("example") ManagerLogExample example);

    int updateByPrimaryKeySelective(ManagerLog record);

    int updateByPrimaryKey(ManagerLog record);
}
