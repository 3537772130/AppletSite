package com.applet.user.mapper;

import com.applet.user.entity.ViewAppletPage;
import com.applet.user.entity.ViewAppletPageExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletPageMapper {
    long countByExample(ViewAppletPageExample example);

    List<ViewAppletPage> selectByExample(ViewAppletPageExample example);
}
