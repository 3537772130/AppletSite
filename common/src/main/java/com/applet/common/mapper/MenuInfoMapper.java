package com.applet.common.mapper;

import com.applet.common.entity.MenuInfo;
import com.applet.common.entity.MenuInfoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MenuInfoMapper {
    long countByExample(MenuInfoExample example);

    int deleteByExample(MenuInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MenuInfo record);

    int insertSelective(MenuInfo record);

    List<MenuInfo> selectByExample(MenuInfoExample example);

    MenuInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MenuInfo record, @Param("example") MenuInfoExample example);

    int updateByExample(@Param("record") MenuInfo record, @Param("example") MenuInfoExample example);

    int updateByPrimaryKeySelective(MenuInfo record);

    int updateByPrimaryKey(MenuInfo record);
}
