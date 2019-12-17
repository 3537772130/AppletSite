package com.applet.apply.mapper;

import com.applet.apply.entity.ViewWeChantInfo;
import com.applet.apply.entity.ViewWeChantInfoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewWeChantInfoMapper {
    long countByExample(ViewWeChantInfoExample example);

    List<ViewWeChantInfo> selectByExample(ViewWeChantInfoExample example);
}
