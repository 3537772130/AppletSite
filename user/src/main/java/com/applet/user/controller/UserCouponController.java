package com.applet.user.controller;

import com.applet.common.util.*;
import com.applet.user.config.annotation.SessionScope;
import com.applet.user.entity.*;
import com.applet.user.service.AppletService;
import com.applet.user.service.CouponServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2019/12/26
 * Time: 11:26
 * To change this template use File | Settings | File Templates.
 * Description: 用户优惠券管理
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/user/coupon/")
public class UserCouponController {
    @Autowired
    private CouponServer couponServer;
    @Autowired
    private AppletService appletService;

    /**
     * 分页加载优惠券列表
     *
     * @param user
     * @param couponName
     * @param couponType
     * @param startDate
     * @param endDate
     * @param status
     * @param request
     * @return
     */
    @RequestMapping(value = "queryCouponPage")
    public Object queryCouponPage(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, String couponName, Integer couponType,
                                  String startDate, String endDate, Integer status, HttpServletRequest request) {
        Page page = PageUtil.initPage(request);
        page = couponServer.selectCouponInfoByPage(user.getId(), couponName, couponType, startDate, endDate, status, page);
        return AjaxResponse.success(page);
    }

    /**
     * 加载优惠券详情
     *
     * @param user
     * @param id
     * @return
     */
    @RequestMapping(value = "loadCouponDetails")
    public Object loadCouponDetails(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer id) {
        ViewCouponInfo info = couponServer.selectCouponDetails(id, user.getId());
        if (null != info) {
            return AjaxResponse.success(info);
        }
        return AjaxResponse.error("未找到相关记录");
    }

    /**
     * 优惠券添加加载小程序
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "loadAddCouponInfo")
    public Object loadAddCouponInfo(@SessionScope(Constants.VUE_USER_INFO) UserInfo user) {
        List<AppletInfo> appletList = appletService.selectAppletInfo(user.getId());
        if (NullUtil.isNotNullOrEmpty(appletList)) {
            List<Map> list = new ArrayList<>();
            for (AppletInfo appletInfo : appletList) {
                Map map = new HashMap();
                map.put("id", appletInfo.getId());
                map.put("name", NullUtil.isNotNullOrEmpty(appletInfo.getAppletSimple()) ? appletInfo.getAppletSimple() : appletInfo.getAppletName());
                list.add(map);
            }
            return AjaxResponse.success(list);
        }
        return AjaxResponse.error("您还没有小程序");
    }

    /**
     * 添加优惠券
     *
     * @param user
     * @param coupon
     * @return
     */
    @RequestMapping(value = "addCouponInfo")
    public Object addCouponInfo(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, CouponInfo coupon) {
        try {
            if (null == coupon) {
                return AjaxResponse.error("参数错误");
            }
            if (NullUtil.isNotNullOrEmpty(coupon.getId())) {
                return AjaxResponse.error("优惠券不允许修改,若信息填写有误，请下架后重新发布新的优惠券");
            }
            if (NullUtil.isNullOrEmpty(coupon.getCouponName())) {
                return AjaxResponse.error("优惠券名称不能为空");
            }
            if (coupon.getCouponName().getBytes().length > 60) {
                return AjaxResponse.error("优惠券名称长度为1-20个字符");
            }
            if (NullUtil.isNullOrEmpty(coupon.getCouponType())) {
                return AjaxResponse.error("优惠券类型参数丢失");
            }
            coupon.setStatus(1);
            if (coupon.getCouponType().intValue() != 1) {
                // 非通用优惠券必须参数
                if (NullUtil.isNullOrEmpty(coupon.getGainAppletId())) {
                    return AjaxResponse.error("获取途径不能为空");
                }
                if (coupon.getCouponType().intValue() == 3) {
                    coupon.setStatus(0);
                }
            }
            if (NullUtil.isNullOrEmpty(coupon.getGainPrice())) {
                return AjaxResponse.error("获取额度不能为空");
            }
            if (coupon.getGainPrice().doubleValue() < 0.0d) {
                return AjaxResponse.error("获取金额限制必须大于等于0");
            }
            if (NullUtil.isNullOrEmpty(coupon.getUseAppletId())) {
                return AjaxResponse.error("应用途径不能为空");
            }
            if (NullUtil.isNullOrEmpty(coupon.getUsePrice())) {
                return AjaxResponse.error("使用额度不能为空");
            }
            if (coupon.getGainPrice().doubleValue() < 0.0d) {
                return AjaxResponse.error("获取额度必须大于等于0");
            }
            if (NullUtil.isNullOrEmpty(coupon.getDenomination())) {
                return AjaxResponse.error("面额不能为空");
            }
            if (coupon.getDenomination().doubleValue() <= 0.0d) {
                return AjaxResponse.error("面额必须大于0");
            }
            if (NullUtil.isNullOrEmpty(coupon.getMakeIssueNum())) {
                return AjaxResponse.error("预颁发数量不能为空");
            }
            if (coupon.getMakeIssueNum().intValue() < 0) {
                return AjaxResponse.error("预派发数量必须大于等于0");
            }
            if (NullUtil.isNullOrEmpty(coupon.getActivityStart())) {
                return AjaxResponse.error("活动开始时间不能为空");
            }
            if (NullUtil.isNullOrEmpty(coupon.getActivityOver())) {
                return AjaxResponse.error("活动过期时间不能为空");
            }
            coupon.setUserId(user.getId());
            couponServer.updateCouponInfo(coupon);
            return AjaxResponse.success("发布优惠券成功");
        } catch (Exception e) {
            log.error("发布优惠券出错{}", e);
            return AjaxResponse.error("发布优惠券失败");
        }
    }

    /**
     * 下架优惠券
     *
     * @param user
     * @param couponId
     * @param couponType
     * @return
     */
    @RequestMapping(value = "stopCoupon")
    public Object stopCoupon(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer couponId, Integer couponType) {
        try {
            CouponInfo info = couponServer.selectCouponInfo(couponId, user.getId(), couponType);
            if (null == info) {
                return AjaxResponse.error("未找到相关记录");
            }
            info.setStatus(2);
            couponServer.updateCouponInfo(info);
            return AjaxResponse.success("下架优惠券成功");
        } catch (Exception e) {
            log.error("下架优惠券失败");
            return AjaxResponse.error("下架优惠券失败");
        }
    }

    /**
     * 加载用户优惠券列表
     * @param user
     * @param type
     * @return
     */
    @RequestMapping(value = "loadMyCoupon")
    public Object loadMyCoupon(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer type){
        if (NullUtil.isNullOrEmpty(type)){
            return AjaxResponse.error("参数错误");
        }
        List<ViewUserCoupon> list = couponServer.selectUserCouponList(user.getId(), type);
        if (NullUtil.isNotNullOrEmpty(list)){
            return AjaxResponse.success(list);
        }
        return AjaxResponse.error("未找到相关记录");
    }

}
