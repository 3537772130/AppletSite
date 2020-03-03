package com.applet.common.mapper;

import com.applet.common.entity.ViewGoodsFile;
import com.applet.common.entity.ViewGoodsFileExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewGoodsFileMapper {
    long countByExample(ViewGoodsFileExample example);

    List<ViewGoodsFile> selectByExample(ViewGoodsFileExample example);
}
