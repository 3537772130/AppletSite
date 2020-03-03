package com.applet.common.entity;

import com.applet.common.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class ViewCouponInfo implements Serializable {
    private Integer id;

    private Integer userId;

    private String nickName;

    private String mobile;

    private String couponName;

    private Integer couponType;

    private Integer gainAppletId;

    private String gainAppletName;

    private Double gainPrice;

    private Integer useAppletId;

    private String useAppletName;

    private String useAppletLogo;

    private Double usePrice;

    private Double denomination;

    private Integer makeIssueNum;

    private Integer alreadyIssueNum;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_TIME_JDK)
    private Date activityStart;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_TIME_JDK)
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
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

    public String getGainAppletName() {
        return gainAppletName;
    }

    public void setGainAppletName(String gainAppletName) {
        this.gainAppletName = gainAppletName == null ? null : gainAppletName.trim();
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
