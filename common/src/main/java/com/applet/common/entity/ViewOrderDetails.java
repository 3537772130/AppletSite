package com.applet.common.entity;

import java.io.Serializable;
import java.util.Date;

public class ViewOrderDetails implements Serializable {
    private Integer id;

    private String orderNo;

    private String courierNo;

    private Integer appletId;

    private String appletName;

    private Integer storeUserId;

    private String appletLogo;

    private Double appletLat;

    private Double appletLon;

    private String appletTelephone;

    private Integer userId;

    private String userMobile;

    private String userNickName;

    private String avatarUrl;

    private Integer wxId;

    private String wxNickName;

    private String openId;

    private Integer receiverId;

    private String receiverName;

    private String receiverMobile;

    private String receiverProvince;

    private String receiverCity;

    private String receiverCounty;

    private String receiverAddress;

    private String receiverLat;

    private String receiverLon;

    private String userRemark;

    private Integer userCouponId;

    private String couponName;

    private Double couponAmount;

    private Double freightAmount;

    private Double totalAmount;

    private Double actualAmount;

    private Integer operateId;

    private String operateRemark;

    private Date operateTime;

    private Integer operateStatus;

    private Integer orderStatus;

    private Date createTime;

    private Date updateTime;

    private Integer payType;

    private Integer payStatus;

    private String payChannel;

    private String payRelationId;

    private Date userSeeTime;

    private Boolean userSeeStatus;

    private Date storeSeeTime;

    private Boolean storeSeeStatus;

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

    public String getCourierNo() {
        return courierNo;
    }

    public void setCourierNo(String courierNo) {
        this.courierNo = courierNo == null ? null : courierNo.trim();
    }

    public Integer getAppletId() {
        return appletId;
    }

    public void setAppletId(Integer appletId) {
        this.appletId = appletId;
    }

    public String getAppletName() {
        return appletName;
    }

    public void setAppletName(String appletName) {
        this.appletName = appletName == null ? null : appletName.trim();
    }

    public Integer getStoreUserId() {
        return storeUserId;
    }

    public void setStoreUserId(Integer storeUserId) {
        this.storeUserId = storeUserId;
    }

    public String getAppletLogo() {
        return appletLogo;
    }

    public void setAppletLogo(String appletLogo) {
        this.appletLogo = appletLogo == null ? null : appletLogo.trim();
    }

    public Double getAppletLat() {
        return appletLat;
    }

    public void setAppletLat(Double appletLat) {
        this.appletLat = appletLat;
    }

    public Double getAppletLon() {
        return appletLon;
    }

    public void setAppletLon(Double appletLon) {
        this.appletLon = appletLon;
    }

    public String getAppletTelephone() {
        return appletTelephone;
    }

    public void setAppletTelephone(String appletTelephone) {
        this.appletTelephone = appletTelephone == null ? null : appletTelephone.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName == null ? null : userNickName.trim();
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    public Integer getWxId() {
        return wxId;
    }

    public void setWxId(Integer wxId) {
        this.wxId = wxId;
    }

    public String getWxNickName() {
        return wxNickName;
    }

    public void setWxNickName(String wxNickName) {
        this.wxNickName = wxNickName == null ? null : wxNickName.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile == null ? null : receiverMobile.trim();
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince == null ? null : receiverProvince.trim();
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity == null ? null : receiverCity.trim();
    }

    public String getReceiverCounty() {
        return receiverCounty;
    }

    public void setReceiverCounty(String receiverCounty) {
        this.receiverCounty = receiverCounty == null ? null : receiverCounty.trim();
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress == null ? null : receiverAddress.trim();
    }

    public String getReceiverLat() {
        return receiverLat;
    }

    public void setReceiverLat(String receiverLat) {
        this.receiverLat = receiverLat == null ? null : receiverLat.trim();
    }

    public String getReceiverLon() {
        return receiverLon;
    }

    public void setReceiverLon(String receiverLon) {
        this.receiverLon = receiverLon == null ? null : receiverLon.trim();
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark == null ? null : userRemark.trim();
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

    public Double getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(Double couponAmount) {
        this.couponAmount = couponAmount;
    }

    public Double getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(Double freightAmount) {
        this.freightAmount = freightAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Integer getOperateId() {
        return operateId;
    }

    public void setOperateId(Integer operateId) {
        this.operateId = operateId;
    }

    public String getOperateRemark() {
        return operateRemark;
    }

    public void setOperateRemark(String operateRemark) {
        this.operateRemark = operateRemark == null ? null : operateRemark.trim();
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public Integer getOperateStatus() {
        return operateStatus;
    }

    public void setOperateStatus(Integer operateStatus) {
        this.operateStatus = operateStatus;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
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

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel == null ? null : payChannel.trim();
    }

    public String getPayRelationId() {
        return payRelationId;
    }

    public void setPayRelationId(String payRelationId) {
        this.payRelationId = payRelationId == null ? null : payRelationId.trim();
    }

    public Date getUserSeeTime() {
        return userSeeTime;
    }

    public void setUserSeeTime(Date userSeeTime) {
        this.userSeeTime = userSeeTime;
    }

    public Boolean getUserSeeStatus() {
        return userSeeStatus;
    }

    public void setUserSeeStatus(Boolean userSeeStatus) {
        this.userSeeStatus = userSeeStatus;
    }

    public Date getStoreSeeTime() {
        return storeSeeTime;
    }

    public void setStoreSeeTime(Date storeSeeTime) {
        this.storeSeeTime = storeSeeTime;
    }

    public Boolean getStoreSeeStatus() {
        return storeSeeStatus;
    }

    public void setStoreSeeStatus(Boolean storeSeeStatus) {
        this.storeSeeStatus = storeSeeStatus;
    }
}