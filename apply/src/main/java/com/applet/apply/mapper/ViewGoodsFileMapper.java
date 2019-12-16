package com.applet.apply.mapper;

import com.applet.apply.entity.ViewGoodsFile;
import com.applet.apply.entity.ViewGoodsFileExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewGoodsFileMapper {
    long countByExample(ViewGoodsFileExample example);

    List<ViewGoodsFile> selectByExample(ViewGoodsFileExample example);
}
