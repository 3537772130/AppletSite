package com.applet.manage.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: SpringBootDemo
 * @description: 正则校验工具类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-06-19 17:15
 **/
public class RegularUtil {

    /**
     * 身份证格式
     * @param idCard
     * @return
     */
    public static boolean checkIdCard(String idCard){
        String reg = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(idCard);
        return m.matches();
    }

    /**
     * 正则校验手机号格式
     * @param mobile
     * @return
     */
    public static boolean checkMobile(String mobile){
        String reg = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,3,5-9]))\\d{8}$";
        if(mobile.length() != 11){
            return false;
        }else{
            Pattern p = Pattern.compile(reg);
            Matcher m = p.matcher(mobile);
            return m.matches();
        }
    }

    /**
     * 正则校验邮箱格式
     * @param email
     * @return
     */
    public static boolean checkEmail(String email){
        String reg = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 正则校验营业执照编码
     * @param licenseCode
     * @return
     */
    public static boolean checkLicenseCode(String licenseCode){
        String reg = "(^(?:(?![IOZSV])[\\dA-Z]){2}\\d{6}(?:(?![IOZSV])[\\dA-Z]){10}$)|(^\\d{15}$)";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(licenseCode);
        return m.matches();
    }

    /**
     * 正则校验支付密码6位纯数字
     * @param paymentCode
     * @return
     */
    public static boolean checkPaymentCode(String paymentCode){
        String reg = "^[0-9]{6}$";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(paymentCode);
        return m.matches();
    }

    /**
     * 正则校验名称不能含特殊字符
     *
     * @param name
     * @return
     */
    public static boolean checkName(String name) {
        String reg = "^([\\u4e00-\\u9fa5]|[\\uFE30-\\uFFA0]|[\\w])+$";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(name);
        return m.matches();
    }

    /**
     * 正则校验字符串是否纯中文
     *
     * @param str
     * @return
     */
    public static boolean checkPureChinese(String str) {
        String reg = "^[\\u4e00-\\u9fa5]+$";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
