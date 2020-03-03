package com.applet.common.mapper;

import com.applet.common.entity.ViewAppletFile;
import com.applet.common.entity.ViewAppletFileExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletFileMapper {
    long countByExample(ViewAppletFileExample example);

    List<ViewAppletFile> selectByExample(ViewAppletFileExample example);
}
