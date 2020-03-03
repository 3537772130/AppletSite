package com.applet.common.mapper;

import com.applet.common.entity.ViewGoodsSpecs;
import com.applet.common.entity.ViewGoodsSpecsExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewGoodsSpecsMapper {
    long countByExample(ViewGoodsSpecsExample example);

    List<ViewGoodsSpecs> selectByExample(ViewGoodsSpecsExample example);
}
