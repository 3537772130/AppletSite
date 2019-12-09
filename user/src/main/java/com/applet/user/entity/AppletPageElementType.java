package com.applet.user.entity;

import java.io.Serializable;

public class AppletPageElementType implements Serializable {
    private Integer id;

    private Integer pageId;

    private String typeName;

    private Integer typeIndex;

    private Boolean typeStatus;

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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Integer getTypeIndex() {
        return typeIndex;
    }

    public void setTypeIndex(Integer typeIndex) {
        this.typeIndex = typeIndex;
    }

    public Boolean getTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(Boolean typeStatus) {
        this.typeStatus = typeStatus;
    }
}