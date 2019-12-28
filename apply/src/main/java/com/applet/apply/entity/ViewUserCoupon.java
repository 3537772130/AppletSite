package com.applet.apply.entity;

import com.applet.common.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 关联用户-优惠券-小程序 视图信息类
 */
public class ViewUserCoupon implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer couponId;

    private String couponName;

    private Integer couponType;

    private Integer useAppletId;

    private String useAppletName;

    private String useAppletLogo;

    private Double usePrice;

    private Double denomination;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_TIME_JDK)
    private Date gainTime;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_TIME_JDK)
    private Date activityOver;

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

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
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

    public Integer getUseAppletId() {
        return useAppletId;
    }

    public void setUseAppletId(Integer useAppletId) {
        this.useAppletId = useAppletId;
    }

    public String getUseAppletName() {
        return useAppletName;
    }

    public void setUseAppletName(String useAppletName) {
        this.useAppletName = useAppletName == null ? null : useAppletName.trim();
    }

    public String getUseAppletLogo() {
        return useAppletLogo;
    }

    public void setUseAppletLogo(String useAppletLogo) {
        this.useAppletLogo = useAppletLogo == null ? null : useAppletLogo.trim();
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

    public Date getGainTime() {
        return gainTime;
    }

    public void setGainTime(Date gainTime) {
        this.gainTime = gainTime;
    }

    public Date getActivityOver() {
        return activityOver;
    }

    public void setActivityOver(Date activityOver) {
        this.activityOver = activityOver;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
