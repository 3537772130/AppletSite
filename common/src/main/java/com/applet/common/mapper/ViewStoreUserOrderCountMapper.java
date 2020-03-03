package com.applet.common.mapper;

import com.applet.common.entity.ViewStoreUserOrderCount;
import com.applet.common.entity.ViewStoreUserOrderCountExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewStoreUserOrderCountMapper {
    long countByExample(ViewStoreUserOrderCountExample example);

    List<ViewStoreUserOrderCount> selectByExample(ViewStoreUserOrderCountExample example);
}
