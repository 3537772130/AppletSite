package com.applet.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单详情 - Vo
 *
 * @author liangzhong.tan
 * @date 2019-12-31 10:11:00
 */
@Data
@ApiModel("订单详情-Vo")
public class SaleOrderDtlVo {

    @ApiModelProperty("Id")
    private Integer orderDtlId;

    @ApiModelProperty("订单Id")
    private Integer orderId;

    @ApiModelProperty("商品Id")
    private Integer goodsId;

    @ApiModelProperty("商品名称")
    private String goodsName;

    @ApiModelProperty("商品规格Id")
    private Integer goodsSpecsId;

    @ApiModelProperty("商品规格名称")
    private String goodsSpecsName;

    @ApiModelProperty("商品规格图片")
    private String goodsSpecsPic;

    @ApiModelProperty("数量")
    private Integer saleQty;

    @ApiModelProperty("折后价格")
    private BigDecimal discountPrice;

    @ApiModelProperty("销售价格-原价")
    private BigDecimal salePrice;
}