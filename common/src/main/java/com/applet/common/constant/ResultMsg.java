package com.applet.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务响应状态码信息 - Enum
 *
 * @author liangzhong
 * @date 2019/12/31 13:51
 */
@Getter
@AllArgsConstructor
public enum ResultMsg {

    /**
     * 业务响应状态码信息
     */

    SUCCESS("S0000", "操作成功"),
    ERROR("E0000", "系统异常"),
    FAIL("F0000", "操作失败"),
    FREQUENT_OPERATIONS("S10001", "操作频繁, 请稍后再试!!!"),
    ;

    private String code;
    private String message;
    }
