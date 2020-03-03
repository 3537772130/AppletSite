package com.applet.common.mapper;

import com.applet.common.entity.ViewManagerInfo;
import com.applet.common.entity.ViewManagerInfoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewManagerInfoMapper {
    long countByExample(ViewManagerInfoExample example);

    List<ViewManagerInfo> selectByExample(ViewManagerInfoExample example);
}
