package com.applet.common.mapper;

import com.applet.common.entity.SaleOrderTimeline;
import com.applet.common.entity.SaleOrderTimelineExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SaleOrderTimelineMapper {
    long countByExample(SaleOrderTimelineExample example);

    int deleteByExample(SaleOrderTimelineExample example);

    int deleteByPrimaryKey(Integer orderTimelineId);

    int insert(SaleOrderTimeline record);

    int insertSelective(SaleOrderTimeline record);

    List<SaleOrderTimeline> selectByExample(SaleOrderTimelineExample example);

    SaleOrderTimeline selectByPrimaryKey(Integer orderTimelineId);

    int updateByExampleSelective(@Param("record") SaleOrderTimeline record, @Param("example") SaleOrderTimelineExample example);

    int updateByExample(@Param("record") SaleOrderTimeline record, @Param("example") SaleOrderTimelineExample example);

    int updateByPrimaryKeySelective(SaleOrderTimeline record);

    int updateByPrimaryKey(SaleOrderTimeline record);
}
