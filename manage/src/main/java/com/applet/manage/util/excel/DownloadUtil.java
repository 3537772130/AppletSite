package com.applet.manage.util.excel;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @program: SpringBootDemo
 * @description: Excel导出工具类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-09-12 11:37
 **/
public class DownloadUtil {

    /**
     * 下载输出流
     *
     * @param response
     * @param fileName
     * @param os
     * @throws IOException
     */
    public static void download(HttpServletResponse response, String fileName, ByteArrayOutputStream os) throws IOException {
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xlsx").getBytes(), "ISO8859-1"));
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }
}
