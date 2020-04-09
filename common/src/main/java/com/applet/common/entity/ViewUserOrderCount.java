package com.applet.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户（消费者）订单统计
 */
public class ViewUserOrderCount implements Serializable {
    private Integer userId;
    // 取消、拒绝的订单数
    private BigDecimal incompleteCount;
    // 待接单的订单数
    private BigDecimal waitMeetCount;
    // 待签收的订单数
    private BigDecimal waitCollectCount;
    // 已签收的订单数
    private BigDecimal finishCount;

    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getIncompleteCount() {
        return incompleteCount;
    }

    public void setIncompleteCount(BigDecimal incompleteCount) {
        this.incompleteCount = incompleteCount;
    }

    public BigDecimal getWaitMeetCount() {
        return waitMeetCount;
    }

    public void setWaitMeetCount(BigDecimal waitMeetCount) {
        this.waitMeetCount = waitMeetCount;
    }

    public BigDecimal getWaitCollectCount() {
        return waitCollectCount;
    }

    public void setWaitCollectCount(BigDecimal waitCollectCount) {
        this.waitCollectCount = waitCollectCount;
    }

    public BigDecimal getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(BigDecimal finishCount) {
        this.finishCount = finishCount;
    }
}
