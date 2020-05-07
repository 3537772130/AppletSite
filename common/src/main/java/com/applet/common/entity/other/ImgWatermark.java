package com.applet.common.entity.other;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2020/5/7
 * Time: 16:16
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class ImgWatermark implements Serializable {

    private List<Watermark> watermarks = new ArrayList<>();

    public List<Watermark> getWatermarks() {
        return watermarks;
    }

    public void setWatermarks(List<Watermark> watermarks) {
        this.watermarks = watermarks;
    }

    @Data
    public class Watermark{
        //图片地址
        private String imgUrl;
        //水印图片左上、右下标
        private List<Double> points;

        public Watermark(String imgUrl, List<Double> points) {
            this.imgUrl = imgUrl;
            this.points = points;
        }
    }
}
