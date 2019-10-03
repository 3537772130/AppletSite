package com.applet.manage.mapper;

import com.applet.manage.entity.ViewAppletVersion;
import com.applet.manage.entity.ViewAppletVersionExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletVersionMapper {
    long countByExample(ViewAppletVersionExample example);

    List<ViewAppletVersion> selectByExample(ViewAppletVersionExample example);
}
