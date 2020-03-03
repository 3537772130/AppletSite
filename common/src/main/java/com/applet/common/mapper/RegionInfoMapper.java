package com.applet.common.mapper;

import com.applet.common.entity.RegionInfo;
import com.applet.common.entity.RegionInfoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RegionInfoMapper {
    long countByExample(RegionInfoExample example);

    int deleteByExample(RegionInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RegionInfo record);

    int insertSelective(RegionInfo record);

    List<RegionInfo> selectByExample(RegionInfoExample example);

    RegionInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RegionInfo record, @Param("example") RegionInfoExample example);

    int updateByExample(@Param("record") RegionInfo record, @Param("example") RegionInfoExample example);

    int updateByPrimaryKeySelective(RegionInfo record);

    int updateByPrimaryKey(RegionInfo record);
}
