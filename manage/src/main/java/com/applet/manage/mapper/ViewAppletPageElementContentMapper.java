package com.applet.manage.mapper;

import com.applet.manage.entity.ViewAppletPageElementContent;
import com.applet.manage.entity.ViewAppletPageElementContentExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletPageElementContentMapper {
    long countByExample(ViewAppletPageElementContentExample example);

    List<ViewAppletPageElementContent> selectByExample(ViewAppletPageElementContentExample example);
}
