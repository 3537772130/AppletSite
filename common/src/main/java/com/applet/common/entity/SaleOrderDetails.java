package com.applet.common.entity;

import com.applet.common.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单明细
 */
public class SaleOrderDetails implements Serializable {
    private Integer orderDtlId;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_TIME_JDK)
    private Date gmtCreated;

    private Integer orderId;

    private Integer goodsId;

    private String goodsName;

    private Integer goodsSpecsId;

    private String goodsSpecsName;

    private String goodsSpecsPic;

    private Integer saleQty;

    private BigDecimal discountPrice;

    private BigDecimal salePrice;

    private static final long serialVersionUID = 1L;

    public Integer getOrderDtlId() {
        return orderDtlId;
    }

    public void setOrderDtlId(Integer orderDtlId) {
        this.orderDtlId = orderDtlId;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
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

    public String getGoodsSpecsPic() {
        return goodsSpecsPic;
    }

    public void setGoodsSpecsPic(String goodsSpecsPic) {
        this.goodsSpecsPic = goodsSpecsPic == null ? null : goodsSpecsPic.trim();
    }

    public Integer getSaleQty() {
        return saleQty;
    }

    public void setSaleQty(Integer saleQty) {
        this.saleQty = saleQty;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
}
