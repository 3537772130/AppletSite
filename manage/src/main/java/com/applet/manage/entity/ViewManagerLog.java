package com.applet.manage.entity;

import com.applet.common.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class ViewManagerLog implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer managerId;
    private String managerUserName;
    private String managerNickName;
    private String beforeJson;
    private String afterJson;
    private Integer operatorId;
    private String operatorUserName;
    private String operatorNickName;
    private String operatorIp;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_TIME_JDK)
    private Date operateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getBeforeJson() {
        return beforeJson;
    }

    public void setBeforeJson(String beforeJson) {
        this.beforeJson = beforeJson == null ? null : beforeJson.trim();
    }

    public String getAfterJson() {
        return afterJson;
    }

    public void setAfterJson(String afterJson) {
        this.afterJson = afterJson == null ? null : afterJson.trim();
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorUserName() {
        return operatorUserName;
    }

    public void setOperatorUserName(String operatorUserName) {
        this.operatorUserName = operatorUserName == null ? null : operatorUserName.trim();
    }

    public String getOperatorNickName() {
        return operatorNickName;
    }

    public void setOperatorNickName(String operatorNickName) {
        this.operatorNickName = operatorNickName == null ? null : operatorNickName.trim();
    }

    public String getOperatorIp() {
        return operatorIp;
    }

    public void setOperatorIp(String operatorIp) {
        this.operatorIp = operatorIp == null ? null : operatorIp.trim();
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}
