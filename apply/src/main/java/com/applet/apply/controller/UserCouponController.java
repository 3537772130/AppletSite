package com.applet.apply.controller;

import com.applet.apply.config.annotation.SessionScope;
import com.applet.apply.entity.ViewWeChantInfo;
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
     *  分页查询用户优惠券列表
     * @param weChantInfo
     * @param type
     * @return
     */
    @RequestMapping(value = "queryUserCouponPage")
    public Object queryUserCouponPage(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer type, HttpServletRequest request) {
        if (NullUtil.isNullOrEmpty(type)){
            return AjaxResponse.error("参数错误");
        }
        Page page = PageUtil.initPage(request);
        page = userCouponService.selectUserCouponList(weChantInfo.getUserId(), type, page);
        return AjaxResponse.success(page);
    }
}
