package com.applet.apply.mapper;

import com.applet.apply.entity.ViewOrderInfo;
import com.applet.apply.entity.ViewOrderInfoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewOrderInfoMapper {
    long countByExample(ViewOrderInfoExample example);

    List<ViewOrderInfo> selectByExample(ViewOrderInfoExample example);
}
