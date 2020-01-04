package com.applet.apply.mapper;

import com.applet.apply.entity.ViewUserCart;
import com.applet.apply.entity.ViewUserCartExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewUserCartMapper {
    long countByExample(ViewUserCartExample example);

    List<ViewUserCart> selectByExample(ViewUserCartExample example);
}
