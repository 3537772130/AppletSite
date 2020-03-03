package com.applet.common.mapper;

import com.applet.common.entity.AppletVersion;
import com.applet.common.entity.AppletVersionExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AppletVersionMapper {
    long countByExample(AppletVersionExample example);

    int deleteByExample(AppletVersionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppletVersion record);

    int insertSelective(AppletVersion record);

    List<AppletVersion> selectByExample(AppletVersionExample example);

    AppletVersion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppletVersion record, @Param("example") AppletVersionExample example);

    int updateByExample(@Param("record") AppletVersion record, @Param("example") AppletVersionExample example);

    int updateByPrimaryKeySelective(AppletVersion record);

    int updateByPrimaryKey(AppletVersion record);
}
