package com.applet.common.util;

import com.alibaba.fastjson.JSONObject;
import com.applet.common.util.http.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 腾讯地图工具类
 * @author zhouhuahu
 * @date 2018/9/26
 */
public class TencentLocationUtils {

    /**
     * @Description: 通过经纬度获取位置
     * @Param: [log, lat]
     * @return: java.lang.String
     * @Author: Alan
     * @Date: 2018/6/1 21:14
     */
    public static Map<String, Object> getLocation(String lon, String lat) {
        String url = "http://apis.map.qq.com/ws/geocoder/v1/?location=" + lat + "," + lon
                + "&key=IT3BZ-D5NHD-Z3E4I-HNVDW-CFXWS-F3BQZ&get_poi=1&poi_options=radius=200;page_size=5;page_index=1;policy=2";
        String result = HttpUtil.doGet(url);
//        System.out.println(result);
        // 转JSON格式
        JSONObject jsonObject = JSONObject.parseObject(result).getJSONObject("result");
        // 获取地址（行政区划信息） 包含有国籍，省份，城市
        JSONObject adInfo = jsonObject.getJSONObject("ad_info");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("nation", adInfo.get("nation"));
        resultMap.put("nationCode", adInfo.get("nation_code"));
        resultMap.put("province", adInfo.get("province"));
        resultMap.put("provinceCode", adInfo.get("adcode"));
        resultMap.put("city", adInfo.get("city"));
        resultMap.put("district", adInfo.get("district"));
        resultMap.put("cityCode", adInfo.get("city_code"));
        return resultMap;
    }

    public static void main(String[] args) {
        // 测试
        String lng = "112.62701";//经度
        String lat = "42.83221";//维度
        Map<String, Object> map = getLocation(lng, lat);
        System.out.println("国   籍：" + map.get("nation"));
        System.out.println("国家代码：" + map.get("nationCode"));
        System.out.println("省   份：" + map.get("province"));
        System.out.println("省份代码：" + map.get("provinceCode"));
        System.out.println("城   市：" + map.get("city"));
        System.out.println("区   县：" + map.get("district"));
        System.out.println("城市代码：" + map.get("cityCode"));
    }
}
