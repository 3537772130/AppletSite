package com.applet.common.mapper;

import com.applet.common.entity.ViewGoodsSpecsCount;
import com.applet.common.entity.ViewGoodsSpecsCountExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewGoodsSpecsCountMapper {
    long countByExample(ViewGoodsSpecsCountExample example);

    List<ViewGoodsSpecsCount> selectByExample(ViewGoodsSpecsCountExample example);
}
