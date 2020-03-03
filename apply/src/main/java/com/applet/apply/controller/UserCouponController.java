package com.applet.apply.controller;

import com.applet.apply.config.annotation.SessionScope;
import com.applet.common.entity.*;
import com.applet.apply.service.UserCouponService;
import com.applet.common.util.AjaxResponse;
import com.applet.common.util.NullUtil;
import com.applet.common.util.Page;
import com.applet.common.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2019/12/29
 * Time: 0:39
 * To change this template use File | Settings | File Templates.
 * Description: 用户优惠券管理类
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/applet/user/coupon/")
public class UserCouponController {
    @Autowired
    private UserCouponService userCouponService;

    /**
     *  分页查询用户可使用优惠券列表
     * @param weChantInfo
     * @return
     */
    @RequestMapping(value = "queryUserCouponPage")
    public Object queryUserCouponPage(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, HttpServletRequest request) {
        Page page = PageUtil.initPage(request);
        page = userCouponService.selectUserCouponList(weChantInfo.getUserId(), page);
        return AjaxResponse.success(page);
    }

    /**
     * 查询用户优惠券详情
     * @param weChantInfo
     * @param userCouponId
     * @return
     */
    @RequestMapping(value = "queryUserCouponDetails")
    public Object queryUserCouponDetails(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer userCouponId){
        ViewUserCoupon coupon = userCouponService.selectUserCouponInfo(userCouponId, weChantInfo.getUserId());
        if (null != coupon){
            return AjaxResponse.success(coupon);
        }
        return AjaxResponse.error("未找到相关记录");
    }

    /**
     * 查询用户在当前小程序可使用的优惠券
     * @param weChantInfo
     * @return
     */
    @RequestMapping(value = "queryUserCouponByUse")
    public Object queryUserCouponByUse(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Double mountPrice){
        List<ViewUserCoupon> list = userCouponService.selectUserCouponList(weChantInfo.getUserId(), weChantInfo.getAppletId(), mountPrice);
        if (NullUtil.isNotNullOrEmpty(list)){
            return AjaxResponse.success(list);
        }
        return AjaxResponse.error("未找到相关记录");
    }

    /**
     * 用户领取优惠券
     * @param appletInfo
     * @param weChantInfo
     * @param couponId
     * @return
     */
    @RequestMapping(value = "userGainCoupon")
    public Object userGainCoupon(@SessionScope("appletInfo") ViewAppletInfo appletInfo, @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer couponId){
        try {
            if (NullUtil.isNullOrEmpty(weChantInfo.getUserId())){
                return AjaxResponse.error("为了您的使用体验，请绑定手机号码");
            }
            boolean bool = userCouponService.checkUserCouponInfo(couponId, weChantInfo.getUserId(), appletInfo.getId());
            if (!bool){
                return AjaxResponse.success("优惠券已经在口袋里啦，快去使用吧 ^_^");
            }
            CouponInfo couponInfo = userCouponService.selectCouponList(couponId, appletInfo.getId());
            if (null == couponInfo){
                return AjaxResponse.error("参数错误");
            }
            if (couponInfo.getStatus().intValue() != 1){
                return AjaxResponse.msg("0","手慢啦，优惠券已经发完了");
            }
            userCouponService.addUserCoupon(couponInfo, weChantInfo.getUserId());
            return AjaxResponse.success("领取成功，快去使用吧 ^_^");
        } catch (Exception e) {
            log.error("领取优惠券出错{}", e);
            return AjaxResponse.error("领取失败");
        }
    }
}
