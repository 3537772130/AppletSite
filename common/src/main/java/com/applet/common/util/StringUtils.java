package com.applet.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: SpringBootDemo
 * @description: 字符串工具类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-06-18 17:25
 **/
public class StringUtils {

    /**
     * @return boolean 返回类型
     * @throws
     * @Title: isNumeric
     * @Description: TODO(是否是整数)
     */
    public static boolean isNumeric(String value) {
        Pattern pattern = Pattern.compile("^\\-?[0-9]+$");
        return pattern.matcher(value).matches();
    }

    public static boolean isFloat(String value) {
        Pattern pattern = Pattern.compile("^([-]|[0-9])[0-9]*(\\.\\d*)?$");
        return pattern.matcher(value).matches();
    }

    public static boolean isEmail(String value) {
        Pattern pattern = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
        return pattern.matcher(value).matches();
    }

    public static boolean isUnicode(String value) {
        Pattern pattern = Pattern.compile("^[\\u4E00-\\u9FA5\\uE815-\\uFA29]+$");
        return pattern.matcher(value).matches();
    }

    /**
     * @param value
     * @return boolean 返回类型
     * @throws
     * @Title: IsIpAddress
     * @Description: TODO(是否是IP地址)
     */
    public static boolean isIpAddress(String value) {
        Pattern pattern = Pattern.compile("^(\\d(25[0-5]|2[0-4][0-9]|1?[0-9]?[0-9])\\d\\.){3}\\d(25[0-5]|2[0-4][0-9]|1?[0-9]?[0-9])\\d$");
        return pattern.matcher(value).matches();
    }

    public static boolean isUrl(String value) {
        Pattern pattern = Pattern.compile("^(http|https|ftp|rtsp|mms):(\\/\\/|\\\\\\\\)[A-Za-z0-9%\\-_@]+\\.[A-Za-z0-9%\\-_@]+[A-Za-z0-9\\.\\/=\\?%\\-&_~`@:\\+!;]*$");
        return pattern.matcher(value).matches();
    }

    public static boolean isIdentityCard(String value) {

        Pattern pattern = Pattern.compile("^(^\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$");
        return pattern.matcher(value).matches();
    }

    public static boolean isMobileNumber(String value, boolean isRestrict) {
        Pattern pattern = Pattern.compile(isRestrict ? "^[1][3-8]\\d{9}$" : "^[1]\\d{10}$");
        return pattern.matcher(value).matches();
    }

    public static boolean isMobileNumber(String value) {
        return isMobileNumber(value, false);
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            System.out.println(e.getMessage());
            convertSuccess = false;
        }
        return convertSuccess;
    }

    public static boolean isChineseName(String str) {
        Pattern pattern = Pattern.compile("^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]){2,5}$");

        Matcher matcher = pattern.matcher(str);

        if (matcher.find()) {
            return true;
        }
        return false;
    }

    /**
     * @return boolean 返回类型
     * @throws
     * @Title: isNumeric
     * @Description: TODO(是否是整数)
     */
    public static boolean isNumericForLength(String value, int length) {
        Pattern pattern = Pattern.compile("^\\-?[0-9]{" + length + "}$");
        return pattern.matcher(value).matches();
    }

    public static void main(String[] args) {
        System.out.println(isNumericForLength("111", 3));
    }
}
