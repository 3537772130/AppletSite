package com.applet.manage.util.file;

import com.applet.manage.util.NullUtil;
import com.applet.manage.util.NullUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.FileNameMap;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;

/**
 * @program: SpringBootDemo
 * @description: 文件处理工具类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-08-26 11:32
 **/
public class FileUtil {
    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

    private final static String PREFIX_VIDEO = "video/";

    /**
     * Get the Mime Type from a File
     *
     * @param fileName 文件名
     * @return 返回MIME类型
     * thx https://www.oschina.net/question/571282_223549
     * add by fengwenhua 2017年5月3日09:55:01
     */
    private static String getMimeType(String fileName) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String type = fileNameMap.getContentTypeFor(fileName);
        return type;
    }

    /**
     * 根据文件后缀名判断 文件是否是视频文件
     *
     * @param fileName 文件名
     * @return 是否是视频文件
     */
    public static boolean isVedioFile(String fileName) {
        String mimeType = getMimeType(fileName);
        if (NullUtil.isNotNullOrEmpty(fileName) && mimeType.contains(PREFIX_VIDEO)) {
            return true;
        }
        return false;
    }

    /**
     * 删除文件夹下所有文件
     *
     * @param path
     * @return
     */
    public static void deleteDir(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {//判断是否待删除目录是否存在
                System.err.println("The dir are not exists!");
            }
            String[] content = file.list();//取得当前目录下所有文件和文件夹
            for (String name : content) {
                File temp = new File(path, name);
                if (temp.isDirectory()) {//判断是否是目录
                    deleteDir(temp.getAbsolutePath());//递归调用，删除目录里的内容
                    temp.delete();//删除空目录
                } else {
                    if (!temp.delete()) {//直接删除文件
                        System.err.println("Failed to delete " + name);
                    }
                }
            }
        } catch (Exception e) {
            log.error("删除文件夹出错{}", e);
        }
    }

    /**
     * 删除CLASS下的文件
     *
     * @param filePath 文件夹路径
     * @param fileName 文件名称
     */
    public static void deleteClassFile(String filePath, String fileName) {
        try {
            String rootPath = PathUtil.getClassPath(filePath);
            File file = new File(rootPath + fileName);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            log.error("删除CLASS下文件出错{}", e);
        }
    }

    /**
     * 删除CLASS下的文件
     *
     * @param completePath 文件完整路径
     */
    public static void deleteClassFile(String completePath) {
        try {
            String rootPath = PathUtil.getClassPath(completePath);
            File file = new File(rootPath);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            log.error("删除CLASS下文件出错{}", e);
        }
    }

    /**
     * 复制文件，并删除原文件
     *
     * @param oldPath
     * @param oldFileName
     * @param newPath
     * @param newFileName
     */
    public static String copyFile(String oldPath, String oldFileName, String newPath, String newFileName) {
        try {
            // 获取复制的文件
            String oldRootPath = PathUtil.getClassPath(oldPath);
            File file1 = new File(oldRootPath + oldFileName);
            if (file1.exists()) {
                // 检查目标文件夹是否存在，不存在则创建
                String newRootPath = PathUtil.getClassPath(newPath);
                File file2 = new File(newRootPath);
                if (!file2.exists()) {
                    file2.mkdir();
                }
                // 获取目标文件，如果存在，则删除
                File file3 = new File(newRootPath + newFileName);
                if (file3.exists()) {
                    file3.delete();
                }
                // 复制文件至目标目录
                Files.copy(file1.toPath(), file3.toPath());
                // 删除原文件
                file1.delete();
                return newPath.replace("static\\", "") + newFileName;
            }
        } catch (Exception e) {
            log.error("复制文件出错{}", e);
        }
        return null;
    }

    /**
     * 复制商品有关文件
     *
     * @param url
     * @param newPath
     * @return
     */
    public static String copyFile(String url, String newPath) {
        int index = url.lastIndexOf("\\");
        String fileName = "\\" + url.substring(index + 1, url.length());
        String oldPath = "static\\images\\upload";
        return copyFile(oldPath, fileName, newPath, fileName);
    }

    /**
     * 复制商品类型图标
     *
     * @param userId
     * @param newUrl
     * @param oldUrl
     * @return
     */
    public static String copyGoodsTypeLogo(Integer userId, String newUrl, String oldUrl) {
        String newPath = "static\\images\\goods\\type\\user" + userId;
        String result = copyFile(newUrl, newPath);
        if (NullUtil.isNotNullOrEmpty(result) && NullUtil.isNotNullOrEmpty(oldUrl)) {
            deleteClassFile("static\\" + oldUrl);
        }
        return result;
    }

    /**
     * 复制商品封面图
     *
     * @param goodsId
     * @param newUrl
     * @param oldUrl
     * @return
     */
    public static String copyGoodsCoverSrc(Integer goodsId, String newUrl, String oldUrl) {
        String newPath = "static\\images\\goods\\info\\" + goodsId;
        String result = copyFile(newUrl, newPath);
        if (NullUtil.isNotNullOrEmpty(result) && NullUtil.isNotNullOrEmpty(oldUrl)) {
            deleteClassFile("static\\" + oldUrl);
        }
        return result;
    }

    /**
     * 复制商品规格图片
     *
     * @param goodsId
     * @param newUrl
     * @param oldUrl
     * @return
     */
    public static String copyGoodsSpecsSrc(Integer goodsId, String newUrl, String oldUrl) {
        String newPath = "static\\images\\goods\\specs\\" + goodsId;
        String result = copyFile(newUrl, newPath);
        if (NullUtil.isNotNullOrEmpty(result) && NullUtil.isNotNullOrEmpty(oldUrl)) {
            deleteClassFile("static\\" + oldUrl);
        }
        return result;
    }

    /**
     * 复制小程序页面元素图标
     *
     * @param elementId
     * @param newUrl
     * @param oldUrl
     * @return
     */
    public static String copyPageElementIcon(Integer elementId, String newUrl, String oldUrl) {
        String newPath = "static\\images\\applet\\page\\element\\" + elementId;
        String result = copyFile(newUrl, newPath);
        if (NullUtil.isNotNullOrEmpty(result) && NullUtil.isNotNullOrEmpty(oldUrl)) {
            deleteClassFile("static\\" + oldUrl);
        }
        return result;
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

    /**
     * 从网络Url中下载文件
     *
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
    public static String downLoadFromUrl(String urlStr, String fileName, String savePath) {
        try {

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置超时间为3秒
            conn.setConnectTimeout(3 * 1000);
            // 防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            // 得到输入流
            InputStream inputStream = conn.getInputStream();
            // 获取自己数组
            byte[] getData = readInputStream(inputStream);
            // 文件保存位置
            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                saveDir.mkdir();
            }
            File file = new File(saveDir + File.separator + fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(getData);
            if (fos != null) {
                fos.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            return saveDir + File.separator + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }
}
