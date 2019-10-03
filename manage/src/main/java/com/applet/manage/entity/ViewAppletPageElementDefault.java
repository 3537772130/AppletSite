package com.applet.manage.entity;

import com.applet.manage.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class ViewAppletPageElementDefault implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer pageId;
    private String elementLogo;
    private String elementIcon;
    private String elementName;
    private Integer contentId;
    private String elementJson;
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

    public String getElementLogo() {
        return elementLogo;
    }

    public void setElementLogo(String elementLogo) {
        this.elementLogo = elementLogo == null ? null : elementLogo.trim();
    }

    public String getElementIcon() {
        return elementIcon;
    }

    public void setElementIcon(String elementIcon) {
        this.elementIcon = elementIcon == null ? null : elementIcon.trim();
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName == null ? null : elementName.trim();
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getElementJson() {
        return elementJson;
    }

    public void setElementJson(String elementJson) {
        this.elementJson = elementJson == null ? null : elementJson.trim();
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
