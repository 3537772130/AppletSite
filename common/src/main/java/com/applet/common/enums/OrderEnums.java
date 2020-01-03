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

    @Getter
    @AllArgsConstructor
    enum OrderStatus implements BaseEnum {
        /**
         * 订单状态
         */
        CANCEL((byte) 0, "取消"),
        PENDING((byte) 1, "待处理"),
        MERCHANT_CONFIRM((byte) 2, "商户确认"),
        REFUSE((byte) 3, "拒绝"),
        STARTS_HIPPING((byte) 4, "开始配送"),
        CONFIRM_DELIVERY((byte) 5, "确认送达"),
        RECEIVED((byte) 6, "已签收"),
        ;

        private byte code;
        private String name;

    }
}