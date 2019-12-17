package com.applet.apply.util;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2019/12/17
 * Time: 19:01
 * To change this template use File | Settings | File Templates.
 * Description: 系统常量属性值
 */
public interface EnumUtil {

    /**
     * 短信模板类型类型
     */
    public enum SMSType{
        //绑定银行卡
        BIND_BANK_CARD,
        //绑定手机号
        BIND_MOBILE,
        //修改密码
        UPDATE_PASS,
        //注册账号
        REGISTER_ACCOUNT,
        //登录账号
        LOGIN_ACCOUNT
    }

    /**
     * 短信发送通道
     */
    public enum SMSChannel{
        //阿里云
        ALIYUN
    }
}
