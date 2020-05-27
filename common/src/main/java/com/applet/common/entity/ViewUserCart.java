package com.applet.common.entity;

import com.applet.common.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户购物车信息 - 视图
 */
public class ViewUserCart implements Serializable {
    private Integer id;

    private Integer wxId;

    private Integer appletId;

    private Integer goodsId;

    private String goodsName;

    private Integer discount;

    private Boolean ifCoupon;

    private String describeStr;

    private Integer specsId;

    private String specsSrc;

    private String specsName;

    private String specsType;

    private Double sellPrice;

    private Integer amount;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_TIME_JDK)
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWxId() {
        return wxId;
    }

    public void setWxId(Integer wxId) {
        this.wxId = wxId;
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Boolean getIfCoupon() {
        return ifCoupon;
    }

    public void setIfCoupon(Boolean ifCoupon) {
        this.ifCoupon = ifCoupon;
    }

    public String getDescribeStr() {
        return describeStr;
    }

    public void setDescribeStr(String describeStr) {
        this.describeStr = describeStr == null ? null : describeStr.trim();
    }

    public Integer getSpecsId() {
        return specsId;
    }

    public void setSpecsId(Integer specsId) {
        this.specsId = specsId;
    }

    public String getSpecsSrc() {
        return specsSrc;
    }

    public void setSpecsSrc(String specsSrc) {
        this.specsSrc = specsSrc == null ? null : specsSrc.trim();
    }

    public String getSpecsName() {
        return specsName;
    }

    public void setSpecsName(String specsName) {
        this.specsName = specsName == null ? null : specsName.trim();
    }

    public String getSpecsType() {
        return specsType;
    }

    public void setSpecsType(String specsType) {
        this.specsType = specsType == null ? null : specsType.trim();
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
