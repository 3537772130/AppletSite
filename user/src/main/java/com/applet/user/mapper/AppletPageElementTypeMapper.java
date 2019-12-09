package com.applet.user.mapper;

import com.applet.user.entity.AppletPageElementType;
import com.applet.user.entity.AppletPageElementTypeExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppletPageElementTypeMapper {
    long countByExample(AppletPageElementTypeExample example);

    List<AppletPageElementType> selectByExample(AppletPageElementTypeExample example);
}
