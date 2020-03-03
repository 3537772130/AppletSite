package com.applet.common.mapper;

import com.applet.common.entity.ViewRoleMenuFirst;
import com.applet.common.entity.ViewRoleMenuFirstExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewRoleMenuFirstMapper {
    long countByExample(ViewRoleMenuFirstExample example);

    List<ViewRoleMenuFirst> selectByExample(ViewRoleMenuFirstExample example);
}
