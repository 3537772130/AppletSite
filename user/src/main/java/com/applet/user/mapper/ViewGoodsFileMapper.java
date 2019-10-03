package com.applet.user.mapper;

import com.applet.user.entity.ViewGoodsFile;
import com.applet.user.entity.ViewGoodsFileExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewGoodsFileMapper {
    long countByExample(ViewGoodsFileExample example);

    List<ViewGoodsFile> selectByExample(ViewGoodsFileExample example);
}
