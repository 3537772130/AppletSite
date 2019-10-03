package com.applet.user.entity;

import java.io.Serializable;

public class RegionInfo implements Serializable {
    private Integer id;

    private static final long serialVersionUID = 1L;

    private Integer parentId;
    private String areaName;

    private Integer level;
    private String shortName;
    private Integer flag;
    private Double wgs84Lng;
    private Double wgs84Lat;
    private Double gcj02Lng;
    private Double gcj02Lat;
    private Double bd09Lng;
    private Double bd09Lat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Double getWgs84Lng() {
        return wgs84Lng;
    }

    public void setWgs84Lng(Double wgs84Lng) {
        this.wgs84Lng = wgs84Lng;
    }

    public Double getWgs84Lat() {
        return wgs84Lat;
    }

    public void setWgs84Lat(Double wgs84Lat) {
        this.wgs84Lat = wgs84Lat;
    }

    public Double getGcj02Lng() {
        return gcj02Lng;
    }

    public void setGcj02Lng(Double gcj02Lng) {
        this.gcj02Lng = gcj02Lng;
    }

    public Double getGcj02Lat() {
        return gcj02Lat;
    }

    public void setGcj02Lat(Double gcj02Lat) {
        this.gcj02Lat = gcj02Lat;
    }

    public Double getBd09Lng() {
        return bd09Lng;
    }

    public void setBd09Lng(Double bd09Lng) {
        this.bd09Lng = bd09Lng;
    }

    public Double getBd09Lat() {
        return bd09Lat;
    }

    public void setBd09Lat(Double bd09Lat) {
        this.bd09Lat = bd09Lat;
    }
}
