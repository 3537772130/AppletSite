package com.applet.common.mapper;

import com.applet.common.entity.ViewAppletPage;
import com.applet.common.entity.ViewAppletPageExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletPageMapper {
    long countByExample(ViewAppletPageExample example);

    List<ViewAppletPage> selectByExample(ViewAppletPageExample example);
}
