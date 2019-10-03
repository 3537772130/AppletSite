package com.applet.manage.mapper;

import com.applet.manage.entity.AppletInfo;
import com.applet.manage.entity.AppletInfoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppletInfoMapper {
    long countByExample(AppletInfoExample example);

    int deleteByExample(AppletInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppletInfo record);

    int insertSelective(AppletInfo record);

    List<AppletInfo> selectByExample(AppletInfoExample example);

    AppletInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppletInfo record, @Param("example") AppletInfoExample example);

    int updateByExample(@Param("record") AppletInfo record, @Param("example") AppletInfoExample example);

    int updateByPrimaryKeySelective(AppletInfo record);

    int updateByPrimaryKey(AppletInfo record);
}
