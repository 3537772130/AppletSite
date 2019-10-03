package com.applet.manage.entity;

/**
 * @program: SpringBootDemo
 * @description: 图片信息
 * @author: Mr.ZhouHuaHu
 * @create: 2019-08-13 09:34
 **/
public class ImageInfo {

    private String italic;// 斜体
    private String bold;// 字体粗细
    private String layout = "0";// 布局
    private String align = "centre";// 对齐方式
    private String fontFamily = "黑体";// 字体
    private Integer fontSize = 24;// 字体大小
    private String color = "3100463";// 字体颜色 十进制颜色码
    private String text;// 文本信息
    private Integer width = 200;// 图片宽度
    private Integer height = 60;// 图片高度

    public String getItalic() {
        return italic;
    }

    public void setItalic(String italic) {
        this.italic = italic;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBold() {
        return bold;
    }

    public void setBold(String bold) {
        this.bold = bold;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
