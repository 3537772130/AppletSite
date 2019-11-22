package com.applet.manage.mapper;

import com.applet.manage.entity.ViewAppletType;
import com.applet.manage.entity.ViewAppletTypeExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletTypeMapper {
    long countByExample(ViewAppletTypeExample example);

    List<ViewAppletType> selectByExample(ViewAppletTypeExample example);
}
