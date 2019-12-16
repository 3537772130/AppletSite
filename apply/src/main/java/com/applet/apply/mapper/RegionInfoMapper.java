package com.applet.apply.mapper;

import com.applet.apply.entity.RegionInfo;
import com.applet.apply.entity.RegionInfoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegionInfoMapper {
    long countByExample(RegionInfoExample example);

    List<RegionInfo> selectByExample(RegionInfoExample example);

    RegionInfo selectByPrimaryKey(Integer id);
}
