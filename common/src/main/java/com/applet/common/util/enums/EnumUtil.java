package com.applet.common.util.enums;

import com.applet.common.util.enums.BaseEnum;

/**
 * 枚举工具类
 *
 * @author 谭良忠
 * @date 2020/1/3 16:14
 */
public class EnumUtil {

    /**
     * 通过code获取枚举
     *
     * @param code
     * @param enumClass
     * @param <T>
     * @return
     */
    public static <T extends BaseEnum> T getEnumByCode(Byte code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (each == null || code == null) {
                return null;
            }
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }

    /**
     * 通过code获取枚举
     *
     * @param code
     * @param enumClass
     * @param <T>
     * @return
     */
    public static <T extends BaseEnum> T getEnumByCode(String code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (each == null || code == null) {
                return null;
            }
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
