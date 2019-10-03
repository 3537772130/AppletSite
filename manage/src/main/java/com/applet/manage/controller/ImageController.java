package com.applet.manage.controller;

import com.applet.manage.entity.ImageInfo;
import com.applet.manage.util.Constants;
import com.applet.manage.util.NullUtil;
import com.applet.manage.util.VerifyCodeUtil;
import com.applet.manage.util.file.ImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * @program: SpringBootDemo
 * @description: 图片控制层
 * @author: Mr.ZhouHuaHu
 * @create: 2019-08-12 17:16
 **/
@Controller
@RequestMapping(value = "/api/images/")
public class ImageController {
    private static final Logger log = LoggerFactory.getLogger(ImageController.class);

    /**
     * 获取指定文字创建的透明背景图片
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping("getImage")
    public void getImage(ImageInfo info, HttpServletResponse response) throws Exception {
        if (NullUtil.isNullOrEmpty(info.getText())) {
            throw new Exception("生成图片失败，缺少内容");
        }
        // 设置头信息
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        ServletOutputStream out = response.getOutputStream();
        ImageUtil.getTransparencyImage(out, info);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    /**
     * 加载图形验证码
     *
     * @param request
     */
    @RequestMapping("/image/loadFigureCode")
    public void loadFigureCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            VerifyCodeUtil imgUtil = new VerifyCodeUtil();
            BufferedImage image = imgUtil.getBuffImg();
            request.getSession().setAttribute(Constants.FIGURE_CODE, imgUtil.getCode());
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (Exception e) {
            log.info("图形验证码生成失败{}", e);
        }
    }

    /**
     * 客户端加载图形验证码
     *
     * @return
     */
    @RequestMapping("/api/image/loadFigureCode")
    public String loadFigureCode() {
        return "redirect:/image/loadFigureCode";
    }

}
