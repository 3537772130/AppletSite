package com.applet.manage.mapper;

import com.applet.manage.entity.ViewAppletInfo;
import com.applet.manage.entity.ViewAppletInfoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletInfoMapper {
    long countByExample(ViewAppletInfoExample example);

    List<ViewAppletInfo> selectByExample(ViewAppletInfoExample example);
}
