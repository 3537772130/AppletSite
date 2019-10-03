package com.applet.manage.mapper;

import com.applet.manage.entity.ViewRoleMenuFirst;
import com.applet.manage.entity.ViewRoleMenuFirstExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewRoleMenuFirstMapper {
    long countByExample(ViewRoleMenuFirstExample example);

    List<ViewRoleMenuFirst> selectByExample(ViewRoleMenuFirstExample example);
}
