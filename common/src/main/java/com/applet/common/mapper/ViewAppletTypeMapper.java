package com.applet.common.mapper;

import com.applet.common.entity.ViewAppletType;
import com.applet.common.entity.ViewAppletTypeExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletTypeMapper {
    long countByExample(ViewAppletTypeExample example);

    List<ViewAppletType> selectByExample(ViewAppletTypeExample example);
}
