package com.applet.manage.mapper;

import com.applet.manage.entity.ViewManagerInfo;
import com.applet.manage.entity.ViewManagerInfoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewManagerInfoMapper {
    long countByExample(ViewManagerInfoExample example);

    List<ViewManagerInfo> selectByExample(ViewManagerInfoExample example);
}
