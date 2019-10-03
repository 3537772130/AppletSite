package com.applet.user.entity;

import java.io.Serializable;

public class GoodsType implements Serializable {
    private Integer id;

    private Integer userId;

    private String typeLogo;

    private String typeName;

    private static final long serialVersionUID = 1L;

    private Boolean typeStatus;
    private Integer typeIndex;

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

    public String getTypeLogo() {
        return typeLogo;
    }

    public void setTypeLogo(String typeLogo) {
        this.typeLogo = typeLogo == null ? null : typeLogo.trim();
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
