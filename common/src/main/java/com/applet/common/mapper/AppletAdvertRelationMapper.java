package com.applet.common.mapper;

import com.applet.common.entity.AppletAdvertRelation;
import com.applet.common.entity.AppletAdvertRelationExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AppletAdvertRelationMapper {
    long countByExample(AppletAdvertRelationExample example);

    int deleteByExample(AppletAdvertRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppletAdvertRelation record);

    int insertSelective(AppletAdvertRelation record);

    List<AppletAdvertRelation> selectByExample(AppletAdvertRelationExample example);

    AppletAdvertRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppletAdvertRelation record, @Param("example") AppletAdvertRelationExample example);

    int updateByExample(@Param("record") AppletAdvertRelation record, @Param("example") AppletAdvertRelationExample example);

    int updateByPrimaryKeySelective(AppletAdvertRelation record);

    int updateByPrimaryKey(AppletAdvertRelation record);
}
