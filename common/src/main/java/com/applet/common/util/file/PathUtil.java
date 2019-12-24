package com.applet.common.util.file;

import jodd.datetime.JDateTime;

import java.io.File;
import java.util.Date;

/**
 * @program: SpringBootDemo
 * @description: 获取项目在服务其中的真实路径的工具类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-08-05 09:31
 **/
public class PathUtil {

    //获取项目的根路径
    private static String classPath = PathUtil.class.getResource("/").getPath();

    //对项目的根路径进行解析，拿到项目路径
    public static String getRootPath(String sourcePath) {
        String rootPath = "";
        //windows下
        if ("\\".equals(File.separator)) {
            rootPath = classPath.substring(1, classPath.indexOf("/WEB-INF/classes")) + sourcePath;
            rootPath = rootPath.replace("/", "\\");
        }
        //linux下
        if ("/".equals(File.separator)) {
            rootPath = classPath.substring(0, classPath.indexOf("/WEB-INF/classes")) + sourcePath;
            rootPath = rootPath.replace("\\", "/");
        }
        return rootPath;
    }

    public static String getClassPath(String filePath) {
        String rootPath = "";
        //windows下
        if ("\\".equals(File.separator)) {
            rootPath = classPath.substring(1, classPath.length()) + filePath;
            rootPath = rootPath.replace("/", "\\");
        }
        //linux下
        if ("/".equals(File.separator)) {
            rootPath = classPath.substring(0, classPath.length()) + filePath;
            rootPath = rootPath.replace("\\", "/");
        }
        return rootPath;
    }

    public static void main(String[] arge) {
//        System.out.println(getClassPath("\\resources\\static\\images\\head-portrait\\"));
        JDateTime time = new JDateTime(new Date());
        System.out.println(time.toString("MMDDhhmmss"));

        FileUtil.copyFile("static\\images\\applet-logo\\draft\\", "U1-APPLET-LOGO.jpg", "static\\images\\applet-logo\\", "92431224MA4LW7EW87.jpg");
    }
}
