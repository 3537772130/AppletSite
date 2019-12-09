package com.applet.user.mapper;

import com.applet.user.entity.AppletPageElement;
import com.applet.user.entity.AppletPageElementExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppletPageElementMapper {
    long countByExample(AppletPageElementExample example);

    List<AppletPageElement> selectByExample(AppletPageElementExample example);
}
