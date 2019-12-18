package com.applet.apply.util.file;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @program: demo
 * @description: 从网络获取图片到本地
 * @author: zhouhuahu
 * @create: 2019-09-28 10:10
 **/
public class GetImageUtil {
    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        String url = "http://localhost:6060/resource/upload/shopPic/merchantApply/2/POS/fd_sfz_f.jpg";
        byte[] btImg = getImageFromNetByUrl(url);
        if (null != btImg && btImg.length > 0) {
            System.out.println("读取到：" + btImg.length + " 字节");
            String fileName = "lkl_xy.jpg";
            String holdUrl = "E:\\Project\\mdb\\target\\yxk_WEB\\upload\\shopPic\\merchant\\2\\";
            writeImageToDisk(btImg, holdUrl, fileName);
        } else {
            System.out.println("没有从该连接获得内容");
        }
    }

    /**
     * 获取报单图片
     *
     * @param httpHead 资源域名
     * @param httpUrl  资源所在路径
     * @param fileName 资源文件名
     * @param holdUrl  保存文件路径
     */
    public static void getMerchantImage(String httpHead, String httpUrl, String fileName, String holdUrl) {
        String url = httpHead + httpUrl + fileName;
        url = getHttpUrl(url);
        byte[] btImg = getImageFromNetByUrl(url);
        if (null != btImg && btImg.length > 0) {
            System.out.println("获取报单图片，读取到：" + btImg.length + " 字节");
            writeImageToDisk(btImg, holdUrl, fileName);
        } else {
            System.out.println("没有从该连接获得内容");
        }

    }

    /**
     * 检查图片路径格式
     *
     * @param url
     * @return
     */
    public static String getHttpUrl(String url) {
        String paths = "";
        //修改路径格式
        for (int i = 0; i < url.length(); i++) {
            char c = url.charAt(i);
            if (c == '\\') {
                paths += "/";
            } else {
                paths += c;
            }
        }
        return paths;
    }

    /**
     * 将图片写入到磁盘
     *
     * @param img      图片数据流
     * @param holdUrl  文件保存的路径
     * @param holdName 文件保存时的名称
     */
    public static void writeImageToDisk(byte[] img, String holdUrl, String holdName) {
        try {
            File file = new File(holdUrl + holdName);
            FileOutputStream fops = new FileOutputStream(file);
            fops.write(img);
            fops.flush();
            fops.close();
            System.out.println("图片已经写入到 " + holdUrl + holdName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据地址获得数据的字节流
     *
     * @param strUrl 网络连接地址
     * @return
     */
    public static byte[] getImageFromNetByUrl(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据
            byte[] btImg = readInputStream(inStream);//得到图片的二进制数据
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream getImageIO(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            return conn.getInputStream();//通过输入流获取图片数据
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 从输入流中获取数据
     *
     * @param inStream 输入流
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
}
