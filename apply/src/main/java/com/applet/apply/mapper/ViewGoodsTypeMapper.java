package com.applet.apply.mapper;

import com.applet.apply.entity.ViewGoodsType;
import com.applet.apply.entity.ViewGoodsTypeExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewGoodsTypeMapper {
    long countByExample(ViewGoodsTypeExample example);

    List<ViewGoodsType> selectByExample(ViewGoodsTypeExample example);
}
