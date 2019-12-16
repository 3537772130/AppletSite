package com.applet.user.mapper;

import com.applet.user.entity.ViewAppletPageContent;
import com.applet.user.entity.ViewAppletPageContentExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletPageContentMapper {
    long countByExample(ViewAppletPageContentExample example);

    List<ViewAppletPageContent> selectByExampleWithBLOBs(ViewAppletPageContentExample example);

    List<ViewAppletPageContent> selectByExample(ViewAppletPageContentExample example);
}
