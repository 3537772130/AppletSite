package com.applet.apply.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 销售订单
 *
 * @author liangzhong.tan
 * @date 2019-12-31 10:11:00
 */
@Data
public class SaleOrderDoc {
    private Integer orderId;

    private Date gmtCreated;

    private Date gmtModified;

    private String orderNo;

    private Integer userId;

    private String receiverName;

    private String receiverPhone;

    private String detailAddr;

    private Double lat;

    private Double lon;

    private BigDecimal carriersFee;

    private Integer appletId;

    private Byte orderStatus;

    private String orderStatusCn;

    private BigDecimal totalAmount;

    private BigDecimal ticketAmount;

    private Integer userCouponId;

    private String orderRemark;

    private String cancelReason;

    private String denialReason;

    private Byte payType;

    public SaleOrderDoc(String orderNo, Integer userId, String receiverName, String receiverPhone, String detailAddr, Double lat, Double lon, BigDecimal carriersFee, Integer appletId, Byte orderStatus, String orderStatusCn, BigDecimal totalAmount, BigDecimal ticketAmount, Integer userCouponId, Byte payType) {
        this.orderNo = orderNo;
        this.userId = userId;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.detailAddr = detailAddr;
        this.lat = lat;
        this.lon = lon;
        this.carriersFee = carriersFee;
        this.appletId = appletId;
        this.orderStatus = orderStatus;
        this.orderStatusCn = orderStatusCn;
        this.totalAmount = totalAmount;
        this.ticketAmount = ticketAmount;
        this.userCouponId = userCouponId;
        this.payType = payType;
    }
}