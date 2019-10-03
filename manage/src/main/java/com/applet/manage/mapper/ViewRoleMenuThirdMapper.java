package com.applet.manage.mapper;

import com.applet.manage.entity.ViewRoleMenuThird;
import com.applet.manage.entity.ViewRoleMenuThirdExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewRoleMenuThirdMapper {
    long countByExample(ViewRoleMenuThirdExample example);

    List<ViewRoleMenuThird> selectByExample(ViewRoleMenuThirdExample example);
}
