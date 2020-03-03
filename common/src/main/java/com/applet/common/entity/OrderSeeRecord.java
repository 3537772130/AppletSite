package com.applet.common.entity;

import com.applet.common.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class OrderSeeRecord implements Serializable {
    private Integer id;

    private Integer orderId;

    private Boolean userSeeStatus;

    private Date userSeeTime;

    private Boolean storeSeeStatus;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_TIME_JDK)
    private Date storeSeeTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Boolean getUserSeeStatus() {
        return userSeeStatus;
    }

    public void setUserSeeStatus(Boolean userSeeStatus) {
        this.userSeeStatus = userSeeStatus;
    }

    public Date getUserSeeTime() {
        return userSeeTime;
    }

    public void setUserSeeTime(Date userSeeTime) {
        this.userSeeTime = userSeeTime;
    }

    public Boolean getStoreSeeStatus() {
        return storeSeeStatus;
    }

    public void setStoreSeeStatus(Boolean storeSeeStatus) {
        this.storeSeeStatus = storeSeeStatus;
    }

    public Date getStoreSeeTime() {
        return storeSeeTime;
    }

    public void setStoreSeeTime(Date storeSeeTime) {
        this.storeSeeTime = storeSeeTime;
    }
}
