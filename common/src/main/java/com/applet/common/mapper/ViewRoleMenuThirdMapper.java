package com.applet.common.mapper;

import com.applet.common.entity.ViewRoleMenuThird;
import com.applet.common.entity.ViewRoleMenuThirdExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewRoleMenuThirdMapper {
    long countByExample(ViewRoleMenuThirdExample example);

    List<ViewRoleMenuThird> selectByExample(ViewRoleMenuThirdExample example);
}
