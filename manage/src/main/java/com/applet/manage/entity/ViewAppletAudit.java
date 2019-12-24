package com.applet.manage.entity;

import com.applet.common.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class ViewAppletAudit implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer appletId;
    private String appletCode;
    private Integer auditResult;
    private String auditRemark;
    private Integer auditorId;
    private String auditorUserName;
    private String auditorNickName;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_TIME_JDK)
    private Date auditTime;

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

    public String getAppletCode() {
        return appletCode;
    }

    public void setAppletCode(String appletCode) {
        this.appletCode = appletCode == null ? null : appletCode.trim();
    }

    public Integer getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(Integer auditResult) {
        this.auditResult = auditResult;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark == null ? null : auditRemark.trim();
    }

    public Integer getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Integer auditorId) {
        this.auditorId = auditorId;
    }

    public String getAuditorUserName() {
        return auditorUserName;
    }

    public void setAuditorUserName(String auditorUserName) {
        this.auditorUserName = auditorUserName == null ? null : auditorUserName.trim();
    }

    public String getAuditorNickName() {
        return auditorNickName;
    }

    public void setAuditorNickName(String auditorNickName) {
        this.auditorNickName = auditorNickName == null ? null : auditorNickName.trim();
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }
}
