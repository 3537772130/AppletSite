package com.applet.common.mapper;

import com.applet.common.entity.ViewOrderPayData;
import com.applet.common.entity.ViewOrderPayDataExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewOrderPayDataMapper {
    long countByExample(ViewOrderPayDataExample example);

    List<ViewOrderPayData> selectByExample(ViewOrderPayDataExample example);
}
