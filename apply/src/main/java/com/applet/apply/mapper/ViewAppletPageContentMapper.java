package com.applet.apply.mapper;

import com.applet.apply.entity.ViewAppletPageContent;
import com.applet.apply.entity.ViewAppletPageContentExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletPageContentMapper {
    long countByExample(ViewAppletPageContentExample example);

    List<ViewAppletPageContent> selectByExampleWithBLOBs(ViewAppletPageContentExample example);

    List<ViewAppletPageContent> selectByExample(ViewAppletPageContentExample example);
}
