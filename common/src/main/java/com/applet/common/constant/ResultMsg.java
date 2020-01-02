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

    SUCCESS("1", "操作成功"),
    ERROR("-1", "系统异常"),
    FAIL("F0000", "操作失败"),
    ;

    private String code;
    private String message;
    }
