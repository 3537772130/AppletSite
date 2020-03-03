package com.applet.common.mapper;

import com.applet.common.entity.ViewOrderInfo;
import com.applet.common.entity.ViewOrderInfoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewOrderInfoMapper {
    long countByExample(ViewOrderInfoExample example);

    List<ViewOrderInfo> selectByExample(ViewOrderInfoExample example);
}
