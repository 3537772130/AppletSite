package com.applet.common.entity;

import com.applet.common.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论回复记录 - 视图
 */
public class ViewCommentReplyRecord implements Serializable {
    private Integer id;

    private Integer commentId;

    private Integer aimUserId;

    private String aimNickName;

    private String aimAvatarUrl;

    private Integer replyUserId;

    private String replyNickName;

    private String replyAvatarUrl;

    private String replyContent;

    private Integer replyIndex;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_TIME_ZH_HM)
    private Date replyTime;

    private Boolean replyStatus;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getAimUserId() {
        return aimUserId;
    }

    public void setAimUserId(Integer aimUserId) {
        this.aimUserId = aimUserId;
    }

    public String getAimNickName() {
        return aimNickName;
    }

    public void setAimNickName(String aimNickName) {
        this.aimNickName = aimNickName == null ? null : aimNickName.trim();
    }

    public String getAimAvatarUrl() {
        return aimAvatarUrl;
    }

    public void setAimAvatarUrl(String aimAvatarUrl) {
        this.aimAvatarUrl = aimAvatarUrl == null ? null : aimAvatarUrl.trim();
    }

    public Integer getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Integer replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getReplyNickName() {
        return replyNickName;
    }

    public void setReplyNickName(String replyNickName) {
        this.replyNickName = replyNickName == null ? null : replyNickName.trim();
    }

    public String getReplyAvatarUrl() {
        return replyAvatarUrl;
    }

    public void setReplyAvatarUrl(String replyAvatarUrl) {
        this.replyAvatarUrl = replyAvatarUrl == null ? null : replyAvatarUrl.trim();
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }

    public Integer getReplyIndex() {
        return replyIndex;
    }

    public void setReplyIndex(Integer replyIndex) {
        this.replyIndex = replyIndex;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Boolean getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(Boolean replyStatus) {
        this.replyStatus = replyStatus;
    }
}
