package com.applet.manage.entity;

import com.applet.common.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class AppletPage implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer fileId;
    private String pageLogo;
    private String pageName;
    private Boolean ifEdit;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = Constants.DATE_TIME_JDK)
    private Date updateTime;
    private Boolean pageStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getPageLogo() {
        return pageLogo;
    }

    public void setPageLogo(String pageLogo) {
        this.pageLogo = pageLogo == null ? null : pageLogo.trim();
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName == null ? null : pageName.trim();
    }

    public Boolean getIfEdit() {
        return ifEdit;
    }

    public void setIfEdit(Boolean ifEdit) {
        this.ifEdit = ifEdit;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getPageStatus() {
        return pageStatus;
    }

    public void setPageStatus(Boolean pageStatus) {
        this.pageStatus = pageStatus;
    }
}
