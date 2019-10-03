package com.applet.manage.mapper;

import com.applet.manage.entity.ViewManagerLog;
import com.applet.manage.entity.ViewManagerLogExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewManagerLogMapper {
    long countByExample(ViewManagerLogExample example);

    List<ViewManagerLog> selectByExample(ViewManagerLogExample example);
}
