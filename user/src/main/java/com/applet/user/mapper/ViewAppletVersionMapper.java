package com.applet.user.mapper;

import com.applet.user.entity.ViewAppletVersion;
import com.applet.user.entity.ViewAppletVersionExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletVersionMapper {
    long countByExample(ViewAppletVersionExample example);

    List<ViewAppletVersion> selectByExample(ViewAppletVersionExample example);
}
