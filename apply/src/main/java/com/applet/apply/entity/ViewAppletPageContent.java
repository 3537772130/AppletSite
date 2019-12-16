package com.applet.apply.entity;

import java.io.Serializable;
import java.util.Date;

public class ViewAppletPageContent implements Serializable {
    private Integer id;

    private Integer appletId;

    private String appletName;

    private Integer userId;

    private Integer appletTypeId;

    private String typeName;

    private Integer appletFileId;

    private String versionNumber;

    private Integer pageId;

    private String pageLogo;

    private String pageName;

    private Date updateTime;

    private String contentJson;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAppletTypeId() {
        return appletTypeId;
    }

    public void setAppletTypeId(Integer appletTypeId) {
        this.appletTypeId = appletTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Integer getAppletFileId() {
        return appletFileId;
    }

    public void setAppletFileId(Integer appletFileId) {
        this.appletFileId = appletFileId;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber == null ? null : versionNumber.trim();
    }

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public String getPageLogo() {
        return pageLogo;
    }

    public void setPageLogo(String pageLogo) {
        this.pageLogo = pageLogo == null ? null : pageLogo.trim();
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName == null ? null : pageName.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getContentJson() {
        return contentJson;
    }

    public void setContentJson(String contentJson) {
        this.contentJson = contentJson == null ? null : contentJson.trim();
    }
}