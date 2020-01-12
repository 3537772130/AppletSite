package com.applet.apply.mapper;

import com.applet.apply.entity.ViewStoreUserOrderCount;
import com.applet.apply.entity.ViewStoreUserOrderCountExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewStoreUserOrderCountMapper {
    long countByExample(ViewStoreUserOrderCountExample example);

    List<ViewStoreUserOrderCount> selectByExample(ViewStoreUserOrderCountExample example);
}
