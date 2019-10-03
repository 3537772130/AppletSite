package com.applet.manage.util.encryption;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @program: SpringBootDemo
 * @description: 加密处理工具类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-08-23 13:49
 **/
public class EncryptionUtil {

    /**
     * 登录密码 MD5加密
     *
     * @param password
     * @param secretKey
     * @return
     */
    public static String encryptPasswordMD5(String password, String secretKey) {
        String cipher = DesUtil.encrypt(password, secretKey);
        return MD5Util.MD5(cipher);
    }

    /**
     * 小程序信息 RSA加密
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static String encryptAppletRSA(String str) throws Exception {
        String publicKeyPath = RsaUtil.APPLET_MANAGE_PUBLIC_PATH;
        PublicKey pub = RsaUtil.getPubKey(publicKeyPath, "RSA");
        byte[] estr = RsaUtil.encrypt(str.getBytes(), pub, 2048, 11, "RSA/ECB/PKCS1Padding");
        return Base64.encode(estr);
    }

    /**
     * 小程序信息 RSA解密
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static String decryptAppletRSA(String str) throws Exception {
        String privateKeyPath = RsaUtil.APPLET_MANAGE_PRIVATE_PATH;
        PrivateKey pri = RsaUtil.getPriKey(privateKeyPath, "RSA");
        byte[] bytes = Base64.decode(str);
        byte[] dstr = RsaUtil.decrypt(bytes, pri, 2048, 11, "RSA/ECB/PKCS1Padding");
        return new String(dstr);
    }
}
