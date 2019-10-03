package com.applet.manage.mapper;

import com.applet.manage.entity.AppletType;
import com.applet.manage.entity.AppletTypeExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppletTypeMapper {
    long countByExample(AppletTypeExample example);

    int deleteByExample(AppletTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppletType record);

    int insertSelective(AppletType record);

    List<AppletType> selectByExample(AppletTypeExample example);

    AppletType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppletType record, @Param("example") AppletTypeExample example);

    int updateByExample(@Param("record") AppletType record, @Param("example") AppletTypeExample example);

    int updateByPrimaryKeySelective(AppletType record);

    int updateByPrimaryKey(AppletType record);
}
