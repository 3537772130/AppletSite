package com.applet.common.mapper;

import com.applet.common.entity.CouponInfo;
import com.applet.common.entity.CouponInfoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CouponInfoMapper {
    long countByExample(CouponInfoExample example);

    int deleteByExample(CouponInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CouponInfo record);

    int insertSelective(CouponInfo record);

    List<CouponInfo> selectByExample(CouponInfoExample example);

    CouponInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CouponInfo record, @Param("example") CouponInfoExample example);

    int updateByExample(@Param("record") CouponInfo record, @Param("example") CouponInfoExample example);

    int updateByPrimaryKeySelective(CouponInfo record);

    int updateByPrimaryKey(CouponInfo record);
}
