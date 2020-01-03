package com.applet.apply.mapper;

import com.applet.apply.entity.SaleOrderTimeline;

/**
 * 订单状态时间流(记录订单状态扭转时间)
 *
 * @author liangzhong.tan
 * @date 2019-12-31 10:11:00
 */
public interface SaleOrderTimelineMapper {
    int deleteByPrimaryKey(Integer orderTimelineId);

    int insert(SaleOrderTimeline record);

    int insertSelective(SaleOrderTimeline record);

    SaleOrderTimeline selectByPrimaryKey(Integer orderTimelineId);

    int updateByPrimaryKeySelective(SaleOrderTimeline record);

    int updateByPrimaryKey(SaleOrderTimeline record);
}