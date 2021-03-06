package com.applet.common.entity;

import com.applet.common.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 小程序广告控制关联
 */
public class AppletAdvertRelation implements Serializable {
    private Integer id;

    private Integer appletTypeId;

    private String pageLogo;

    private Integer relationType;

    private String relationImage;

    private String relationWebsite;

    private String relationName;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_YMD_JDK)
    private Date startTime;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_YMD_JDK)
    private Date expireTime;

    private Boolean isDefault;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_TIME_JDK)
    private Date updateTime;

    private Boolean relationStatus;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppletTypeId() {
        return appletTypeId;
    }

    public void setAppletTypeId(Integer appletTypeId) {
        this.appletTypeId = appletTypeId;
    }

    public String getPageLogo() {
        return pageLogo;
    }

    public void setPageLogo(String pageLogo) {
        this.pageLogo = pageLogo == null ? null : pageLogo.trim();
    }

    public Integer getRelationType() {
        return relationType;
    }

    public void setRelationType(Integer relationType) {
        this.relationType = relationType;
    }

    public String getRelationImage() {
        return relationImage;
    }

    public void setRelationImage(String relationImage) {
        this.relationImage = relationImage == null ? null : relationImage.trim();
    }

    public String getRelationWebsite() {
        return relationWebsite;
    }

    public void setRelationWebsite(String relationWebsite) {
        this.relationWebsite = relationWebsite == null ? null : relationWebsite.trim();
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName == null ? null : relationName.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getRelationStatus() {
        return relationStatus;
    }

    public void setRelationStatus(Boolean relationStatus) {
        this.relationStatus = relationStatus;
    }
}
