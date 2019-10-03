package com.applet.manage.entity;

import com.applet.manage.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class AppletPageElement implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer pageId;
    private String elementIcon;
    private String elementLogo;
    private String elementName;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_TIME_JDK)
    private Date updateTime;
    private Boolean elementStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public String getElementIcon() {
        return elementIcon;
    }

    public void setElementIcon(String elementIcon) {
        this.elementIcon = elementIcon == null ? null : elementIcon.trim();
    }

    public String getElementLogo() {
        return elementLogo;
    }

    public void setElementLogo(String elementLogo) {
        this.elementLogo = elementLogo == null ? null : elementLogo.trim();
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName == null ? null : elementName.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getElementStatus() {
        return elementStatus;
    }

    public void setElementStatus(Boolean elementStatus) {
        this.elementStatus = elementStatus;
    }
}
