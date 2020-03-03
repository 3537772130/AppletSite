package com.applet.common.mapper;

import com.applet.common.entity.ViewAppletAuditList;
import com.applet.common.entity.ViewAppletAuditListExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletAuditListMapper {
    long countByExample(ViewAppletAuditListExample example);

    List<ViewAppletAuditList> selectByExample(ViewAppletAuditListExample example);
}
