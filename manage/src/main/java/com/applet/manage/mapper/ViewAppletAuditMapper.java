package com.applet.manage.mapper;

import com.applet.manage.entity.ViewAppletAudit;
import com.applet.manage.entity.ViewAppletAuditExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletAuditMapper {
    long countByExample(ViewAppletAuditExample example);

    List<ViewAppletAudit> selectByExample(ViewAppletAuditExample example);
}
