package com.applet.common.mapper;

import com.applet.common.entity.AppletPageElementType;
import com.applet.common.entity.AppletPageElementTypeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AppletPageElementTypeMapper {
    long countByExample(AppletPageElementTypeExample example);

    int deleteByExample(AppletPageElementTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppletPageElementType record);

    int insertSelective(AppletPageElementType record);

    List<AppletPageElementType> selectByExample(AppletPageElementTypeExample example);

    AppletPageElementType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppletPageElementType record, @Param("example") AppletPageElementTypeExample example);

    int updateByExample(@Param("record") AppletPageElementType record, @Param("example") AppletPageElementTypeExample example);

    int updateByPrimaryKeySelective(AppletPageElementType record);

    int updateByPrimaryKey(AppletPageElementType record);
}
