package com.applet.apply.entity;

import java.io.Serializable;

public class ViewGoodsFile implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer goodsId;

    private Integer fileType;

    private String fileSrc;

    private Boolean fileStatus;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getFileSrc() {
        return fileSrc;
    }

    public void setFileSrc(String fileSrc) {
        this.fileSrc = fileSrc == null ? null : fileSrc.trim();
    }

    public Boolean getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(Boolean fileStatus) {
        this.fileStatus = fileStatus;
    }
}