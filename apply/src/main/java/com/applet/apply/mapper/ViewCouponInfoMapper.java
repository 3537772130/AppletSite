package com.applet.apply.mapper;

import com.applet.apply.entity.ViewCouponInfo;
import com.applet.apply.entity.ViewCouponInfoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewCouponInfoMapper {
    long countByExample(ViewCouponInfoExample example);

    List<ViewCouponInfo> selectByExample(ViewCouponInfoExample example);
}
