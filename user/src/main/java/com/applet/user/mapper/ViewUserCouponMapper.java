package com.applet.user.mapper;

import com.applet.user.entity.ViewUserCoupon;
import com.applet.user.entity.ViewUserCouponExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewUserCouponMapper {
    long countByExample(ViewUserCouponExample example);

    List<ViewUserCoupon> selectByExample(ViewUserCouponExample example);
}
