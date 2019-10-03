package com.applet.manage.util.encryption;

import com.applet.manage.util.RandomUtil;

import java.security.MessageDigest;

/**
 * @program: SpringBootDemo
 * @description: MD5加密工具类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-06-24 10:53
 **/
public class MD5Util {

    public final static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String generateShortPwd() {
        String pwd = "";
        for (int i = 0; i < 8; i++) {
            pwd += (char) (32 + (int) 95 * Math.random() + 1);
        }
        return pwd;
    }

    public static void main(String[] args) {
        String password = "123456";
        String checkCode = RandomUtil.getRandomStr32();
        System.out.println("密码加密串：" + checkCode);
        String cipher = DesUtil.encrypt(password, checkCode);
        cipher = MD5Util.MD5(cipher);
        System.out.println("加密后密码：" + cipher);
    }
}
