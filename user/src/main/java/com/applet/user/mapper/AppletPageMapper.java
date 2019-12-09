package com.applet.user.mapper;

import com.applet.user.entity.AppletPage;
import com.applet.user.entity.AppletPageExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppletPageMapper {
    long countByExample(AppletPageExample example);

    List<AppletPage> selectByExample(AppletPageExample example);
}
