package com.applet.manage.mapper;

import com.applet.manage.entity.AppletPageElement;
import com.applet.manage.entity.AppletPageElementExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AppletPageElementMapper {
    long countByExample(AppletPageElementExample example);

    int deleteByExample(AppletPageElementExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppletPageElement record);

    int insertSelective(AppletPageElement record);

    List<AppletPageElement> selectByExample(AppletPageElementExample example);

    AppletPageElement selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppletPageElement record, @Param("example") AppletPageElementExample example);

    int updateByExample(@Param("record") AppletPageElement record, @Param("example") AppletPageElementExample example);

    int updateByPrimaryKeySelective(AppletPageElement record);

    int updateByPrimaryKey(AppletPageElement record);
}
