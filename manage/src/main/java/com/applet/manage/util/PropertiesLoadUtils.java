package com.applet.manage.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @program: SpringBootDemo
 * @description: 环境工具类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-06-18 17:27
 **/
public class PropertiesLoadUtils {

    private static Logger log = LoggerFactory.getLogger(PropertiesLoadUtils.class);

    // 定义配置文件路径
    private static final String propertyPath = "/config/param.properties";

    /**
     * 是否正式环境
     * @return
     */
    public static boolean isRun(){
        String runtime =  readValue("environment");
        if (null != runtime && runtime.equals("run")) {
            //当前为生产环境
            return true;
        }else{
            //当前为测试环境
            return false;
        }
    }

    /**
     * 获取指定key值信息
     * @param key
     * @return
     */
    public static String readValue(String key) {
        Properties props = new Properties();
        try {
            InputStream in = PropertiesLoadUtils.class.getResourceAsStream(propertyPath);
            props.load(in);
            String value = props.getProperty(key);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取所有键值信息
     * @return
     */
    public static Map<String, String> readProperties() {
        Properties props = new Properties();
        Map map = null;
        try {
            InputStream in = PropertiesLoadUtils.class.getResourceAsStream(propertyPath);
            props.load(in);
            Enumeration en = props.propertyNames();
            map = new HashMap();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
                String value = props.getProperty(key);
                map.put(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
