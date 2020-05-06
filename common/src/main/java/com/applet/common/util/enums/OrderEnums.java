package com.applet.common.util.enums;

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
    enum OperateStatus implements BaseEnum {
        DELETE(-2, "删除订单"),
        CANCEL( -1, "取消订单"),
        MAKE(0, "预下单"),
        LAUNCH_PAY(1, "发起支付"),
        SUBMIT( 2, "提交订单"),
        SUBMIT_FAIL(3, "提交失败"),
        MERCHANT_CONFIRM( 4, "商家接单"),
        MERCHANT_DELIVERY( 5, "商家配送"),
        INSTANT_DELIVERY(6, "即时配送"),
        LOGISTICS_DELIVERY(7, "物流配送"),
        CONFIRM_ARRIVE(8, "确认送达"),
        SIGN_FOR(9, "用户签收"),
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
        FAIL( -1, "订单失败"),
        WAIT(0, "预下单"),
        SUCCESS( 1, "订单成功"),
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
