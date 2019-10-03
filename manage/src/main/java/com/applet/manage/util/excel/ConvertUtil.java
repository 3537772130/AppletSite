package com.applet.manage.util.excel;

import com.applet.manage.entity.ViewAppletInfo;
import com.applet.manage.util.Constants;
import jodd.datetime.JDateTime;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: SpringBootDemo
 * @description: Excel转换工具类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-09-12 11:30
 **/
public class ConvertUtil {

    /**
     * 小程序信息 转 Map
     *
     * @param record
     * @return
     */
    public static Map appletInfoToMap(ViewAppletInfo record) {
        Map map = new HashMap();
        map.put("1", record.getAppletCode());
        map.put("2", record.getAppletName());
        map.put("3", record.getMobile());
        map.put("4", record.getNickName());
        map.put("5", record.getProvince());
        map.put("6", record.getCity());
        map.put("7", record.getCounty());
        map.put("8", record.getIfRetail() ? "批发" : "零售");
        map.put("9", record.getIfSelling() ? "正则营业" : "暂停营业");
        map.put("10", new JDateTime(record.getUpdateTime()).toString(Constants.DATE_YMD));
        map.put("11", record.getStatus().intValue() == 1 ? "正常" : "禁用");
        return map;
    }
}
