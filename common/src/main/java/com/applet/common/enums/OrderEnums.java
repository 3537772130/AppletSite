package com.applet.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 销售订单枚举
 *
 * @author 谭良忠
 * @date 2020/1/3 16:14
 */
public interface OrderEnums {

    /**
     * 支付通道
     */
    @Getter
    @AllArgsConstructor
    enum PayChannel implements BaseEnum {

        WX_JSAPI( "JSAPI", "WX_JSAPI"),
        WX_NATIVE( "NATIVE", "WX_NATIVE"),
        WX_APP( "APP", "WX_APP"),
        ;

        private String code;
        private String name;

    }

    /**
     * 支付状态
     */
    @Getter
    @AllArgsConstructor
    enum PayStatus implements BaseEnum {

        WAIT( 0, "待付款"),
        SUCCESS( 1, "付款成功"),
        FAIL( -1, "付款失败"),
        ;

        private Integer code;
        private String name;

    }

    /**
     * 订单状态
     */
    @Getter
    @AllArgsConstructor
    enum OrderStatus implements BaseEnum {

        CANCEL( 0, "取消"),
        PENDING( 1, "待处理"),
        MERCHANT_CONFIRM( 2, "商户确认"),
        DENIAL( 3, "拒绝"),
        STARTS_HIPPING( 4, "开始配送"),
        CONFIRM_DELIVERY(5, "确认送达"),
        RECEIVED(6, "已签收"),
        ;

        private Integer code;
        private String name;

    }

    /**
     * 用户优惠券状态
     */
    @Getter
    @AllArgsConstructor
    enum UserCouponStatus implements BaseEnum {

        UNUSED(0, "未使用"),
        USING(1, "使用中"),
        USED(2, "已使用"),
        ;

        private Integer code;
        private String name;

    }
}
