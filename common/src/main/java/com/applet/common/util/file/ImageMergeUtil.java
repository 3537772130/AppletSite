package com.applet.common.util.file;

import com.applet.common.entity.other.ImgWatermark;
import com.applet.common.util.Arith;
import com.applet.common.util.NullUtil;
import com.applet.common.util.qiniu.QiNiuConfig;
import com.applet.common.util.qiniu.QiNiuUtil;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Coordinate;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2020/5/7
 * Time: 16:49
 * To change this template use File | Settings | File Templates.
 * Description: 图片工具类 - 拼接图片
 */
public class ImageMergeUtil {
    // 默认商品图片宽度
    private final static Integer GOODS_IMAGE_WIDTH = 800;

    /**
     *
     * @param fileName 七牛云文件名称（key）
     * @param goodsImage 商品图片
     * @param materielImage 物料图片
     * @param materielType 物料类型
     * @param positionList 水印百分比位置坐标信息
     * @return
     */
    public static Boolean mergeGoodsImageToAppletMateriel(String fileName, String goodsImage, String materielImage, int materielType, List<Double> positionList) {
        if (materielType == 1 || materielType == 2) {
            // 拼接图片 上下
            BufferedImage image1 = getBufferedImage(goodsImage);
            int height1 = getHeightToInProportion(image1, GOODS_IMAGE_WIDTH);
            image1 = resizeBufferedImage(image1, GOODS_IMAGE_WIDTH.intValue(), height1, true);
            BufferedImage image2 = getBufferedImage(materielImage);
            int height2 = getHeightToInProportion(image2, GOODS_IMAGE_WIDTH);
            image2 = resizeBufferedImage(image2, GOODS_IMAGE_WIDTH.intValue(), height2, true);
            try {
                BufferedImage bufferedImage = mergeImages(false, image1, image2);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", out);
                QiNiuUtil.uploadFile(out.toByteArray(), fileName, QiNiuConfig.bucketAppletPublic);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // 合成图片 水印二维码
            if (NullUtil.isNotNullOrEmpty(positionList)){
                ImgWatermark imgWatermarkVo = new ImgWatermark();
                imgWatermarkVo.getWatermarks().add(new ImgWatermark().new Watermark(materielImage, positionList));
                try {
                    BufferedImage bufferedImage = watermarkImg(goodsImage, imgWatermarkVo);
                    int height = getHeightToInProportion(bufferedImage, GOODS_IMAGE_WIDTH);
                    bufferedImage = ImageMergeUtil.resizeBufferedImage(bufferedImage, GOODS_IMAGE_WIDTH, height, true);
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    ImageIO.write(bufferedImage, "png", out);
                    QiNiuUtil.uploadFile(out.toByteArray(), fileName, QiNiuConfig.bucketAppletPublic);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 拼接俩张图片
     *
     * @param isHorizontal
     * @param image1
     * @param image2
     * @return
     * @throws IOException
     */
    public static BufferedImage mergeImages(boolean isHorizontal, BufferedImage image1, BufferedImage image2) throws IOException {
        return mergeImage(isHorizontal, image1, image2);
    }

    /**
     * 拼接任数量的图片成一张图片
     *
     * @param isHorizontal true代表水平拼接，fasle代表垂直拼接
     * @param imgs         待合并的图片数组
     * @return
     * @throws IOException
     */
    private static BufferedImage mergeImage(boolean isHorizontal, BufferedImage... imgs) throws IOException {
        // 生成新图片
        BufferedImage destImage = null;
        // 计算新图片的长和高
        int allw = 0, allh = 0, allwMax = 0, allhMax = 0;
        // 获取总长、总宽、最长、最宽
        for (int i = 0; i < imgs.length; i++) {
            BufferedImage img = imgs[i];
            allw += img.getWidth();
            allh += img.getHeight();
            if (img.getWidth() > allwMax) {
                allwMax = img.getWidth();
            }
            if (img.getHeight() > allhMax) {
                allhMax = img.getHeight();
            }
        }
        // 创建新图片
        if (isHorizontal) {
            destImage = new BufferedImage(allw, allhMax, BufferedImage.TYPE_INT_RGB);
        } else {
            destImage = new BufferedImage(allwMax, allh, BufferedImage.TYPE_INT_RGB);
        }
        // 拼接所有子图片到新图片
        int wx = 0, wy = 0;
        for (int i = 0; i < imgs.length; i++) {
            BufferedImage img = imgs[i];
            int w1 = img.getWidth();
            int h1 = img.getHeight();
            // 从图片中读取RGB
            int[] ImageArrayOne = new int[w1 * h1];
            ImageArrayOne = img.getRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // 逐行扫描图像中各个像素的RGB到数组中
            if (isHorizontal) { // 水平方向合并
                destImage.setRGB(wx, 0, w1, h1, ImageArrayOne, 0, w1); // 设置上半部分或左半部分的RGB
            } else { // 垂直方向合并
                destImage.setRGB(0, wy, w1, h1, ImageArrayOne, 0, w1); // 设置上半部分或左半部分的RGB
            }
            wx += w1;
            wy += h1;
        }
        return destImage;
    }

    /**
     * 合并图片 将图片水印到另一张图片上
     *
     * @param baseImgUrl
     * @param imgWatermarkVo
     * @return
     * @throws Exception
     */
    public static BufferedImage watermarkImg(String baseImgUrl, ImgWatermark imgWatermarkVo) throws Exception {
        BufferedImage destImage = getBufferedImage(baseImgUrl);
        BufferedImage tempImage = null;
        int w1 = destImage.getWidth(), h1 = destImage.getHeight(), startX, startY, endX, endY;
        Coordinate coordinate = null;//水印位置
        List<Double> points = null;//水印位置坐标左上、右下
        for (ImgWatermark.Watermark imgVo : imgWatermarkVo.getWatermarks()) {
            InputStream inputStream = getInputStream(imgVo.getImgUrl());
            if (null == inputStream) {
                continue;
            }
            points = imgVo.getPoints();
            startX = new BigDecimal(points.get(0) * w1).intValue();
            startY = new BigDecimal(points.get(1) * h1).intValue();
            endX = new BigDecimal(points.get(2) * w1).intValue();
            endY = new BigDecimal(points.get(3) * h1).intValue();
            coordinate = new Coordinate(startX, startY);//设置水印位置
            tempImage = Thumbnails.of(ImageIO.read(inputStream)).size(endX - startX, endY - startY).keepAspectRatio(false).asBufferedImage();
            destImage = Thumbnails.of(destImage).size(w1, h1).watermark(coordinate, tempImage, 1f).asBufferedImage();
        }
        return destImage;
    }

    /**
     * 获取网络图片资源 并转换成InputStream
     *
     * @param baseUrl
     * @return
     */
    private static InputStream getInputStream(String baseUrl) {
        if (StringUtils.isBlank(baseUrl)) {
            return null;
        }
        try {
            URL url = new URL(baseUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(6000);
            connection.setReadTimeout(6000);
            int code = connection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == code) {
                return connection.getInputStream();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取网络图片资源 并转换成BufferedImage
     *
     * @param baseUrl
     * @return
     */
    private static BufferedImage getBufferedImage(String baseUrl) {
        if (StringUtils.isBlank(baseUrl)) {
            return null;
        }
        try {
            URL url = new URL(baseUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(6000);
            connection.setReadTimeout(6000);
            int code = connection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == code) {
                return toBufferedImage(url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解决图片合成失真的问题，图片表面被涂上了一层红色
     * ImageIO.read(url)方法有问题 读取图片的时候可能会不正确处理ICC ，ICC为JPEG图片格式中的一种头部信息
     *
     * @param url
     * @return
     */
    private static BufferedImage toBufferedImage(URL url) {
        Image image = Toolkit.getDefaultToolkit().getImage(url);
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        try {
            int transparency = Transparency.OPAQUE;
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null),
                    image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
        }
        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            bimage = new BufferedImage(image.getWidth(null),
                    image.getHeight(null), type);
        }
        // Copy image to buffered image
        Graphics g = bimage.createGraphics();
        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bimage;
    }

    /**
     * 调整bufferedimage大小
     *
     * @param source  BufferedImage 原始image
     * @param targetW int  目标宽
     * @param targetH int  目标高
     * @param flag    boolean 是否同比例调整
     * @return BufferedImage  返回新image
     */
    private static BufferedImage resizeBufferedImage(BufferedImage source, int targetW, int targetH, boolean flag) {
        int type = source.getType();
        BufferedImage target = null;
        double sx = (double) targetW / source.getWidth();
        double sy = (double) targetH / source.getHeight();
        if (flag && sx > sy) {
            sx = sy;
            targetW = (int) (sx * source.getWidth());
        } else if (flag && sx <= sy) {
            sy = sx;
            targetH = (int) (sy * source.getHeight());
        }
        if (type == BufferedImage.TYPE_CUSTOM) { // handmade
            ColorModel cm = source.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
        } else {
            target = new BufferedImage(targetW, targetH, type);
        }
        Graphics2D g = target.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
        g.dispose();
        return target;
    }

    /**
     * 根据图片宽度，同比例获取高度
     * @param image
     * @return
     */
    private static int getHeightToInProportion(BufferedImage image, Integer width){
        Integer tw = image.getWidth();
        Integer th = image.getHeight();
        double multiple = Arith.div(tw.doubleValue(), width.doubleValue(), 2);
        Double height = Arith.roundDown(Arith.sub(th.doubleValue(), multiple), 0);
        return height.intValue();
    }

    public static void main(String[] args) {
//        String goodsImage = "http://public.appletsite.com//api/public/U1-GT20191204180834289667";
        String goodsImage = "http://public.appletsite.com//api/public/applet-mobile-test";
//        BufferedImage image1 = getBufferedImage(goodsImage);
//        int height1 = getHeightToInProportion(image1, GOODS_IMAGE_WIDTH);
//        image1 = resizeBufferedImage(image1, GOODS_IMAGE_WIDTH.intValue(), height1, true);

//        // 扫码_搜索联合传播样式
//        String materielImage = "http://public.appletsite.com//api/public/applet-materiel-test1";
////        // 搜索框传播样式
////        String materielImage = "http://public.appletsite.com//api/public/applet-materiel-test2";
//        BufferedImage image2 = getBufferedImage(materielImage);
//        int height2 = getHeightToInProportion(image2, GOODS_IMAGE_WIDTH);
//        image2 = resizeBufferedImage(image2, GOODS_IMAGE_WIDTH.intValue(), height2, true);
//        try {
//            BufferedImage bufferedImage = mergeImages(false, image1, image2);
//            OutputStream os = new FileOutputStream("C:\\Users\\周华虎\\Pictures\\优选文具\\test\\new1.jpg");
//            ImageIO.write(bufferedImage, "jpg", os);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // 普通二维码
        String materielImage = "http://public.appletsite.com//api/public/applet-materiel-test3";
//        // 二维码位置：右下角
//        List<Double> points = Arrays.asList(0.75D, 0.75D, 1.0D, 1.0D);
//        // 二维码位置：左上角
//        List<Double> points = Arrays.asList(0.0D, 0.0D, 0.25D, 0.25D);
//        // 二维码位置：左下角
//        List<Double> points = Arrays.asList(0.0D, 0.75D, 0.25D, 1.0D);
        // 二维码位置：右上角
        List<Double> points = Arrays.asList(0.85D, 0.0D, 1.0D, 0.12D);
        ImgWatermark imgWatermarkVo = new ImgWatermark();
        imgWatermarkVo.getWatermarks().add(new ImgWatermark().new Watermark(materielImage, points));
        try {
            BufferedImage bufferedImage = watermarkImg(goodsImage, imgWatermarkVo);
            int height = getHeightToInProportion(bufferedImage, GOODS_IMAGE_WIDTH);
            bufferedImage = ImageMergeUtil.resizeBufferedImage(bufferedImage, GOODS_IMAGE_WIDTH, height, true);
            OutputStream os = new FileOutputStream("C:\\Users\\周华虎\\Pictures\\优选文具\\test\\new3-5.jpg");
            ImageIO.write(bufferedImage, "jpg", os);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
