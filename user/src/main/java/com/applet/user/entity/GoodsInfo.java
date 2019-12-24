package com.applet.user.entity;

import com.applet.common.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class GoodsInfo implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer typeId;

    private String goodsName;

    private String coverSrc;

    private static final long serialVersionUID = 1L;
    private Double minPrice;
    private Double maxPrice;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_TIME_JDK)
    private Date updateTime;

    private Boolean status;
    private Integer goodsIndex;

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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getCoverSrc() {
        return coverSrc;
    }

    public void setCoverSrc(String coverSrc) {
        this.coverSrc = coverSrc == null ? null : coverSrc.trim();
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getGoodsIndex() {
        return goodsIndex;
    }

    public void setGoodsIndex(Integer goodsIndex) {
        this.goodsIndex = goodsIndex;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
