package com.applet.manage.mapper;

import com.applet.manage.entity.ViewRoleMenuSecond;
import com.applet.manage.entity.ViewRoleMenuSecondExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewRoleMenuSecondMapper {
    long countByExample(ViewRoleMenuSecondExample example);

    List<ViewRoleMenuSecond> selectByExample(ViewRoleMenuSecondExample example);
}
