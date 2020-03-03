package com.applet.common.mapper;

import com.applet.common.entity.ManagerRoleMenu;
import com.applet.common.entity.ManagerRoleMenuExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ManagerRoleMenuMapper {
    long countByExample(ManagerRoleMenuExample example);

    int deleteByExample(ManagerRoleMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ManagerRoleMenu record);

    int insertSelective(ManagerRoleMenu record);

    List<ManagerRoleMenu> selectByExample(ManagerRoleMenuExample example);

    ManagerRoleMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ManagerRoleMenu record, @Param("example") ManagerRoleMenuExample example);

    int updateByExample(@Param("record") ManagerRoleMenu record, @Param("example") ManagerRoleMenuExample example);

    int updateByPrimaryKeySelective(ManagerRoleMenu record);

    int updateByPrimaryKey(ManagerRoleMenu record);
}
