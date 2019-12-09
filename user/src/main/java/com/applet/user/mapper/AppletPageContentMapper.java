package com.applet.user.mapper;

import com.applet.user.entity.AppletPageContent;
import com.applet.user.entity.AppletPageContentExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppletPageContentMapper {
    long countByExample(AppletPageContentExample example);

    List<AppletPageContent> selectByExampleWithBLOBs(AppletPageContentExample example);

    List<AppletPageContent> selectByExample(AppletPageContentExample example);
}
