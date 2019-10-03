package com.applet.user.entity;

import com.applet.user.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class AppletInfo implements Serializable {
    private Integer id;

    private Integer userId;

    private static final long serialVersionUID = 1L;
    private Integer typeId;

    private String appletCode;

    private String appletName;

    private String appletSimple;

    private String licenseCode;

    private String businessScope;
    private String appletLogo;

    private String telephone;

    private String managerAccount;

    private String managerPassword;

    private String appId;

    private String appSecret;

    private String province;

    private String city;

    private String county;

    private String addressSimple;

    private String addressDetails;

    private Double lon;

    private Double lat;

    private Boolean ifRetail;

    private Boolean ifSelling;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_TIME_JDK)
    private Date updateTime;
    private String licenseSrc;
    private Integer status;

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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getAppletLogo() {
        return appletLogo;
    }

    public void setAppletLogo(String appletLogo) {
        this.appletLogo = appletLogo == null ? null : appletLogo.trim();
    }

    public String getAppletCode() {
        return appletCode;
    }

    public void setAppletCode(String appletCode) {
        this.appletCode = appletCode == null ? null : appletCode.trim();
    }

    public String getAppletName() {
        return appletName;
    }

    public void setAppletName(String appletName) {
        this.appletName = appletName == null ? null : appletName.trim();
    }

    public String getAppletSimple() {
        return appletSimple;
    }

    public void setAppletSimple(String appletSimple) {
        this.appletSimple = appletSimple == null ? null : appletSimple.trim();
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode == null ? null : licenseCode.trim();
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope == null ? null : businessScope.trim();
    }

    public String getLicenseSrc() {
        return licenseSrc;
    }

    public void setLicenseSrc(String licenseSrc) {
        this.licenseSrc = licenseSrc == null ? null : licenseSrc.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getManagerAccount() {
        return managerAccount;
    }

    public void setManagerAccount(String managerAccount) {
        this.managerAccount = managerAccount == null ? null : managerAccount.trim();
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword == null ? null : managerPassword.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret == null ? null : appSecret.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public String getAddressSimple() {
        return addressSimple;
    }

    public void setAddressSimple(String addressSimple) {
        this.addressSimple = addressSimple == null ? null : addressSimple.trim();
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails == null ? null : addressDetails.trim();
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Boolean getIfRetail() {
        return ifRetail;
    }

    public void setIfRetail(Boolean ifRetail) {
        this.ifRetail = ifRetail;
    }

    public Boolean getIfSelling() {
        return ifSelling;
    }

    public void setIfSelling(Boolean ifSelling) {
        this.ifSelling = ifSelling;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
