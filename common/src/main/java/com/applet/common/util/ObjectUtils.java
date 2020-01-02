package com.applet.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * object 工具类
 *
 * @author 谭良忠
 * @date 2020/1/2 9:50
 */
@Slf4j
public class ObjectUtils {

    public static Map<String, Object> objToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>(16);
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!"class".equals(key)) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Class<?> propertyType = property.getPropertyType();
                    Object value = getter.invoke(obj);
                    if (String.valueOf(propertyType).indexOf("java.lang.Byte") > 0 && "0".equals(String.valueOf(value))) {
                        map.put(key, String.valueOf(value));
                    } else if (String.valueOf(propertyType).indexOf("java.lang.Long") > 0 && "0".equals(String.valueOf(value))) {
                        map.put(key, String.valueOf(value));
                    } else if (String.valueOf(propertyType).indexOf("java.lang.Integer") > 0 && "0".equals(String.valueOf(value))) {
                        map.put(key, String.valueOf(value));
                    } else {
                        map.put(key, value);
                    }

                }

            }
        } catch (Exception e) {
            log.error("ObjToMap, Error: ", e);
        }

        return map;
    }

    /**
     * 获取空字段
     *
     * @param source
     * @return
     */
    public static String[] getNullFields(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            try {
                Object srcValue = src.getPropertyValue(pd.getName());
                if (srcValue == null) {
                    emptyNames.add(pd.getName());
                }
            } catch (Exception e) {

            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}  