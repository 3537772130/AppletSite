package com.applet.apply.mapper;

import com.applet.apply.entity.ViewOrderInfo;
import com.applet.apply.entity.ViewOrderInfoExample;
import java.util.List;

public interface ViewOrderInfoMapper {
    long countByExample(ViewOrderInfoExample example);

    List<ViewOrderInfo> selectByExample(ViewOrderInfoExample example);
}