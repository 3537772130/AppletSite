package com.applet.common.entity;

import java.io.Serializable;

public class ViewGoodsSpecsCount implements Serializable {
    private Integer goodsId;

    private Long specsCount;

    private static final long serialVersionUID = 1L;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Long getSpecsCount() {
        return specsCount;
    }

    public void setSpecsCount(Long specsCount) {
        this.specsCount = specsCount;
    }
}