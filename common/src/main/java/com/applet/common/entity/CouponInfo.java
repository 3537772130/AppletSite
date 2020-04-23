package com.applet.common.entity;

import com.applet.common.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class CouponInfo implements Serializable {
    private Integer id;

    private Integer userId;

    private String couponName;

    private Integer couponType;

    private Integer gainAppletId;

    private Double gainPrice;

    private Integer useAppletId;

    private Double usePrice;

    private Double denomination;

    private Integer makeIssueNum;

    private Integer alreadyIssueNum;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_TIME_ZH)
    private Date activityStart;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_TIME_ZH)
    private Date activityOver;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_TIME_JDK)
    private Date createTime;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public Integer getGainAppletId() {
        return gainAppletId;
    }

    public void setGainAppletId(Integer gainAppletId) {
        this.gainAppletId = gainAppletId;
    }

    public Double getGainPrice() {
        return gainPrice;
    }

    public void setGainPrice(Double gainPrice) {
        this.gainPrice = gainPrice;
    }

    public Integer getUseAppletId() {
        return useAppletId;
    }

    public void setUseAppletId(Integer useAppletId) {
        this.useAppletId = useAppletId;
    }

    public Double getUsePrice() {
        return usePrice;
    }

    public void setUsePrice(Double usePrice) {
        this.usePrice = usePrice;
    }

    public Double getDenomination() {
        return denomination;
    }

    public void setDenomination(Double denomination) {
        this.denomination = denomination;
    }

    public Integer getMakeIssueNum() {
        return makeIssueNum;
    }

    public void setMakeIssueNum(Integer makeIssueNum) {
        this.makeIssueNum = makeIssueNum;
    }

    public Integer getAlreadyIssueNum() {
        return alreadyIssueNum;
    }

    public void setAlreadyIssueNum(Integer alreadyIssueNum) {
        this.alreadyIssueNum = alreadyIssueNum;
    }

    public Date getActivityStart() {
        return activityStart;
    }

    public void setActivityStart(Date activityStart) {
        this.activityStart = activityStart;
    }

    public Date getActivityOver() {
        return activityOver;
    }

    public void setActivityOver(Date activityOver) {
        this.activityOver = activityOver;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
