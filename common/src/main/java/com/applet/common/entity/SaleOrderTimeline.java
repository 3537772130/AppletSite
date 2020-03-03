package com.applet.common.entity;

import com.applet.common.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class SaleOrderTimeline implements Serializable {
    private Integer orderTimelineId;

    private Integer orderId;

    private Byte orderStatus;

    private String orderStatusCn;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_TIME_JDK)
    private Date gmtCreated;

    private static final long serialVersionUID = 1L;

    public Integer getOrderTimelineId() {
        return orderTimelineId;
    }

    public void setOrderTimelineId(Integer orderTimelineId) {
        this.orderTimelineId = orderTimelineId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusCn() {
        return orderStatusCn;
    }

    public void setOrderStatusCn(String orderStatusCn) {
        this.orderStatusCn = orderStatusCn == null ? null : orderStatusCn.trim();
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public SaleOrderTimeline(Integer orderId, Byte orderStatus, String orderStatusCn) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderStatusCn = orderStatusCn;
    }
}
