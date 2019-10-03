package com.applet.user.mapper;

import com.applet.user.entity.ManagerInfo;
import com.applet.user.entity.ManagerInfoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ManagerInfoMapper {
    long countByExample(ManagerInfoExample example);

    int deleteByExample(ManagerInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ManagerInfo record);

    int insertSelective(ManagerInfo record);

    List<ManagerInfo> selectByExample(ManagerInfoExample example);

    ManagerInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ManagerInfo record, @Param("example") ManagerInfoExample example);

    int updateByExample(@Param("record") ManagerInfo record, @Param("example") ManagerInfoExample example);

    int updateByPrimaryKeySelective(ManagerInfo record);

    int updateByPrimaryKey(ManagerInfo record);
}
