package com.applet.common.mapper;

import com.applet.common.entity.AppletAudit;
import com.applet.common.entity.AppletAuditExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AppletAuditMapper {
    long countByExample(AppletAuditExample example);

    int deleteByExample(AppletAuditExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppletAudit record);

    int insertSelective(AppletAudit record);

    List<AppletAudit> selectByExample(AppletAuditExample example);

    AppletAudit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppletAudit record, @Param("example") AppletAuditExample example);

    int updateByExample(@Param("record") AppletAudit record, @Param("example") AppletAuditExample example);

    int updateByPrimaryKeySelective(AppletAudit record);

    int updateByPrimaryKey(AppletAudit record);
}
