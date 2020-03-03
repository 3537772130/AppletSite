package com.applet.common.mapper;

import com.applet.common.entity.ViewManagerLog;
import com.applet.common.entity.ViewManagerLogExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewManagerLogMapper {
    long countByExample(ViewManagerLogExample example);

    List<ViewManagerLog> selectByExample(ViewManagerLogExample example);
}
