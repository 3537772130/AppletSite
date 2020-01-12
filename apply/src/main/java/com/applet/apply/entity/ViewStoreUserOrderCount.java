package com.applet.apply.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商户订单统计
 */
public class ViewStoreUserOrderCount implements Serializable {
    private Integer storeUserId;

    private BigDecimal cancelCount;

    private BigDecimal waitCount;

    private BigDecimal refuseCount;

    private BigDecimal acceptCount;

    private BigDecimal deliverCount;

    private BigDecimal finishCount;

    private static final long serialVersionUID = 1L;

    public Integer getStoreUserId() {
        return storeUserId;
    }

    public void setStoreUserId(Integer storeUserId) {
        this.storeUserId = storeUserId;
    }

    public BigDecimal getCancelCount() {
        return cancelCount;
    }

    public void setCancelCount(BigDecimal cancelCount) {
        this.cancelCount = cancelCount;
    }

    public BigDecimal getWaitCount() {
        return waitCount;
    }

    public void setWaitCount(BigDecimal waitCount) {
        this.waitCount = waitCount;
    }

    public BigDecimal getRefuseCount() {
        return refuseCount;
    }

    public void setRefuseCount(BigDecimal refuseCount) {
        this.refuseCount = refuseCount;
    }

    public BigDecimal getAcceptCount() {
        return acceptCount;
    }

    public void setAcceptCount(BigDecimal acceptCount) {
        this.acceptCount = acceptCount;
    }

    public BigDecimal getDeliverCount() {
        return deliverCount;
    }

    public void setDeliverCount(BigDecimal deliverCount) {
        this.deliverCount = deliverCount;
    }

    public BigDecimal getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(BigDecimal finishCount) {
        this.finishCount = finishCount;
    }
}
