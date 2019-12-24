package com.applet.user.entity;

import java.io.Serializable;

public class ViewGoodsSpecs implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer goodsId;

    private String specsSrc;

    private String specsText;

    private Double sellPrice;

    private String describeStr;

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

    public String getDescribeStr() {
        return describeStr;
    }

    public void setDescribeStr(String describeStr) {
        this.describeStr = describeStr == null ? null : describeStr.trim();
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