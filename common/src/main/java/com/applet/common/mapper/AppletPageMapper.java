package com.applet.common.mapper;

import com.applet.common.entity.AppletPage;
import com.applet.common.entity.AppletPageExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AppletPageMapper {
    long countByExample(AppletPageExample example);

    int deleteByExample(AppletPageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppletPage record);

    int insertSelective(AppletPage record);

    List<AppletPage> selectByExample(AppletPageExample example);

    AppletPage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppletPage record, @Param("example") AppletPageExample example);

    int updateByExample(@Param("record") AppletPage record, @Param("example") AppletPageExample example);

    int updateByPrimaryKeySelective(AppletPage record);

    int updateByPrimaryKey(AppletPage record);
}
