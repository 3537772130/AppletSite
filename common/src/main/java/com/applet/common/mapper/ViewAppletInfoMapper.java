package com.applet.common.mapper;

import com.applet.common.entity.ViewAppletInfo;
import com.applet.common.entity.ViewAppletInfoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletInfoMapper {
    long countByExample(ViewAppletInfoExample example);

    List<ViewAppletInfo> selectByExample(ViewAppletInfoExample example);
}
