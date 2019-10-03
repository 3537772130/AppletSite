package com.applet.manage.util;

import java.util.List;

/**
 * @program: SpringBootDemo
 * @description: 判空工具类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-06-18 17:24
 **/
public class NullUtil {

    /**
     * 获取站点域名
     *
     * @return
     */
    public static String getWebSite() {
        if (PropertiesLoadUtils.isRun()) {
            return "https://www.wscxy.xin/applet";
        } else {
            return "http://192.168.1.52:6060";
        }
    }

    /**
     * 截取手机号前三后四中间****
     * 13193783375 ==》 131****3375
     *
     * @param mobile
     * @return
     */
    public static String subMobileQ3H4(String mobile) {
        if (isNotNullOrEmpty(mobile) && mobile.length() >= 11) {
            return mobile.substring(0, 3) + "****" + mobile.substring(mobile.length() - 4, mobile.length());
        } else {
            return "****";
        }
    }

    /**
     * 截取银行卡号前六后四中间****
     *
     * @param card
     * @return
     */
    public static String subBankCardQ6H4(String card) {
        if (isNotNullOrEmpty(card) && card.length() >= 12) {
            return card.substring(0, 6) + "****" + card.substring(card.length() - 4, card.length());
        } else {
            return "****";
        }
    }

    /**
     * 从后往前截取字符串长度
     *
     * @param str
     * @param length
     * @return
     */
    public static String subBehind(String str, int length) {
        if (isNotNullOrEmpty(str) && str.length() >= 1) {
            return str.substring(str.length() - length, str.length());
        } else {
            return "***";
        }
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(String str) {
        if (null == str){
            return true;
        } else {
            return str.trim().equals("") ? true : false;
        }
    }

    /**
     * 判断整型是否为空
     * @param num
     * @return
     */
    public static boolean isNullOrEmpty(Integer num) {
        return null == num ? true:false;
    }

    /**
     * 判断List对象是否为空
     * @param list
     * @return
     */
    public static boolean isNullOrEmpty(List list) {
        return (null != list && list.size() > 0) ? false : true;
    }

    /**
     * 判断浮点型是否为空
     * @param num
     * @return
     */
    public static boolean isNullOrEmpty(Double num){
        return null == num ? true : false;
    }

    /**
     * 判断布尔型是否为空
     * @param bool
     * @return
     */
    public static boolean isNullOrEmpty(Boolean bool){
        return null == bool ? true : false;
    }


    /**
     * 判断字符串是否不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotNullOrEmpty(String str) {
        return !isNullOrEmpty(str);
    }


    /**
     * 判断整型是否不为空
     *
     * @param num
     * @return
     */
    public static boolean isNotNullOrEmpty(Integer num) {
        return null == num ? false : true;
    }

    /**
     * 判断List对象是否不为空
     *
     * @param list
     * @return
     */
    public static boolean isNotNullOrEmpty(List list) {
        return (null != list && list.size() > 0) ? true : false;
    }

    /**
     * 判断浮点型是否不为空
     * @param num
     * @return
     */
    public static boolean isNotNullOrEmpty(Double num){
        return null == num ? false : true;
    }

    /**
     * 判断布尔型是否不为空
     * @param bool
     * @return
     */
    public static boolean isNotNullOrEmpty(Boolean bool){
        return null == bool ? false : true;
    }


    /**
     *    * 判断传进来的字符串，是否
     *    * 大于指定的字节，如果大于递归调用
     *    * 直到小于指定字节数 ，一定要指定字符编码，因为各个系统字符编码都不一样，字节数也不一样
     *    * @param s
     *    *      原始字符串
     *    * @param num
     *    *      传进来指定字节数
     *    * @return String 截取后的字符串
     *    * @throws UnsupportedEncodingException
     *    
     */
    public static String cusString(String inputStr, int length) {
        StringBuffer result = new StringBuffer();
        int currLength = 0;
        for (char a : inputStr.toCharArray()) {
            if (currLength + Character.toString(a).getBytes().length > length) {
                break;
            } else {
                currLength += Character.toString(a).getBytes().length;
                result.append(a);
            }
        }
        return result.toString();
    }

    /**
     * 截取用户姓名,显示第一个字+***
     *
     * @param userName
     * @return
     */
    public static String getSubUserName(String userName) {
        if (isNotNullOrEmpty(userName) && userName.length() >= 1) {
            StringBuilder builder = new StringBuilder(userName.substring(0, 1));
            int length = userName.length();
            for (int i = 1; i < length; i++) {
                builder.append("*");
            }
            return builder.toString();
        } else {
            return "****";
        }
    }

    /**
     * 截取显示用户身份证号,前3+***+后4位
     *
     * @param idNumber
     * @return
     */
    public static String subIdNumber(String idNumber) {
        if (isNotNullOrEmpty(idNumber) && idNumber.length() >= 18) {
            StringBuilder builder = new StringBuilder(idNumber.substring(0, 3));
            int length = idNumber.length();
            for (int i = 0; i < length - 7; i++) {
                builder.append("*");
            }
            builder.append(idNumber.substring(14, length));
            return builder.toString();
        } else {
            return "****";
        }
    }
}
