package com.applet.common.entity.page;

import com.applet.common.entity.ViewCouponInfo;
import com.applet.common.entity.ViewGoodsInfo;
import com.applet.common.entity.ViewGoodsType;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2020/4/19
 * Time: 22:26
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class GoodsClassify implements Serializable {

    private List<ViewGoodsType> typeList;

    private List<ViewGoodsInfo> infoList;

    private List<ViewCouponInfo> couponList;

    public List<ViewGoodsType> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<ViewGoodsType> typeList) {
        this.typeList = typeList;
    }

    public List<ViewGoodsInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<ViewGoodsInfo> infoList) {
        this.infoList = infoList;
    }

    public List<ViewCouponInfo> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<ViewCouponInfo> couponList) {
        this.couponList = couponList;
    }
}
