package com.applet.manage.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 订单状态时间流(记录订单状态扭转时间)
 *
 * @author liangzhong.tan
 * @date 2019-12-31 10:11:00
 */
@Data
@NoArgsConstructor
public class SaleOrderTimeline {
    private Integer orderTimelineId;

    private Integer orderId;

    private Byte orderStatus;

    private String orderStatusCn;

    private Date gmtCreated;

    public SaleOrderTimeline(Integer orderId, Byte orderStatus, String orderStatusCn) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderStatusCn = orderStatusCn;
    }
}