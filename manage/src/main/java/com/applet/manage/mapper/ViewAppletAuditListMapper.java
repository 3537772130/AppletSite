package com.applet.manage.mapper;

import com.applet.manage.entity.ViewAppletAuditList;
import com.applet.manage.entity.ViewAppletAuditListExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletAuditListMapper {
    long countByExample(ViewAppletAuditListExample example);

    List<ViewAppletAuditList> selectByExample(ViewAppletAuditListExample example);
}
