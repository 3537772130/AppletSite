package com.applet.common.mapper;

import com.applet.common.entity.ViewUserCart;
import com.applet.common.entity.ViewUserCartExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewUserCartMapper {
    long countByExample(ViewUserCartExample example);

    List<ViewUserCart> selectByExample(ViewUserCartExample example);

    List<ViewUserCart> findByIds(List<Integer> ids);
}
