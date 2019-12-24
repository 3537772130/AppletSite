package com.applet.manage.entity;

import com.applet.common.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class ViewMerchantInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String mobile;
    private String nickName;
    private Boolean gender;
    private String birthday;
    private String email;
    private Boolean isDealer;
    private Double balance;
    private Double freeBalance;
    private Integer integral;
    private Integer goodsCount;
    private String extensionCode;
    private Integer managerId;
    private String managerUserName;
    private String managerNickName;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_TIME_YMD)
    private Date createDate;
    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Boolean getIsDealer() {
        return isDealer;
    }

    public void setIsDealer(Boolean isDealer) {
        this.isDealer = isDealer;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getFreeBalance() {
        return freeBalance;
    }

    public void setFreeBalance(Double freeBalance) {
        this.freeBalance = freeBalance;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getExtensionCode() {
        return extensionCode;
    }

    public void setExtensionCode(String extensionCode) {
        this.extensionCode = extensionCode == null ? null : extensionCode.trim();
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getManagerUserName() {
        return managerUserName;
    }

    public void setManagerUserName(String managerUserName) {
        this.managerUserName = managerUserName == null ? null : managerUserName.trim();
    }

    public String getManagerNickName() {
        return managerNickName;
    }

    public void setManagerNickName(String managerNickName) {
        this.managerNickName = managerNickName == null ? null : managerNickName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
