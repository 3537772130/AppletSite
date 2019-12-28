package com.applet.apply.mapper;

import com.applet.apply.entity.ViewUserCoupon;
import com.applet.apply.entity.ViewUserCouponExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewUserCouponMapper {
    long countByExample(ViewUserCouponExample example);

    List<ViewUserCoupon> selectByExample(ViewUserCouponExample example);
}
