package com.applet.manage.entity;

import com.applet.common.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class AppletType implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String typeName;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_TIME_JDK)
    private Date updateTime;
    private Boolean typeStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(Boolean typeStatus) {
        this.typeStatus = typeStatus;
    }
}
