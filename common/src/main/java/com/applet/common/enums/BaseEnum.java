package com.applet.common.enums;

/**
 * 枚举基类
 *
 * @author 谭良忠
 * @date 2020/1/3 16:14
 */
public interface BaseEnum {
    /**
     * 获取编码
     *
     * @return
     */
    <T> T getCode();

    /**
     * 获取名称
     *
     * @return
     */
    String getName();
}