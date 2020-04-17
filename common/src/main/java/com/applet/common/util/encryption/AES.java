package com.applet.common.util.encryption;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2020/4/15
 * Time: 22:59
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class AES {
    /**
     *
     * @author ngh
     * AES128 算法
     *
     * CBC 模式
     *
     * PKCS7Padding 填充模式
     *
     * CBC模式需要添加偏移量参数iv，必须16位
     * 密钥 sessionKey，必须16位
     *
     * 介于java 不支持PKCS7Padding，只支持PKCS5Padding 但是PKCS7Padding 和 PKCS5Padding 没有什么区别
     * 要实现在java端用PKCS7Padding填充，需要用到bouncycastle组件来实现
     */
    private static final String sessionKey = "8050207040109060";
    // 偏移量 16位
    private static final String iv = "6030205010408090";

    // 算法名称
    static final String KEY_ALGORITHM = "AES";
    // 加解密算法/模式/填充方式
    static final String algorithmStr = "AES/CBC/PKCS7Padding";
    // 加解密 密钥 16位

    static byte[] ivByte;
    static byte[] keybytes;
    private static Key key;
    private static Cipher cipher;

    public static void init() {
        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        keybytes = sessionKey.getBytes();
        ivByte = iv.getBytes();
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        // 转化成JAVA的密钥格式
        key = new SecretKeySpec(keybytes, KEY_ALGORITHM);
        try {
            // 初始化cipher
            cipher = Cipher.getInstance(algorithmStr, "BC");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 加密方法
     *
     * @param content 要加密的字符串
     * @return
     */
    public static String encrypt(String content) {
        byte[] encryptedText = null;
        byte[] contentByte = content.getBytes();
        init();
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(ivByte));
            encryptedText = cipher.doFinal(contentByte);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new String(Hex.encode(encryptedText));
    }
    /**
     * 解密方法
     *
     * @param encryptedData 要解密的字符串
     * @return
     */
    public static String decrypt(String encryptedData) {
        byte[] encryptedText = null;
        byte[] encryptedDataByte = Hex.decode(encryptedData);
        init();
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivByte));
            encryptedText = cipher.doFinal(encryptedDataByte);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new String(encryptedText);
    }

    public static void main(String[] args) {
        //加密字符串
        String content = "孟飞快跑";
        System.out.println("加密前的：" + content);
        // 加密方法
        String enc = encrypt(content);
        System.out.println("加密后的内容：" + enc);

        // 解密方法
        String dec = decrypt(enc);
        System.out.println("解密后的内容：" + dec);
    }
}
