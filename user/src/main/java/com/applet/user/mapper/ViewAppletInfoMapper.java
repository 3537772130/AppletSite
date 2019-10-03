package com.applet.user.mapper;

import com.applet.user.entity.ViewAppletInfo;
import com.applet.user.entity.ViewAppletInfoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletInfoMapper {
    long countByExample(ViewAppletInfoExample example);

    List<ViewAppletInfo> selectByExample(ViewAppletInfoExample example);
}
