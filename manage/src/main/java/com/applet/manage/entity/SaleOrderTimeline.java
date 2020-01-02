package com.applet.manage.entity;

import lombok.Data;

import java.util.Date;

/**
 * 订单状态时间流(记录订单状态扭转时间)
 *
 * @author liangzhong.tan
 * @date 2019-12-31 10:11:00
 */
@Data
public class SaleOrderTimeline {
    private Integer orderTimelineId;

    private Integer orderId;

    private Byte orderStatus;

    private Byte orderStatusCn;

    private Date gmtCreated;
}