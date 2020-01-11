package com.applet.apply.entity;

import java.io.Serializable;

public class OrderSee implements Serializable {
    private Integer id;

    private Integer orderId;

    private Boolean userStatus;

    private Boolean storeStatus;

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

    public Boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }

    public Boolean getStoreStatus() {
        return storeStatus;
    }

    public void setStoreStatus(Boolean storeStatus) {
        this.storeStatus = storeStatus;
    }
}