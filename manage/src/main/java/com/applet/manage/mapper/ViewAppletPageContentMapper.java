package com.applet.manage.mapper;

import com.applet.manage.entity.ViewAppletPageContent;
import com.applet.manage.entity.ViewAppletPageContentExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletPageContentMapper {
    long countByExample(ViewAppletPageContentExample example);

    List<ViewAppletPageContent> selectByExample(ViewAppletPageContentExample example);
}
