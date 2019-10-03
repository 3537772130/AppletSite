package com.applet.user.mapper;

import com.applet.user.entity.ViewAppletAuditList;
import com.applet.user.entity.ViewAppletAuditListExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletAuditListMapper {
    long countByExample(ViewAppletAuditListExample example);

    List<ViewAppletAuditList> selectByExample(ViewAppletAuditListExample example);
}
