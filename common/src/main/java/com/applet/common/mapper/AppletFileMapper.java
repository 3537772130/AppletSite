package com.applet.common.mapper;

import com.applet.common.entity.AppletFile;
import com.applet.common.entity.AppletFileExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AppletFileMapper {
    long countByExample(AppletFileExample example);

    int deleteByExample(AppletFileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppletFile record);

    int insertSelective(AppletFile record);

    List<AppletFile> selectByExample(AppletFileExample example);

    AppletFile selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppletFile record, @Param("example") AppletFileExample example);

    int updateByExample(@Param("record") AppletFile record, @Param("example") AppletFileExample example);

    int updateByPrimaryKeySelective(AppletFile record);

    int updateByPrimaryKey(AppletFile record);
}
