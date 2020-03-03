package com.applet.common.mapper;

import com.applet.common.entity.ViewGoodsInfo;
import com.applet.common.entity.ViewGoodsInfoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewGoodsInfoMapper {
    long countByExample(ViewGoodsInfoExample example);

    List<ViewGoodsInfo> selectByExample(ViewGoodsInfoExample example);
}
