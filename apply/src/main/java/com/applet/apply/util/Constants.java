package com.applet.apply.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: SpringBootDemo
 * @description: 常量
 * @author: Mr.ZhouHuaHu
 * @create: 2019-06-19 17:18
 **/
public class Constants {
    // 时间格式
    public static final String DATE_TIME_JDK = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_JODD = "YYYY-MM-DD hh:mm:ss";
    public static final String DATE_TIME_YMDHM = "YYYY-MM-DD hh:mm";
    public static final String DEFAULT_DATE_FORMAT_STAMP = "YYYYMMDDhhmmss";
    public static final String DATE_YMD = "YYYY-MM-DD";
    public static final String DATE_TIME_YMD = "yyyy-MM-dd";
    public static final String DATE_TIME_ZH = "YYYY年MM月DD日";
    public static final String JODD_FMT_YYYYMMDD = "YYYYMMDD";
    public static final String JODD_FMT_HHMMSS = "hhmmss";
    //图形验证码
    public static final String FIGURE_CODE = "figure_code";

    // 小程序上传
    // logo路径
    public static final String APPLET_UPLOAD_ID = "APPLET_UPLOAD_ID";


    // 图片上传格式
    public static final String UPLOAD_FILE_TYPE_IMAGE = "image/png,image/jpeg";
    // 系统压缩文件格式
    public static final String UPLOAD_FILE_TYPE_ZIP = "application/x-zip-compressed,application/x-7z-compressed,application/x-gzip";//上传模板压缩文件类型
    // 视频上传格式
    public static final String UPLOAD_FILE_TYPE_VIDEO = "video/mpeg,video/mp4,video/3gpp,video/quicktime,video/x-ms-wmv";//上传视频文件类型
    // 音频上传格式
    public static final String UPLOAD_FILE_TYPE_AUDIO = "audio/mpeg,audio/mp3,audio/mp4";

    // 地域信息集合
    // v-k：ID => NAME
    public static final List<Map> REGION_MAP_TO_ID = new ArrayList<>();
    // v-k：NAME => NAME
    public static final List<Map> REGION_MAP_TO_NAME = new ArrayList<>();

    //绑定手机号图形验证码
    public static final String BIND_MOBILE_FIGURE_CODE = "bindMobile";
    //添加绑定手机号
    public static final String BIND_MOBILE_TO_ADD = "bindWx";
    //修改绑定手机号
    public static final String BIND_MOBILE_TO_UPDATE = "updateBindMobile";
    //绑定微信短信模板类型
    public static final String BIND_MOBILE_OPERATION = "bindWx";
    //绑定微信短信验证码类型
    public static final String BIND_MOBILE_TYPE = "APPLET";
    //短信每日发送次数
    public static final Integer SMS_CODE_AMOUNT = 3;
}
