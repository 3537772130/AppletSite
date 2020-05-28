package com.applet.common.util;

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
    public static final String DATE_TIME_JODD = "YYYY-MM-DD HH:mm:ss";
    public static final String DATE_TIME_YMDHM = "YYYY-MM-DD hh:mm";
    public static final String DEFAULT_DATE_FORMAT_STAMP = "YYYYMMDDhhmmss";
    public static final String DATE_YMD = "YYYY-MM-DD";
    public static final String DATE_YMD_JDK = "yyyy/MM/dd";
    public static final String DATE_YMD_JODD = "yyyy-MM-dd";
    public static final String DATE_TIME_ZH = "yyyy年MM月dd日";
    public static final String DATE_TIME_ZH_HM = "YYYY年MM月dd日 hh:mm";
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
    // 客户端的外网IP
    public static final String CLIENT_PUBLIC_IP = "CLIENT_PUBLIC_IP";
    // 客户端所在城市编码
    public static final String CLIENT_CITY_CODE = "CLIENT_CITY_CODE";

    // 小程序上传
    // logo路径
    public static final String APPLET_UPLOAD_ID = "APPLET_UPLOAD_ID";


    // 商品规格数量
    public static final int GOODS_SPECS_COUNT = 20;
    // 商品类型数量
    public static final int GOODS_TYPE_COUNT = 50;
    // 图片上传格式
    public static final String UPLOAD_FILE_TYPE_IMAGE = "image/png,image/jpeg,image/jpg";
    // 系统压缩文件格式
    public static final String UPLOAD_FILE_TYPE_ZIP = "application/x-zip-compressed,application/x-7z-compressed,application/x-gzip";//上传模板压缩文件类型
    // 视频上传格式
    public static final String UPLOAD_FILE_TYPE_VIDEO = "video/mp4";//上传视频文件类型
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

    // 绑定手机号图形
    public static final String BIND_MOBILE_FIGURE_CODE = "bindMobile";
    // 短信每日发送次数
    public static final Integer SMS_CODE_AMOUNT = 3;


    // 通知类型
    // 系统通知
    public static final Integer RELATION_TYPE_SYSTEM = 1;
    // 评论回复通知
    public static final Integer RELATION_TYPE_COMMENT = 2;

    // 系统通知类型
    // 系统公告
    public static final Integer SYSTEM_NOTICE_TYPE_PUBLIC = 1;
    // 新用户推送
    public static final Integer SYSTEM_NOTICE_TYPE_NEW_USER = 2;
    // 普通推送
    public static final Integer SYSTEM_NOTICE_TYPE_PLAIN = 3;
    public static final Map<Integer, String> SYSTEM_NOTICE_TYPE_MAP = new HashMap<Integer, String>() {
        {
            put(SYSTEM_NOTICE_TYPE_PUBLIC, "系统公告");
            put(SYSTEM_NOTICE_TYPE_NEW_USER, "新用户推送");
            put(SYSTEM_NOTICE_TYPE_PLAIN, "普通推送");
        }
    };

    public static final Map<Integer, String> MATERIEL_TYPE_MAP = new HashMap<Integer, String>() {
        {
            put(1, "扫码_搜索联合传播样式");
            put(2, "搜索框传播样式");
            put(3, "二维码");
        }
    };

    public static final String TEST_ORDER_REMARK = "商家支付测试";
}

