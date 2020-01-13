package com.applet.apply.entity;

import java.io.Serializable;

public class ViewGoodsSellCount implements Serializable {
    private Integer id;

    private Integer appletId;

    private Integer userId;

    private String goodsName;

    private String coverSrc;

    private Double minPrice;

    private Double maxPrice;

    private Integer discount;

    private Long sellCount;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppletId() {
        return appletId;
    }

    public void setAppletId(Integer appletId) {
        this.appletId = appletId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Long getSellCount() {
        return sellCount;
    }

    public void setSellCount(Long sellCount) {
        this.sellCount = sellCount;
    }
}