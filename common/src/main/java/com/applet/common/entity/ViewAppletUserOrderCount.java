package com.applet.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class ViewAppletUserOrderCount implements Serializable {
    private Integer appletId;
    // 取消的订单数（用户取消）
    private BigDecimal cancelCount;
    // 待接的订单数
    private BigDecimal waitCount;
    // 备货的订单数
    private BigDecimal refuseCount;
    // 取消的订单数（商家驳回）
    private BigDecimal acceptCount;
    // 配送的订单数
    private BigDecimal deliverCount;
    // 完成的订单数
    private BigDecimal finishCount;

    private static final long serialVersionUID = 1L;

    public Integer getAppletId() {
        return appletId;
    }

    public void setAppletId(Integer appletId) {
        this.appletId = appletId;
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
