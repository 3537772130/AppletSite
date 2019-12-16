package com.applet.apply.entity;

import java.io.Serializable;

public class ViewGoodsSpecs implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer goodsId;

    private String specsSrc;

    private String specsText;

    private Double sellPrice;

    private Double actualPrice;

    private Integer discount;

    private String discountDescribe;

    private Integer specsIndex;

    private Boolean specsStatus;

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

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getSpecsSrc() {
        return specsSrc;
    }

    public void setSpecsSrc(String specsSrc) {
        this.specsSrc = specsSrc == null ? null : specsSrc.trim();
    }

    public String getSpecsText() {
        return specsText;
    }

    public void setSpecsText(String specsText) {
        this.specsText = specsText == null ? null : specsText.trim();
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getDiscountDescribe() {
        return discountDescribe;
    }

    public void setDiscountDescribe(String discountDescribe) {
        this.discountDescribe = discountDescribe == null ? null : discountDescribe.trim();
    }

    public Integer getSpecsIndex() {
        return specsIndex;
    }

    public void setSpecsIndex(Integer specsIndex) {
        this.specsIndex = specsIndex;
    }

    public Boolean getSpecsStatus() {
        return specsStatus;
    }

    public void setSpecsStatus(Boolean specsStatus) {
        this.specsStatus = specsStatus;
    }
}