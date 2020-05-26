package com.applet.common.util.encryption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.*;
import java.lang.reflect.Method;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @program: SpringBootDemo
 * @description: 加密处理工具类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-08-23 13:49
 **/
public class RsaUtil {
    public static final Logger logger = LoggerFactory.getLogger(RsaUtil.class);
    // 系统信息加解密文件
    public static final String SYSTEM_MANAGE_PUBLIC_PATH = "/encrypt/userAppletInfo/system_public_key_pkcs8.pem";
    public static final String SYSTEM_MANAGE_PRIVATE_PATH = "/encrypt/userAppletInfo/system_private_key_pkcs8.pem";
    // 小程序信息加解密文件
    public static final String APPLET_MANAGE_PUBLIC_PATH = "/encrypt/userAppletInfo/applet_public_key_pkcs8.pem";
    public static final String APPLET_MANAGE_PRIVATE_PATH = "/encrypt/userAppletInfo/applet_private_key_pkcs8.pem";

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        // 加密
        PublicKey pub = getPubKey(SYSTEM_MANAGE_PUBLIC_PATH, "RSA");
        String str = "电饭锅森岛帆高是大法官";
        byte[] estr = encrypt(str.getBytes(), pub, 2048, 11, "RSA/ECB/PKCS1Padding");
        String result = Base64.encode(estr);
        System.out.println(result);
        // 解密
        byte[] bytes = Base64.decode(result);
        PrivateKey pri = getPriKey(SYSTEM_MANAGE_PRIVATE_PATH, "RSA");
        byte[] dstr = decrypt(bytes, pri, 2048, 11, "RSA/ECB/PKCS1Padding");
        System.out.println("解密后：" + new String(dstr));
    }

    public static byte[] decrypt(byte[] encryptedBytes, PrivateKey privateKey, int keyLength, int reserveSize, String cipherAlgorithm) throws Exception {
        int keyByteSize = keyLength / 8;
        int decryptBlockSize = keyByteSize - reserveSize;
        int nBlock = encryptedBytes.length / keyByteSize;
        ByteArrayOutputStream outbuf = null;
        try {
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            outbuf = new ByteArrayOutputStream(nBlock * decryptBlockSize);
            for (int offset = 0; offset < encryptedBytes.length; offset += keyByteSize) {
                int inputLen = encryptedBytes.length - offset;
                if (inputLen > keyByteSize) {
                    inputLen = keyByteSize;
                }
                byte[] decryptedBlock = cipher.doFinal(encryptedBytes, offset, inputLen);
                outbuf.write(decryptedBlock);
            }
            outbuf.flush();
            return outbuf.toByteArray();
        } catch (Exception e) {
            throw new Exception("DEENCRYPT ERROR:", e);
        } finally {
            try {
                if (outbuf != null) {
                    outbuf.close();
                }
            } catch (Exception e) {
                outbuf = null;
                throw new Exception("CLOSE ByteArrayOutputStream ERROR:", e);
            }
        }
    }

    public static byte[] encrypt(byte[] plainBytes, PublicKey publicKey, int keyLength, int reserveSize, String cipherAlgorithm) throws Exception {
        int keyByteSize = keyLength / 8;
        int encryptBlockSize = keyByteSize - reserveSize;
        int nBlock = plainBytes.length / encryptBlockSize;
        if ((plainBytes.length % encryptBlockSize) != 0) {
            nBlock += 1;
        }
        ByteArrayOutputStream outbuf = null;
        try {
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            outbuf = new ByteArrayOutputStream(nBlock * keyByteSize);
            for (int offset = 0; offset < plainBytes.length; offset += encryptBlockSize) {
                int inputLen = plainBytes.length - offset;
                if (inputLen > encryptBlockSize) {
                    inputLen = encryptBlockSize;
                }
                byte[] encryptedBlock = cipher.doFinal(plainBytes, offset, inputLen);
                outbuf.write(encryptedBlock);
            }
            outbuf.flush();
            return outbuf.toByteArray();
        } catch (Exception e) {
            throw new Exception("ENCRYPT ERROR:", e);
        } finally {
            try {
                if (outbuf != null) {
                    outbuf.close();
                }
            } catch (Exception e) {
                outbuf = null;
                throw new Exception("CLOSE ByteArrayOutputStream ERROR:", e);
            }
        }
    }

    public static PrivateKey getPriKey(String privateKeyPath, String keyAlgorithm) {
        try {
            return getPrivateKey(RsaUtil.class.getResourceAsStream(privateKeyPath), keyAlgorithm);
        } catch (Exception e) {
            logger.error("加载私钥出错! ", e);
        }
        return null;
    }

    public static PublicKey getPubKey(String publicKeyPath, String keyAlgorithm) {
        try {
            return getPublicKey(RsaUtil.class.getResourceAsStream(publicKeyPath), keyAlgorithm);
        } catch (Exception e) {
            logger.error("加载公钥出错! ", e);
        }
        return null;
    }

    public static PublicKey getPublicKey(InputStream inputStream, String keyAlgorithm) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String readLine = null;
            while ((readLine = br.readLine()) != null) {
                if (readLine.charAt(0) == '-') {
                    continue;
                } else {
                    sb.append(readLine);
                    sb.append('\r');
                }
            }
            X509EncodedKeySpec pubX509 = new X509EncodedKeySpec(decodeBase64(sb.toString()));
            KeyFactory keyFactory = KeyFactory.getInstance(keyAlgorithm);
            PublicKey publicKey = keyFactory.generatePublic(pubX509);

            return publicKey;
        } catch (Exception e) {
            throw new Exception("READ PUBLIC KEY ERROR:", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                inputStream = null;
                throw new Exception("INPUT STREAM CLOSE ERROR:", e);
            }
        }
    }

    public static PrivateKey getPrivateKey(InputStream inputStream, String keyAlgorithm) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String readLine = null;
            while ((readLine = br.readLine()) != null) {
                if (readLine.charAt(0) == '-') {
                    continue;
                } else {
                    sb.append(readLine);
                    sb.append('\r');
                }
            }
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(decodeBase64(sb.toString()));
            KeyFactory keyFactory = KeyFactory.getInstance(keyAlgorithm);
            PrivateKey privateKey = keyFactory.generatePrivate(priPKCS8);
            return privateKey;
        } catch (Exception e) {
            throw new Exception("READ PRIVATE KEY ERROR:", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                inputStream = null;
                throw new Exception("INPUT STREAM CLOSE ERROR:", e);
            }
        }
    }

    //下面是base64的编码和解码
    public static String encodeBase64(byte[] input) throws Exception {
        Class clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method mainMethod = clazz.getMethod("encode", byte[].class);
        mainMethod.setAccessible(true);
        Object retObj = mainMethod.invoke(null, new Object[]{input});
        return (String) retObj;
    }

    /***
     * decode by Base64
     */
    public static byte[] decodeBase64(String input) throws Exception {
        Class clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method mainMethod = clazz.getMethod("decode", String.class);
        mainMethod.setAccessible(true);
        Object retObj = mainMethod.invoke(null, input);
        return (byte[]) retObj;
    }
}

