package com.applet.common.mapper;

import com.applet.common.entity.ViewCouponInfo;
import com.applet.common.entity.ViewCouponInfoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewCouponInfoMapper {
    long countByExample(ViewCouponInfoExample example);

    List<ViewCouponInfo> selectByExample(ViewCouponInfoExample example);
}
