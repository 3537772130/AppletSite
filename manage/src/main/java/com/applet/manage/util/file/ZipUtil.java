package com.applet.manage.util.file;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @program: demo
 * @description: 压缩文件工具类
 * @author: zhouhuahu
 * @create: 2019-09-24 22:48
 **/
public class ZipUtil {

    /**
     * 把文件打成压缩包并保存在本地硬盘
     *
     * @param srcfiles
     * @param zipPath
     */
    public static void zipFiles(List srcfiles, String zipPath) {
        byte[] buf = new byte[4096];
        ZipOutputStream out = null;
        try {
            // 创建zip输出流
            out = new ZipOutputStream(new FileOutputStream(zipPath));
            // 循环将源文件列表添加到zip文件中
            for (int i = 0; i < srcfiles.size(); i++) {
                File file = new File(srcfiles.get(i).toString());
                FileInputStream in = new FileInputStream(file);
                String fileName = file.getName();
                // 将文件名作为zip的Entry存入zip文件中
                out.putNextEntry(new ZipEntry(fileName));
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                    out = null;
                } catch (IOException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 把文件打成压缩包并输出到客户端浏览器中
     *
     * @param request
     * @param response
     * @param srcFiles
     * @param downloadZipFileName
     */
    public static void zipFiles(HttpServletRequest request, HttpServletResponse response, List srcFiles, String downloadZipFileName) {
        byte[] buf = new byte[4096];
        try {
            // Create the ZIP file
            // ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipPath));
            ZipOutputStream out = new ZipOutputStream(response.getOutputStream());//--设置成这样可以不用保存在本地，再输出， 通过response流输出,直接输出到客户端浏览器中。
            // Compress the files
            if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
                downloadZipFileName = new String(downloadZipFileName.getBytes("GB2312"), "ISO-8859-1");
            } else {
                // 对文件名进行编码处理中文问题
                downloadZipFileName = java.net.URLEncoder.encode(downloadZipFileName, "UTF-8");// 处理中文文件名的问题
                downloadZipFileName = new String(downloadZipFileName.getBytes("UTF-8"), "GBK");// 处理中文文件名的问题
            }
            response.reset(); // 重点突出
            response.setCharacterEncoding("UTF-8"); // 重点突出
            response.setContentType("application/x-msdownload");// 不同类型的文件对应不同的MIME类型 // 重点突出
            // inline在浏览器中直接显示，不提示用户下载
            // attachment弹出对话框，提示用户进行下载保存本地
            // 默认为inline方式
            response.setHeader("Content-Disposition", "attachment;filename=" + downloadZipFileName);
            for (int i = 0; i < srcFiles.size(); i++) {
                File file = new File(srcFiles.get(i).toString());
                FileInputStream in = new FileInputStream(file);
                // Add ZIP entry to output stream.
                String fileName = file.getName();
                out.putNextEntry(new ZipEntry(fileName));
                // Transfer bytes from the file to the ZIP file
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                // Complete the entry
                out.closeEntry();
                in.close();
            }
            // Complete the ZIP file
            out.close();
            System.out.println("压缩完成.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zipFiles(HttpServletRequest request, HttpServletResponse response, String srcFile, String downloadZipFileName) {
        List<String> list = new ArrayList<>();
        list.add(srcFile);
        zipFiles(request, response, list, downloadZipFileName);
    }

    public static void main(String[] args) {
        List srcFiles = new ArrayList();
        srcFiles.add("c:/codetemplates.xml");
        srcFiles.add("d:/t1.png");
        zipFiles(srcFiles, "c:/mm.zip");

    }
}
