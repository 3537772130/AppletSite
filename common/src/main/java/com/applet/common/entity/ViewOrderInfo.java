package com.applet.common.entity;

import com.applet.common.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ViewOrderInfo implements Serializable {
    private Integer id;

    private String orderNo;

    private Integer userId;

    private String nickName;

    private String avatarUrl;

    private Integer appletId;

    private Integer storeUserId;

    private String appletName;

    private String appletLogo;

    private Double appletLon;

    private Double appletLat;

    private String appletTelephone;

    private String receiverName;

    private String receiverPhone;

    private String detailAddr;

    private Double lat;

    private Double lon;

    private Integer userCouponId;

    private String couponName;

    private BigDecimal ticketAmount;

    private BigDecimal carriersFee;

    private BigDecimal totalAmount;

    private String orderRemark;

    private String reason;

    private Byte payType;

    private Boolean userSeeStatus;

    private Date userSeeTime;

    private Boolean storeSeeStatus;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_TIME_JDK)
    private Date storeSeeTime;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_TIME_JDK)
    private Date createTime;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_TIME_JDK)
    private Date updateTime;

    private Byte orderStatus;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    public Integer getAppletId() {
        return appletId;
    }

    public void setAppletId(Integer appletId) {
        this.appletId = appletId;
    }

    public Integer getStoreUserId() {
        return storeUserId;
    }

    public void setStoreUserId(Integer storeUserId) {
        this.storeUserId = storeUserId;
    }

    public String getAppletName() {
        return appletName;
    }

    public void setAppletName(String appletName) {
        this.appletName = appletName == null ? null : appletName.trim();
    }

    public String getAppletLogo() {
        return appletLogo;
    }

    public void setAppletLogo(String appletLogo) {
        this.appletLogo = appletLogo == null ? null : appletLogo.trim();
    }

    public Double getAppletLon() {
        return appletLon;
    }

    public void setAppletLon(Double appletLon) {
        this.appletLon = appletLon;
    }

    public Double getAppletLat() {
        return appletLat;
    }

    public void setAppletLat(Double appletLat) {
        this.appletLat = appletLat;
    }

    public String getAppletTelephone() {
        return appletTelephone;
    }

    public void setAppletTelephone(String appletTelephone) {
        this.appletTelephone = appletTelephone == null ? null : appletTelephone.trim();
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone == null ? null : receiverPhone.trim();
    }

    public String getDetailAddr() {
        return detailAddr;
    }

    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr == null ? null : detailAddr.trim();
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Integer getUserCouponId() {
        return userCouponId;
    }

    public void setUserCouponId(Integer userCouponId) {
        this.userCouponId = userCouponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }

    public BigDecimal getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(BigDecimal ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public BigDecimal getCarriersFee() {
        return carriersFee;
    }

    public void setCarriersFee(BigDecimal carriersFee) {
        this.carriersFee = carriersFee;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark == null ? null : orderRemark.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }
}
