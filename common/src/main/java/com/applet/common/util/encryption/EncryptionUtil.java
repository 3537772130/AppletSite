package com.applet.common.util.encryption;

import com.alibaba.fastjson.JSONObject;
import com.applet.common.entity.pay.WxAppletPay;
import com.applet.common.util.NullUtil;
import com.applet.common.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

/**
 * @program: SpringBootDemo
 * @description: 加密处理工具类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-08-23 13:49
 **/
@Slf4j
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
     * 系统 RSA加密
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static String encryptSystemRSA(String str) throws Exception {
        String publicKeyPath = RsaUtil.SYSTEM_MANAGE_PUBLIC_PATH;
        PublicKey pub = RsaUtil.getPubKey(publicKeyPath, "RSA");
        byte[] estr = RsaUtil.encrypt(str.getBytes(), pub, 2048, 11, "RSA/ECB/PKCS1Padding");
        return Base64.encode(estr);
    }

    /**
     * 系统 RSA解密
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static String decryptSystemRSA(String str) throws Exception {
        String privateKeyPath = RsaUtil.SYSTEM_MANAGE_PRIVATE_PATH;
        PrivateKey pri = RsaUtil.getPriKey(privateKeyPath, "RSA");
        byte[] bytes = Base64.decode(str);
        byte[] dstr = RsaUtil.decrypt(bytes, pri, 2048, 11, "RSA/ECB/PKCS1Padding");
        return new String(dstr);
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

    /**
     * 小程序支付信息 AES加密
     * @param appId
     * @param payKey
     * @param prepayId
     * @return
     */
    public static String encryptAppletPayInfoAES(String appId, String payKey, String prepayId) throws Exception{
        try {
            // 小程序验签字符串
            String APPLET_SGIN = "appId=##appId&nonceStr=##nonceStr&package=##package&signType=##signType&timeStamp=##timeStamp&key=##key";
            String time_stamp = RandomUtil.getSecondTimestamp(new Date());
            String nonce_str = RandomUtil.getRandomStr32();
            String package_str = "prepay_id=" + prepayId;
            String signType = "MD5";
            APPLET_SGIN = APPLET_SGIN.replaceAll("##appId", appId)
                    .replaceAll("##nonceStr", nonce_str)
                    .replaceAll("##package", package_str)
                    .replaceAll("##signType", signType)
                    .replaceAll("##timeStamp", time_stamp)
                    .replaceAll("##key", payKey);
//            System.out.println("小程序支付数据：\n" + APPLET_SGIN);
            String pay_sign = MD5Util.MD5(APPLET_SGIN).toUpperCase();
            WxAppletPay pay = new WxAppletPay(appId, nonce_str, package_str, signType, time_stamp, payKey, pay_sign);
            String json = JSONObject.toJSONString(pay);
//            log.info("小程序支付信息字符串{}", json);
            if (NullUtil.isNotNullOrEmpty(json)){
                return AES.encrypt(json);
            }
        } catch (Exception e) {
            log.error("获取小程序支付签名出错{}", e);
        }
        return null;
    }

    public static void main(String[] arge) {
        try {
            String appId = "4sGW/csWX95nusHOLFh9sd2/aW1rfrsicmv+8eYRiCakL7vUF349cPAJ0icVDszIJjv9ycfRf0B1U+glNNyXrgVy+1ThCoUMD3+3YDGqrJ9Yp7jJKgeOXcdIah1baLrJtibalCIARY5NGkIodutQ0NeN9ltO5+UjS0P+ZP1PhvQ=";
            String payKey = "pH1L+mspl/epV3cMKsnUzpTzU346QBBAHGfk9HJ7apU8esIHgzcgSz87QNijlF0pvmC6Ch19/ZSw1DkobpJKqe6whdJRUbOSBwJuUNuLWQyd7AczkCkfloyYst5qbH+ksPKRZpiL3AuRVJTYr51XiF33mUsewpZIeiK6DGVsajc=";
            String json = encryptAppletPayInfoAES(appId, payKey, "");
            System.out.println("获取到小程序支付信息JSON字符串为：\n" + json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
