package com.applet.manage.mapper;

import com.applet.manage.entity.AppletPageContent;
import com.applet.manage.entity.AppletPageContentExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AppletPageContentMapper {
    long countByExample(AppletPageContentExample example);

    int deleteByExample(AppletPageContentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppletPageContent record);

    int insertSelective(AppletPageContent record);

    List<AppletPageContent> selectByExample(AppletPageContentExample example);

    AppletPageContent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppletPageContent record, @Param("example") AppletPageContentExample example);

    int updateByExample(@Param("record") AppletPageContent record, @Param("example") AppletPageContentExample example);

    int updateByPrimaryKeySelective(AppletPageContent record);

    int updateByPrimaryKey(AppletPageContent record);
}
