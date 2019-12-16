package com.applet.apply.mapper;

import com.applet.apply.entity.ViewGoodsInfo;
import com.applet.apply.entity.ViewGoodsInfoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewGoodsInfoMapper {
    long countByExample(ViewGoodsInfoExample example);

    List<ViewGoodsInfo> selectByExample(ViewGoodsInfoExample example);
}
