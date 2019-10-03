package com.applet.manage.mapper;

import com.applet.manage.entity.ViewAppletFile;
import com.applet.manage.entity.ViewAppletFileExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletFileMapper {
    long countByExample(ViewAppletFileExample example);

    List<ViewAppletFile> selectByExample(ViewAppletFileExample example);
}
