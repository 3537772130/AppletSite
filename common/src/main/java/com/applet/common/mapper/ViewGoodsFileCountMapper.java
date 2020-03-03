package com.applet.common.mapper;

import com.applet.common.entity.ViewGoodsFileCount;
import com.applet.common.entity.ViewGoodsFileCountExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewGoodsFileCountMapper {
    long countByExample(ViewGoodsFileCountExample example);

    List<ViewGoodsFileCount> selectByExample(ViewGoodsFileCountExample example);
}
