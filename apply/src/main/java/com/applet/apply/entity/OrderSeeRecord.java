package com.applet.apply.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单查看记录
 */
public class OrderSeeRecord implements Serializable {
    private Integer id;

    private Integer orderId;

    private Boolean userSeeStatus;

    private Date userSeeTime;

    private Boolean storeSeeStatus;

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
