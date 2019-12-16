package com.applet.apply.mapper;

import com.applet.apply.entity.ViewGoodsSpecs;
import com.applet.apply.entity.ViewGoodsSpecsExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewGoodsSpecsMapper {
    long countByExample(ViewGoodsSpecsExample example);

    List<ViewGoodsSpecs> selectByExample(ViewGoodsSpecsExample example);
}
