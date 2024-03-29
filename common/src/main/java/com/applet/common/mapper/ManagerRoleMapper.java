package com.applet.common.mapper;

import com.applet.common.entity.ManagerRole;
import com.applet.common.entity.ManagerRoleExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ManagerRoleMapper {
    long countByExample(ManagerRoleExample example);

    int deleteByExample(ManagerRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ManagerRole record);

    int insertSelective(ManagerRole record);

    List<ManagerRole> selectByExample(ManagerRoleExample example);

    ManagerRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ManagerRole record, @Param("example") ManagerRoleExample example);

    int updateByExample(@Param("record") ManagerRole record, @Param("example") ManagerRoleExample example);

    int updateByPrimaryKeySelective(ManagerRole record);

    int updateByPrimaryKey(ManagerRole record);
}
