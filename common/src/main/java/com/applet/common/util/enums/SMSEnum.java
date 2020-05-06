package com.applet.common.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 短信枚举
 */
public interface SMSEnum {

    /**
     * 短信通道
     */
    @Getter
    @AllArgsConstructor
    enum channel implements BaseEnum {
        ALIYUN( "ALIYUN", "阿里云"),
        ;
        private String code;
        private String name;
    }

    /**
     * 短信类型
     */
    @Getter
    @AllArgsConstructor
    enum type implements BaseEnum {
        BIND_BANK_CARD( "BIND_BANK_CARD", "绑定银行卡"),
        BIND_MOBILE( "BIND_MOBILE", "绑定手机号"),
        BIND_WE_CHANT( "BIND_WE_CHANT", "绑定微信"),
        BIND_APPLET( "BIND_APPLET", "绑定小程序"),
        BIND_APPLET_UPDATE( "BIND_APPLET_UPDATE", "换绑小程序"),
        UPDATE_PASS( "UPDATE_PASS", "修改密码"),
        REGISTER_ACCOUNT( "REGISTER_ACCOUNT", "注册账号"),
        LOGIN_ACCOUNT( "LOGIN_ACCOUNT", "登录账号"),
        ;
        private String code;
        private String name;
    }
}
