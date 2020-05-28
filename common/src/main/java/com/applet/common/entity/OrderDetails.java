package com.applet.common.entity;

import java.io.Serializable;

/**
 * 订单详情
 */
public class OrderDetails implements Serializable {
    private Integer id;

    private Integer orderId;

    private Integer goodsId;

    private String goodsName;

    private Integer goodsDiscount;

    private Integer goodsSpecsId;

    private String goodsSpecsName;

    private String goodsSpecsType;

    private String goodsSpecsPic;

    private Integer goodsNumber;

    private Double sellPrice;

    private Double actualPrice;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public Integer getGoodsDiscount() {
        return goodsDiscount;
    }

    public void setGoodsDiscount(Integer goodsDiscount) {
        this.goodsDiscount = goodsDiscount;
    }

    public Integer getGoodsSpecsId() {
        return goodsSpecsId;
    }

    public void setGoodsSpecsId(Integer goodsSpecsId) {
        this.goodsSpecsId = goodsSpecsId;
    }

    public String getGoodsSpecsName() {
        return goodsSpecsName;
    }

    public void setGoodsSpecsName(String goodsSpecsName) {
        this.goodsSpecsName = goodsSpecsName == null ? null : goodsSpecsName.trim();
    }

    public String getGoodsSpecsType() {
        return goodsSpecsType;
    }

    public void setGoodsSpecsType(String goodsSpecsType) {
        this.goodsSpecsType = goodsSpecsType == null ? null : goodsSpecsType.trim();
    }

    public String getGoodsSpecsPic() {
        return goodsSpecsPic;
    }

    public void setGoodsSpecsPic(String goodsSpecsPic) {
        this.goodsSpecsPic = goodsSpecsPic == null ? null : goodsSpecsPic.trim();
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
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
}
