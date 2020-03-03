package com.applet.common.mapper;

import com.applet.common.entity.AppletType;
import com.applet.common.entity.AppletTypeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
