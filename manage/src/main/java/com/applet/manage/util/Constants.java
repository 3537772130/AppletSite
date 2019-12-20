package com.applet.manage.util;

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
    // 后台管理用户信息
    public static final String WEB_MANAGER_INFO = "EXAMPLE_WEB_MANAGER_INFO";
    // 后台管理用户菜单
    public static final String WEB_MANAGER_MENU_LIST = "EXAMPLE_WEB_MENU_LIST";
    // 前端普通用户信息
    public static final String VUE_USER_INFO = "EXAMPLE_VUE_USER_INFO";

    // 小程序上传
    // logo路径
    public static final String APPLET_UPLOAD_ID = "APPLET_UPLOAD_ID";


    // 商品规格数量
    public static final int GOODS_SPECS_COUNT = 20;
    // 商品类型数量
    public static final int GOODS_TYPE_COUNT = 15;
    // 图片上传格式
    public static final String UPLOAD_FILE_TYPE_IMAGE = "image/png,image/jpeg,image/jpg";
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

    // 管理员角色
    // v-k: roleId => 权限集合
    public static final Map<Integer, Map> MANAGER_ROLE_AUTH_MAP = new HashMap<>();
    // 权限标识集合
    public static final Map<String, String> MANAGER_ROLE_AUTH_LOGO_MAP = new HashMap<>();


    public static final Map<String, String> MONTH_MAP = new HashMap<String, String>() {
        {
            put("01", "一月");
            put("02", "二月");
            put("03", "三月");
            put("04", "四月");
            put("05", "五月");
            put("06", "六月");
            put("07", "七月");
            put("08", "八月");
            put("09", "九月");
            put("10", "十月");
            put("11", "十一月");
            put("12", "十二月");
        }
    };

}
