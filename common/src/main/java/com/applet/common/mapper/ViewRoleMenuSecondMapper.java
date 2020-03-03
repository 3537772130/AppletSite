package com.applet.common.mapper;

import com.applet.common.entity.ViewRoleMenuSecond;
import com.applet.common.entity.ViewRoleMenuSecondExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewRoleMenuSecondMapper {
    long countByExample(ViewRoleMenuSecondExample example);

    List<ViewRoleMenuSecond> selectByExample(ViewRoleMenuSecondExample example);
}
