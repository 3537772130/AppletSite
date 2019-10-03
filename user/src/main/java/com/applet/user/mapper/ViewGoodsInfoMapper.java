package com.applet.user.mapper;

import com.applet.user.entity.ViewGoodsInfo;
import com.applet.user.entity.ViewGoodsInfoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewGoodsInfoMapper {
    long countByExample(ViewGoodsInfoExample example);

    List<ViewGoodsInfo> selectByExample(ViewGoodsInfoExample example);
}
