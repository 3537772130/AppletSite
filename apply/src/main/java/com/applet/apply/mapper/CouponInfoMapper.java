package com.applet.apply.mapper;

import com.applet.apply.entity.CouponInfo;
import com.applet.apply.entity.CouponInfoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CouponInfoMapper {
    long countByExample(CouponInfoExample example);

    List<CouponInfo> selectByExample(CouponInfoExample example);

    CouponInfo selectByPrimaryKey(Integer id);
}
