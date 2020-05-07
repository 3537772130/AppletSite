package com.applet.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 小程序物料图片信息
 */
public class AppletMaterielInfo implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer appletId;

    private String materielImage;

    private Integer materielType;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

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

    public Integer getAppletId() {
        return appletId;
    }

    public void setAppletId(Integer appletId) {
        this.appletId = appletId;
    }

    public String getMaterielImage() {
        return materielImage;
    }

    public void setMaterielImage(String materielImage) {
        this.materielImage = materielImage == null ? null : materielImage.trim();
    }

    public Integer getMaterielType() {
        return materielType;
    }

    public void setMaterielType(Integer materielType) {
        this.materielType = materielType;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
