package com.applet.user.mapper;

import com.applet.user.entity.ViewGoodsType;
import com.applet.user.entity.ViewGoodsTypeExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewGoodsTypeMapper {
    long countByExample(ViewGoodsTypeExample example);

    List<ViewGoodsType> selectByExample(ViewGoodsTypeExample example);
}
