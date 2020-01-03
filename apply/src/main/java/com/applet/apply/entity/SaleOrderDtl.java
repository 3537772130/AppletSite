package com.applet.apply.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单详情
 *
 * @author liangzhong.tan
 * @date 2019-12-31 10:11:00
 */
@Data
public class SaleOrderDtl {
    private Integer orderDtlId;

    private Integer orderId;

    private Integer goodsId;

    private String goodsName;

    private Integer goodsSpecsId;

    private String goodsSpecsName;

    private String goodsSpecsPic;

    private Integer saleQty;

    private BigDecimal amount;

    private BigDecimal salePrice;
}