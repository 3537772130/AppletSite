package com.applet.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户商品推广图片
 */
public class UserGoodsSpreadImage implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer appletId;

    private Integer goodsId;

    private String spreadImage;

    private Date updateTime;

    private Boolean spreadStatus;

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

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getSpreadImage() {
        return spreadImage;
    }

    public void setSpreadImage(String spreadImage) {
        this.spreadImage = spreadImage == null ? null : spreadImage.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getSpreadStatus() {
        return spreadStatus;
    }

    public void setSpreadStatus(Boolean spreadStatus) {
        this.spreadStatus = spreadStatus;
    }
}
