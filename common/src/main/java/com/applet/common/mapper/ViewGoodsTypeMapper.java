package com.applet.common.mapper;

import com.applet.common.entity.ViewGoodsType;
import com.applet.common.entity.ViewGoodsTypeExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewGoodsTypeMapper {
    long countByExample(ViewGoodsTypeExample example);

    List<ViewGoodsType> selectByExample(ViewGoodsTypeExample example);
}
