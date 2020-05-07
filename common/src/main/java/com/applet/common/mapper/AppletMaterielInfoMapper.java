package com.applet.common.mapper;

import com.applet.common.entity.AppletMaterielInfo;
import com.applet.common.entity.AppletMaterielInfoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AppletMaterielInfoMapper {
    long countByExample(AppletMaterielInfoExample example);

    int deleteByExample(AppletMaterielInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppletMaterielInfo record);

    int insertSelective(AppletMaterielInfo record);

    List<AppletMaterielInfo> selectByExample(AppletMaterielInfoExample example);

    AppletMaterielInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppletMaterielInfo record, @Param("example") AppletMaterielInfoExample example);

    int updateByExample(@Param("record") AppletMaterielInfo record, @Param("example") AppletMaterielInfoExample example);

    int updateByPrimaryKeySelective(AppletMaterielInfo record);

    int updateByPrimaryKey(AppletMaterielInfo record);
}
