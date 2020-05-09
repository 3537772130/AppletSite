package com.applet.user.controller;

import com.applet.common.entity.AppletMaterielInfo;
import com.applet.common.entity.UserGoodsSpreadImage;
import com.applet.common.entity.UserInfo;
import com.applet.common.entity.ViewGoodsFile;
import com.applet.common.entity.other.GoodsSpreadImage;
import com.applet.common.entity.other.ImageInfo;
import com.applet.common.util.AjaxResponse;
import com.applet.common.util.Constants;
import com.applet.common.util.NullUtil;
import com.applet.common.util.RandomUtil;
import com.applet.common.util.file.ImageMergeUtil;
import com.applet.common.util.file.VerifyCodeUtil;
import com.applet.common.util.file.ImageUtil;
import com.applet.common.util.qiniu.QiNiuUtil;
import com.applet.user.config.annotation.CancelAuth;
import com.applet.user.config.annotation.SessionScope;
import com.applet.user.service.UserGoodsService;
import com.applet.user.service.UserSpreadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @program: SpringBootDemo
 * @description: 图片控制层
 * @author: Mr.ZhouHuaHu
 * @create: 2019-08-12 17:16
 **/
@Slf4j
@Controller
@RequestMapping(value = "/api/user/")
public class ImageController {
    @Autowired
    private UserSpreadService userSpreadService;
    @Autowired
    private UserGoodsService userGoodsService;

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

    /**
     * 预览商品推广图片
     * @param user
     * @param image
     * @param response
     */
    @RequestMapping(value = "previewGoodsSpreadImage")
    public void previewGoodsSpreadImage(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, GoodsSpreadImage image,
                                     HttpServletResponse response) {
        try {
            if (NullUtil.isNullOrEmpty(image.getGoodsFileId()) || NullUtil.isNullOrEmpty(image.getMaterielId())) {
                log.error("error：预览商品推广图片失败，参数缺失(1)");
                return;
            }
            // 校验信息
            ViewGoodsFile goodsFile = userGoodsService.selectFileInfo(image.getGoodsFileId(), user.getId());
            AppletMaterielInfo materielInfo = userSpreadService.selectAppletMaterielInfo(image.getMaterielId(), user.getId());
            if (null == goodsFile || null == materielInfo) {
                log.error("error：预览商品推广图片失败，信息不符");
                return;
            }
            List<Double> positionList = null;
            if (materielInfo.getMaterielType().intValue() == 3) {
                // 水印图片，设置水印坐标
                if (NullUtil.isNullOrEmpty(image.getStartX()) || NullUtil.isNullOrEmpty(image.getStartY())
                        || NullUtil.isNullOrEmpty(image.getSizeX()) || NullUtil.isNullOrEmpty(image.getSizeY())) {
                    log.error("error：预览商品推广图片失败，参数缺失(2)");
                    return;
                }
                positionList = image.setGoodsSpreadImage();
                if (NullUtil.isNullOrEmpty(positionList)) {
                    log.error("error：预览商品推广图片失败，图片溢出");
                    return;
                }

            }
            // 设置头信息
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/jpeg");
            ServletOutputStream out = response.getOutputStream();
            ImageMergeUtil.previewGoodsImageToAppletMateriel(out, goodsFile.getFileSrc(),
                    materielInfo.getMaterielImage(), materielInfo.getMaterielType(), positionList);
        } catch (Exception e) {
            log.error("预览商品推广图片出错{}", e);
        }
    }

    /**
     * 下载推广图片
     * @param user
     * @param id
     * @param request
     * @param response
     */
    @RequestMapping(value = "downloadGoodsSpreadImage")
    public void downloadGoodsSpreadImage(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer id,
                                         HttpServletRequest request, HttpServletResponse response){
        try {
            UserGoodsSpreadImage image = userSpreadService.selectUserGoodsSpreadImage(id, user.getId());
            if (null != image) {
                QiNiuUtil.downFileForBrowser(request, response, image.getSpreadImage());
            }
        } catch (IOException e) {
            log.error("从七牛云下载文件到浏览器出错{}", e);
        }
    }
}
