package com.applet.common.bo;

import com.applet.common.vo.SaleOrderDtlVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 谭良忠
 * @date 2019/12/31 14:17
 */
@Data
@ApiModel("销售订单")
public class SaleOrderBo {

    // TODO 支付方式

    @ApiModelProperty("订单Id")
    private Integer orderId;

    @ApiModelProperty("订单No")
    private String orderNo;

    @ApiModelProperty("下单用户")
    private Integer userId;

    @ApiModelProperty("收货人姓名")
    private String receiverName;

    @ApiModelProperty("收货人手机")
    private String receiverPhone;

    @ApiModelProperty("详细地址")
    private String detailAddr;

    @ApiModelProperty("经度")
    private Double lat;

    @ApiModelProperty("维度")
    private Double lon;

    @ApiModelProperty("详细地址")
    private BigDecimal carriersFee;

    @ApiModelProperty("小程序Id")
    private Integer appletId;

    @ApiModelProperty("订单状态(0-取消,1-待处理(用户提交订单后,待商户处理),2-商户确认,3-拒绝,4-开始配送,5-确认送达,6-签收)")
    private Byte orderStatus;

    @ApiModelProperty("订单状态Cn(0-取消,1-待处理(用户提交订单后,待商户处理),2-商户确认,3-拒绝,4-开始配送,5-确认送达,6-签收)")
    private String orderStatusCn;

    @ApiModelProperty("订单金额")
    private BigDecimal totalAmount;

    @ApiModelProperty("优惠金额")
    private BigDecimal ticketAmount;

    @ApiModelProperty("优惠券")
    private Integer userCouponId;

    @ApiModelProperty("订单备注")
    private String orderRemark;

    @ApiModelProperty("订单取消原因")
    private String cancelReason;

    @ApiModelProperty("订单拒绝原因")
    private String denialReason;

    @ApiModelProperty("购物车Ids")
    private List<Integer> dtls;

//    @ApiModelProperty("订单详情")
//    private List<SaleOrderDtlVo> dtls;

}
