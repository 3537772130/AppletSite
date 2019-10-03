package com.applet.manage.util.encryption;

import jodd.datetime.JDateTime;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * @program: SpringBootDemo
 * @description: DES秘钥工具类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-06-24 10:58
 **/
public class DesUtil {

    private static final String DEFAULT_PASSWORD_CRYPT_KEY = "__jDlog_";

    private static final String DES = "DES";
    private static Cipher cipher = null;

    static {
        // Cipher����ʵ����ɼ��ܲ���
        try {
            cipher = Cipher.getInstance(DES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DesUtil() {

    }

    public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();

        DESKeySpec dks = new DESKeySpec(key);

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(src);
    }

    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {

        SecureRandom sr = new SecureRandom();

        DESKeySpec dks = new DESKeySpec(key);

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(src);
    }

    public static String decrypt(String data) {
        try {
            return new String(decrypt(hex2byte(data.getBytes()),
                    DEFAULT_PASSWORD_CRYPT_KEY.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return null;
    }

    public static String decrypt(String data, String key,String encoding) {
        try {
            return new String(
                    decrypt(hex2byte(data.getBytes()), key.getBytes()),encoding);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String decrypt(String data, String key) {
        try {
            return new String(
                    decrypt(hex2byte(data.getBytes()), key.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encrypt(String data) {
        try {
            return byte2hex(encrypt(data.getBytes(),
                    DEFAULT_PASSWORD_CRYPT_KEY.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encrypt(String data, String key) {
        try {
            return byte2hex(encrypt(data.getBytes(), key.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";

        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                StringBuilder str = new StringBuilder();
                str.append(hs).append("0").append(stmp);
                hs = str.toString();
            } else {
                hs = hs + stmp;
            }
        }

        return hs.toUpperCase();
    }

    public static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0) {
            throw new IllegalArgumentException("���Ȳ���ż��");
        }

        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }

        return b2;
    }

    public static void main(String[] args) {
//		String url = "123";
//		url = DesUtil.encrypt(url, "ED92168D-230A-4D90-864A-E86315086C51");
//		System.err.println(url);
//		System.out.println(MD5Util.MD5(url));
//		url = DesUtil.decrypt(
//				"723491D51988AE16",
//				"ED92168D-230A-4D90-864A-E86315086C51");
        new JDateTime("2016-11-15 23:00:00","YYYY-MM-DD hh:mm:ss");
        String encData = "F5C334043A8A09B04C5688F696563771695D5F5E139F9ADCB7D4D73B00CC6E1D891D4BA8994BAADE3FB0443DEABC0A232665F8F8826617512838C60865BCD18C3C08BF657716C28A1A1C80A7DA610CAF324ABF1CDFE95CC3F44083D541ADF894D2A317E90D0D851AF44083D541ADF894D37E32B62911DBEA4D8F0E6FD5B84CAEE78E37166C6E9DE6CB177D4CDE100ABBC19B7EFDDC44370C6934174128FB4DC6FA26672691AAE1A1DA9484F3D465944F76D53C91C019D48164C413750CDC7244BB6F12A6203BD06ACB177D4CDE100ABBAD0EE7C97D361540678DD1E7A726F532FA4A66CE0D35389591A9B50A4A7C40D6CC89FDFCFAB4B7E56F5C8CC0BD583A8E09125BC1CEE8C328856C0D21806269FBFF0CA48C4A46A33D9B7410CEA2ABFB0C502A4D847D7454E9C4CC6D40AC6647484EA10987003193836CACEE6968A9ADD3721B17AF438C62F94C2491D38E2F0B3194C1C6603F853A678FF9438516495C9207C2DD6DDF64ABF3C4A15FF7EFCBE5607A54F78D552EB3BC87F31BBB09D1B8551204179F24669E6D4F35BE8453FB327BC15126529931747B2FB547893C710C1743C89828BB2404804FA6977B4E86F5CAD3614F1FFA50F5D659C0B61AC924755EFFF22FB85D08BE9F3883526EB49FE05CA3212A993C06C7962779A5D54E8CC794";
        encData = new String(hex2byte(encData.getBytes()));
        encData = decrypt(encData, "f49a7b8c");
    }
}
