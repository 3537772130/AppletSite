package com.applet.common.mapper;

import com.applet.common.entity.ViewAppletAudit;
import com.applet.common.entity.ViewAppletAuditExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletAuditMapper {
    long countByExample(ViewAppletAuditExample example);

    List<ViewAppletAudit> selectByExample(ViewAppletAuditExample example);
}
