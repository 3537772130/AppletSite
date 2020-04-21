package com.applet.common.mapper;

import com.applet.common.entity.ViewAppletUserOrderCount;
import com.applet.common.entity.ViewAppletUserOrderCountExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletUserOrderCountMapper {
    long countByExample(ViewAppletUserOrderCountExample example);

    List<ViewAppletUserOrderCount> selectByExample(ViewAppletUserOrderCountExample example);
}
