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
     * 订单状态
     */
    @Getter
    @AllArgsConstructor
    enum OrderStatus implements BaseEnum {

        CANCEL((byte) 0, "取消"),
        PENDING((byte) 1, "待处理"),
        MERCHANT_CONFIRM((byte) 2, "商户确认"),
        DENIAL((byte) 3, "拒绝"),
        STARTS_HIPPING((byte) 4, "开始配送"),
        CONFIRM_DELIVERY((byte) 5, "确认送达"),
        RECEIVED((byte) 6, "已签收"),
        ;

        private Byte code;
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