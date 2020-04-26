package com.applet.common.util.http;

import com.applet.common.entity.other.GeoLocation;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Subdivision;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.*;

/**
 * @program: SpringBootDemo
 * @description: IP工具类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-06-21 11:17
 **/
@Slf4j
public class IpUtil {
    public static final String GEO_LITE2_CITY_PATH = "/localLibrary/GeoLite2-City.mmdb";

    /**
     * 初始化信息
     */
    private DatabaseReader initReader(){
        InputStream inputStream = this.getClass().getResourceAsStream(GEO_LITE2_CITY_PATH);
        if (inputStream == null) {
            log.error("----------初始化IP工具类失败----------");
            return null;
        }
        try {
            return new DatabaseReader.Builder(inputStream).build();
        } catch (Exception e) {
            log.error("----------初始化IP工具类出错----------{}", e);
            return null;
        }
    }

    /**
     * 获取ip地址映射的地域信息
     *
     * @param ipAddress
     * @return
     */
    public static GeoLocation getLocationFromRequest(String ipAddress) {
        IpUtil ipUtil = new IpUtil();
        GeoLocation location = ipUtil.getLocationV2(ipAddress);
        return location;
    }

    /**
     * 获取ip地址映射的国家
     *
     * @param ipAddress
     * @return
     */
    private GeoLocation getLocationV2(String ipAddress) {
        GeoLocation geoLocation = null;
        DatabaseReader reader = initReader();
        if (null == reader) {
            log.error("location database is not found.");
        } else {
            try {
                geoLocation = new GeoLocation();
                InetAddress ipAdd = InetAddress.getByName(ipAddress);
                CityResponse response = reader.city(ipAdd);


                Country country = response.getCountry();
                geoLocation.setCountryCode(country.getIsoCode());
                geoLocation.setCountryName(country.getName());


                Subdivision subdivision = response.getMostSpecificSubdivision();
                geoLocation.setRegionName(subdivision.getName());


                City city = response.getCity();
                geoLocation.setCity(city.getName());
                geoLocation.setPostalCode(response.getPostal().getCode());
                geoLocation.setLatitude(String.valueOf(response.getLocation().getLatitude()));
                geoLocation.setLongitude(String.valueOf(response.getLocation().getLongitude()));
            } catch (Exception e) {
                log.error("获取IP地域信息出错{}", e);
                return null;
            }
        }
        return geoLocation;
    }

    /**
     * 获取客户端请求IP地址
     * @return
     */
    public static String getLocalIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取客户端请求IP地址
     * @param request
     * @return
     */
    public static String getLocalIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        log.info("获取到当前请求的IP地址为：" + ipAddress);
        return ipAddress;
    }

}
