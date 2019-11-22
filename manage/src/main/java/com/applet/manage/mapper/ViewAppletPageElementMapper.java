package com.applet.manage.mapper;

import com.applet.manage.entity.ViewAppletPageElement;
import com.applet.manage.entity.ViewAppletPageElementExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletPageElementMapper {
    long countByExample(ViewAppletPageElementExample example);

    List<ViewAppletPageElement> selectByExample(ViewAppletPageElementExample example);
}
