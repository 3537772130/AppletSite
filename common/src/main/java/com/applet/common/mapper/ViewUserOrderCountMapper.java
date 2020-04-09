package com.applet.common.mapper;

import com.applet.common.entity.ViewUserOrderCount;
import com.applet.common.entity.ViewUserOrderCountExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewUserOrderCountMapper {
    long countByExample(ViewUserOrderCountExample example);

    List<ViewUserOrderCount> selectByExample(ViewUserOrderCountExample example);
}
