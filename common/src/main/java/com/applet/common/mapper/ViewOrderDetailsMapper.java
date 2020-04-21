package com.applet.common.mapper;

import com.applet.common.entity.ViewOrderDetails;
import com.applet.common.entity.ViewOrderDetailsExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewOrderDetailsMapper {
    long countByExample(ViewOrderDetailsExample example);

    List<ViewOrderDetails> selectByExample(ViewOrderDetailsExample example);
}
