package com.applet.common.mapper;

import com.applet.common.entity.ViewAppletVersion;
import com.applet.common.entity.ViewAppletVersionExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletVersionMapper {
    long countByExample(ViewAppletVersionExample example);

    List<ViewAppletVersion> selectByExample(ViewAppletVersionExample example);
}
