package com.applet.common.util.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zhouhuahu on 2018/6/26.
 */
public class SHA {

    public static String SHA1(String decript){
        return SHA1(decript,null);
    }

    public static String SHA1(String decript,String charSet) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            if(null !=charSet){
                digest.update(decript.getBytes(charSet));
            }else{
                digest.update(decript.getBytes());
            }
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String SHA(String decript) {
        try {
            MessageDigest digest = MessageDigest
                    .getInstance("SHA");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
