package com.applet.manage.mapper;

import com.applet.manage.entity.ViewAppletPageElementDefault;
import com.applet.manage.entity.ViewAppletPageElementDefaultExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletPageElementDefaultMapper {
    long countByExample(ViewAppletPageElementDefaultExample example);

    List<ViewAppletPageElementDefault> selectByExample(ViewAppletPageElementDefaultExample example);
}
