package com.applet.common.util.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.*;

/**
 * @program: SpringBootDemo
 * @description: IP工具类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-06-21 11:17
 **/
public class IpUtil {
    private static final Logger log = LoggerFactory.getLogger(IpUtil.class);

    public static String getLocalIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getForIp(HttpServletRequest request) {
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
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * @param content        请求的参数 格式为：name=xxx&pwd=xxx
     * @param encodingString 服务器端请求编码。如GBK,UTF-8等
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getAddresses(String content, String encodingString) {
        //调用淘宝API
        String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
        String returnStr = getResult(urlStr, content, encodingString);
        if (returnStr != null) {
            System.out.println(returnStr);
            return returnStr;
        }
        return null;
    }

    /**
     * @param urlStr         请求的地址
     * @param content        请求的参数 格式为：name=xxx&pwd=xxx
     * @param encodingString 服务器端请求编码。如GBK,UTF-8等
     * @return
     */
    private static String getResult(String urlStr, String content, String encodingString) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            // 新建连接实例
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接超时时间，单位毫秒
            //connection.setConnectTimeout(20000);
            // 设置读取数据超时时间，单位毫秒
            //connection.setReadTimeout(20000);
            //是否打开输出流
            connection.setDoOutput(true);
            //是否打开输入流
            connection.setDoInput(true);
            //提交方法 POST|GET
            connection.setRequestMethod("POST");
            //是否缓存
            connection.setUseCaches(false);
            //打开连接端口
            connection.connect();
            //打开输出流往对端服务器写数据
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            //写数据，即提交表单 name=xxx&pwd=xxx
            out.writeBytes(content);
            //刷新
            out.flush();
            //关闭输出流
            out.close();
            // 往对端写完数据对端服务器返回数据 ,以BufferedReader流来读取
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), encodingString));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }
}
