package com.applet.common.entity.other;

import com.applet.common.util.Arith;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2020/5/8
 * Time: 0:10
 * To change this template use File | Settings | File Templates.
 * Description: 商品推广图片信息
 */
public class GoodsSpreadImage {
    // 商品文件ID
    public Integer goodsFileId;
    // 小程序物料ID
    public Integer materielId;
    // 商品推广图片ID
    private Integer spreadImageId;
    // 水印图片水平开始位置
    private Integer startX;
    // 水印图片垂直开始位置
    private Integer startY;
    // 水印图片水平宽度
    private Integer sizeX;
    // 水印图片垂直高度
    private Integer sizeY;

    public Integer getGoodsFileId() {
        return goodsFileId;
    }

    public void setGoodsFileId(Integer goodsFileId) {
        this.goodsFileId = goodsFileId;
    }

    public Integer getMaterielId() {
        return materielId;
    }

    public void setMaterielId(Integer materielId) {
        this.materielId = materielId;
    }

    public Integer getSpreadImageId() {
        return spreadImageId;
    }

    public void setSpreadImageId(Integer spreadImageId) {
        this.spreadImageId = spreadImageId;
    }

    public Integer getStartX() {
        return startX;
    }

    public void setStartX(Integer startX) {
        this.startX = startX;
    }

    public Integer getStartY() {
        return startY;
    }

    public void setStartY(Integer startY) {
        this.startY = startY;
    }

    public Integer getSizeX() {
        return sizeX;
    }

    public void setSizeX(Integer sizeX) {
        this.sizeX = sizeX;
    }

    public Integer getSizeY() {
        return sizeY;
    }

    public void setSizeY(Integer sizeY) {
        this.sizeY = sizeY;
    }

    public List<Double> setGoodsSpreadImage(){
        // 水印图片水平结束位置
        Double endX = Arith.add(this.startX.doubleValue(), this.sizeX.doubleValue());
        // 水印图片垂直结束位置
        Double endY = Arith.add(this.startY.doubleValue(), this.sizeY.doubleValue());
        if (endX.intValue() > 100 || endY.intValue() > 100){
            return null;
        }
        double startX = Arith.div(this.startX.doubleValue(), 100D, 2);
        double startY = Arith.div(this.startY.doubleValue(), 100D, 2);
        endX = Arith.div(endX.doubleValue(), 100D, 2);
        endY = Arith.div(endY.doubleValue(), 100D, 2);
        return Arrays.asList(startX, startY, endX, endY);
    }
}
