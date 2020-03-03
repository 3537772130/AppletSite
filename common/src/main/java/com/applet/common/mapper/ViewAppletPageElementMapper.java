package com.applet.common.mapper;

import com.applet.common.entity.ViewAppletPageElement;
import com.applet.common.entity.ViewAppletPageElementExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletPageElementMapper {
    long countByExample(ViewAppletPageElementExample example);

    List<ViewAppletPageElement> selectByExample(ViewAppletPageElementExample example);
}
