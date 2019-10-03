package com.applet.manage.util;

import jodd.datetime.JDateTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * @program: SpringBootDemo
 * @description: 时间处理工具类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-08-08 15:33
 **/
public class DateUtils {

    public final static String[] REPLACE_STRING = new String[]{"GMT+0800", "GMT+08:00"};

    public final static String SPLIT_STRING = "(中国标准时间)";

    /**
     * string 转 date  yyyy-MM-dd HH:mm:ss
     *
     * @param dateString
     * @return
     */
    public static Date strToDate(String dateString) {
        try {
            JDateTime time = new JDateTime(dateString);
            return time.convertToDate();
        } catch (Exception ex) {
            try {
                dateString = dateString.split(Pattern.quote(SPLIT_STRING))[0].replace(REPLACE_STRING[0], REPLACE_STRING[1]);
                SimpleDateFormat sf1 = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss z", Locale.US);
                Date date = sf1.parse(dateString);
                return date;
            } catch (Exception e) {
                throw new RuntimeException("时间转化格式错误" + "[dateString=" + dateString + "]" + "[FORMAT_STRING=" + Constants.DATE_TIME_JDK + "]");
            }
        }
    }
}
