package com.applet.user.mapper;

import com.applet.user.entity.ViewCouponInfo;
import com.applet.user.entity.ViewCouponInfoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewCouponInfoMapper {
    long countByExample(ViewCouponInfoExample example);

    List<ViewCouponInfo> selectByExample(ViewCouponInfoExample example);
}
