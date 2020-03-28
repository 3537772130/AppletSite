package com.applet.common.entity;

import com.applet.common.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论信息
 */
public class CommentInfo implements Serializable {
    private Integer id;

    private Integer orderId;

    private Integer goodsId;

    private Integer commentUserId;

    private String commentContent;

    private String commentImg1;

    private String commentImg2;

    private String commentImg3;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_TIME_JDK)
    private Date commentTime;

    private Boolean commentStatus;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(Integer commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    public String getCommentImg1() {
        return commentImg1;
    }

    public void setCommentImg1(String commentImg1) {
        this.commentImg1 = commentImg1 == null ? null : commentImg1.trim();
    }

    public String getCommentImg2() {
        return commentImg2;
    }

    public void setCommentImg2(String commentImg2) {
        this.commentImg2 = commentImg2 == null ? null : commentImg2.trim();
    }

    public String getCommentImg3() {
        return commentImg3;
    }

    public void setCommentImg3(String commentImg3) {
        this.commentImg3 = commentImg3 == null ? null : commentImg3.trim();
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Boolean getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Boolean commentStatus) {
        this.commentStatus = commentStatus;
    }
}
