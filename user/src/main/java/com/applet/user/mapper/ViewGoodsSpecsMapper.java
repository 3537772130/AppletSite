package com.applet.user.mapper;

import com.applet.user.entity.ViewGoodsSpecs;
import com.applet.user.entity.ViewGoodsSpecsExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewGoodsSpecsMapper {
    long countByExample(ViewGoodsSpecsExample example);

    List<ViewGoodsSpecs> selectByExample(ViewGoodsSpecsExample example);
}
