package com.applet.common.mapper;

import com.applet.common.entity.ViewGoodsSellCount;
import com.applet.common.entity.ViewGoodsSellCountExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewGoodsSellCountMapper {
    long countByExample(ViewGoodsSellCountExample example);

    List<ViewGoodsSellCount> selectByExample(ViewGoodsSellCountExample example);
}
