package com.applet.apply.mapper;

import com.applet.apply.entity.ViewGoodsSellCount;
import com.applet.apply.entity.ViewGoodsSellCountExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewGoodsSellCountMapper {
    long countByExample(ViewGoodsSellCountExample example);

    List<ViewGoodsSellCount> selectByExample(ViewGoodsSellCountExample example);
}
