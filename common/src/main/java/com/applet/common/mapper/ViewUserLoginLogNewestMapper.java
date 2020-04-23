package com.applet.common.mapper;

import com.applet.common.entity.ViewUserLoginLogNewest;
import com.applet.common.entity.ViewUserLoginLogNewestExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewUserLoginLogNewestMapper {
    long countByExample(ViewUserLoginLogNewestExample example);

    List<ViewUserLoginLogNewest> selectByExample(ViewUserLoginLogNewestExample example);
}
