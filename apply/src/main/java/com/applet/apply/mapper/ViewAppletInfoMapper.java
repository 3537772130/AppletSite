package com.applet.apply.mapper;

import com.applet.apply.entity.ViewAppletInfo;
import com.applet.apply.entity.ViewAppletInfoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletInfoMapper {
    long countByExample(ViewAppletInfoExample example);

    List<ViewAppletInfo> selectByExample(ViewAppletInfoExample example);
}
