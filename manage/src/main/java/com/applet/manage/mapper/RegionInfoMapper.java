package com.applet.manage.mapper;

import com.applet.manage.entity.RegionInfo;
import com.applet.manage.entity.RegionInfoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegionInfoMapper {
    long countByExample(RegionInfoExample example);

    int deleteByPrimaryKey(Integer id);

    List<RegionInfo> selectByExample(RegionInfoExample example);

    int updateByPrimaryKeySelective(RegionInfo record);

    int updateByPrimaryKey(RegionInfo record);
}
