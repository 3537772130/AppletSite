package com.applet.common.mapper;

import com.applet.common.entity.ViewUserCoupon;
import com.applet.common.entity.ViewUserCouponExample;
import java.util.List;

public interface ViewUserCouponMapper {
    long countByExample(ViewUserCouponExample example);

    List<ViewUserCoupon> selectByExample(ViewUserCouponExample example);
}