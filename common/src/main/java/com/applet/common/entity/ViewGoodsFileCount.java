package com.applet.common.entity;

import java.io.Serializable;

public class ViewGoodsFileCount implements Serializable {
    private Integer goodsId;

    private Long fileCount;

    private static final long serialVersionUID = 1L;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Long getFileCount() {
        return fileCount;
    }

    public void setFileCount(Long fileCount) {
        this.fileCount = fileCount;
    }
}