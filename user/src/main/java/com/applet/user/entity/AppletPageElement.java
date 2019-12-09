package com.applet.user.entity;

import java.io.Serializable;
import java.util.Date;

public class AppletPageElement implements Serializable {
    private Integer id;

    private Integer pageId;

    private Integer typeId;

    private String elementLogo;

    private String elementName;

    private Integer elementIndex;

    private Date updateTime;

    private Boolean elementStatus;

    private static final long serialVersionUID = 1L;

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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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

    public Integer getElementIndex() {
        return elementIndex;
    }

    public void setElementIndex(Integer elementIndex) {
        this.elementIndex = elementIndex;
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