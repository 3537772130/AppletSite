package com.applet.common.util.file;

import com.applet.common.entity.ImageInfo;
import com.applet.common.util.NullUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @program: SpringBootDemo
 * @description: 图片工具类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-08-12 14:44
 **/
public class ImageUtil {

    /**
     * 获取参数信息
     *
     * @return map 《包含fontFamily、style、fontSize、color、style[斜体、粗细]、text[完整的文本信息]》
     */
    public static Map<String, String> getParameter(ImageInfo info) {
        // 获取创建图片的信息参数
        Map<String, String> map = new HashMap<String, String>();
        //这两条暂时未使用 暂时无法实现效果
        map.put("layout", info.getLayout());// 布局？
        map.put("align", info.getAlign());// 对齐
        //有效数据
        map.put("fontFamily", info.getFontFamily());// 字体
        map.put("fontSize", info.getFontSize().toString());// 字体大小
        map.put("color", info.getColor());// 字体颜色
        map.put("text", info.getText());// 文本信息

        //获得字体颜色 rgb参数
        int c = Integer.parseInt(info.getColor());
        map.put("b", String.valueOf(c % 256));
        c /= 256;
        map.put("g", String.valueOf(c % 256));
        c /= 256;
        map.put("r", String.valueOf(c % 256));

        //设置字体样式
        String style = String.valueOf(Font.PLAIN);//默认字体
        if (NullUtil.isNotNullOrEmpty(info.getBold()) && NullUtil.isNotNullOrEmpty(info.getItalic())) {
            style = String.valueOf(Font.BOLD | Font.ITALIC);// 粗体&&斜体 √
        } else if (NullUtil.isNotNullOrEmpty(info.getBold())) {//粗体 √
            style = String.valueOf(Font.BOLD);
        } else if (NullUtil.isNotNullOrEmpty(info.getItalic())) {
            style = String.valueOf(Font.ITALIC);// 斜体 √
        }
        map.put("style", style);
        return map;
    }

    /**
     * 创建透明的图片
     *
     * @param out
     * @throws IOException
     */
    public static void getTransparencyImage(OutputStream out, ImageInfo info) throws IOException {
        //获取数据处理
        Map<String, String> map = getParameter(info);
        String fontFamily = info.getFontFamily();
        int fontSize = info.getFontSize().intValue();
        String text = info.getText();

        //初始化基本数据
        int width = info.getWidth(), height = info.getHeight(), ascent = 1, line = 1;

        //获得正确的text的width, height
        Map<String, Object> resultMap = getTextInfo(map, width, height);
        line = Integer.parseInt(resultMap.get("line").toString());
        width = Integer.parseInt(resultMap.get("width").toString());
        height = (int) Double.parseDouble(resultMap.get("height").toString());//每一行的高度
        ascent = Integer.parseInt(resultMap.get("ascent").toString());
        // 创建BufferedImage对象
        BufferedImage image = new BufferedImage(width, (height + ascent) * line, BufferedImage.TYPE_INT_ARGB);
        // 获取Graphics2D
        Graphics2D g2d = image.createGraphics();

        // ---------- 增加下面的代码使得背景透明 -----------------
        image = g2d.getDeviceConfiguration().createCompatibleImage(width, (height + ascent) * line);
        g2d.dispose();
        g2d = image.createGraphics();
        // ---------- 背景透明代码结束 -----------------

        //抗锯齿 让图片看起来更清晰
        RenderingHints rh = g2d.getRenderingHints();
        rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        // 画图 Start
        //获得字体颜色
        int r = Integer.parseInt(map.get("r")),
                g = Integer.parseInt(map.get("g")),
                b = Integer.parseInt(map.get("b"));
        int style = Integer.parseInt(map.get("style"));

        // 设置字体 名称、样式、磅值大小（同时使用斜体和粗体）
        Font font = new Font(fontFamily, style, fontSize);

        g2d.setFont(font);
        // 设置字体颜色
        g2d.setColor(new Color(r, g, b));
        StringTokenizer st = new StringTokenizer(text, "\r\n");
        int i = 0;
        while (st.hasMoreTokens()) {
            i++;
            // str 显示的文字, x 文字距离左边的间距, y 文字距离上面的间距 和字体一样
            int y = (int) ((height + ascent) * i / 1.5);
            g2d.drawString(st.nextToken(), 0, y);
        }

        // 画图 End
        // 释放对象
        g2d.dispose();
        // 输出文件
        ImageIO.write(image, "png", out);
        // 保存文件
//        String filePath = "static\\images\\logo\\logo.png";
//        String rootPath = PathUtil.getClassPath(filePath);
//        ImageIO.write(image, "PNG", new File(rootPath));
    }

    /**
     * 获取Text对应的信息
     *
     * @param map    《包含fontFamily、style、fontSize、color、style[斜体、粗细]、text[完整的文本信息]》
     * @param width  宽度 默认为1
     * @param height 高度 默认为1
     * @return
     */
    public static Map<String, Object> getTextInfo(Map<String, String> map, int width, int height) {

        int fontSize = Integer.parseInt(map.get("fontSize"));
        String fontFamily = map.get("fontFamily");
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        Font font = new Font(fontFamily, Integer.parseInt(map.get("style")), fontSize);
        FontMetrics mets = g2d.getFontMetrics(font);
        int ascent = mets.getLeading();//FontMetrics对象所描述的Font的标准行间距
        double heightInPixels = mets.getHeight();// 文字行高

        int line = 0;//text行数
        int textWidth = 0;//文本最宽的width
        int tempWidth = 0;//临时存储当前行文本Width
        StringTokenizer st = new StringTokenizer(map.get("text"), "\r\n");
        while (st.hasMoreTokens()) {
            line++;
            String printText = st.nextToken();
            //判断字符宽度, 保存最宽的字符的宽度
            Rectangle2D rec = getRectangle2D(map, printText);
            tempWidth = (int) rec.getWidth() + 10;
            if (textWidth < tempWidth) {
                textWidth = tempWidth;
            }
            // str 显示的文字, x 文字距离左边的间距, y 文字距离上面的间距 和字体一样
            g2d.drawString(printText, 0, fontSize * line);
        }
        // 释放对象
        g2d.dispose();

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("width", textWidth);
        resultMap.put("height", heightInPixels);
        resultMap.put("ascent", ascent);
        resultMap.put("line", line);

        return resultMap;
    }

    /**
     * 获取 Rectangle2D 对象
     *
     * @param map       《包含fontFamily、style、fontSize、color、style[斜体、粗细]、text[完整的文本信息]》
     * @param printText 文本信息
     * @return <code>new Rectangle2D();</code>
     */
    public static Rectangle2D getRectangle2D(Map<String, String> map, String printText) {
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        Font font = new Font(map.get("fontFamily"), Integer.parseInt(map.get("style")), Integer.parseInt(map.get("fontSize")));
        FontMetrics mets = g2d.getFontMetrics(font);
        Rectangle2D bounds = mets.getStringBounds(printText, null);

        return bounds;
    }

}
