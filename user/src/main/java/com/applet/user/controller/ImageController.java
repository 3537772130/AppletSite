package com.applet.user.controller;

import com.applet.common.entity.other.ImageInfo;
import com.applet.common.util.Constants;
import com.applet.common.util.NullUtil;
import com.applet.common.util.file.VerifyCodeUtil;
import com.applet.common.util.file.ImageUtil;
import com.applet.user.config.annotation.CancelAuth;
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
@RequestMapping(value = "/api/user/")
public class ImageController {
    private static final Logger log = LoggerFactory.getLogger(ImageController.class);

    /**
     * 设置指定文字创建的透明背景图片
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping("setImageText")
    @CancelAuth
    public void setImageText(ImageInfo info, HttpServletResponse response) throws Exception {
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
    @RequestMapping("/loadFigureCode")
    @CancelAuth
    public void loadFigureCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            VerifyCodeUtil imgUtil = new VerifyCodeUtil();
            BufferedImage image = imgUtil.getBuffImg();
            request.getSession().setAttribute(Constants.FIGURE_CODE, imgUtil.getCode().toUpperCase());
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (Exception e) {
            log.info("图形验证码生成失败{}", e);
        }
    }

}
