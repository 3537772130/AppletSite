package com.applet.common.mapper;

import com.applet.common.entity.ViewWeChantInfo;
import com.applet.common.entity.ViewWeChantInfoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewWeChantInfoMapper {
    long countByExample(ViewWeChantInfoExample example);

    List<ViewWeChantInfo> selectByExample(ViewWeChantInfoExample example);
}
