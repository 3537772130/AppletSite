package com.applet.common.mapper;

import com.applet.common.entity.ViewUserCoupon;
import com.applet.common.entity.ViewUserCouponExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewUserCouponMapper {
    long countByExample(ViewUserCouponExample example);

    List<ViewUserCoupon> selectByExample(ViewUserCouponExample example);
}
