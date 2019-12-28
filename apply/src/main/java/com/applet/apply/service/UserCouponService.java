package com.applet.apply.service;

import com.applet.apply.entity.ViewUserCoupon;
import com.applet.apply.entity.ViewUserCouponExample;
import com.applet.apply.mapper.CouponInfoMapper;
import com.applet.apply.mapper.UserCouponMapper;
import com.applet.apply.mapper.ViewCouponInfoMapper;
import com.applet.apply.mapper.ViewUserCouponMapper;
import com.applet.common.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2019/12/29
 * Time: 0:40
 * To change this template use File | Settings | File Templates.
 * Description: 用户优惠券服务类
 */
@Service
public class UserCouponService {
    @Autowired
    private UserCouponMapper userCouponMapper;
    @Autowired
    private ViewUserCouponMapper viewUserCouponMapper;
    @Autowired
    private CouponInfoMapper couponInfoMapper;
    @Autowired
    private ViewCouponInfoMapper viewCouponInfoMapper;

    /**
     * 查询用户优惠券记录
     * @param userId
     * @param type
     * @return
     */
    public Page selectUserCouponList(Integer userId, Integer type, Page page){
        ViewUserCouponExample example = new ViewUserCouponExample();
        example.setPage(page);
        example.setOrderByClause("activity_over desc");
        switch (type){
            case 1:
                // 查询用户未使用的正常优惠券
                example.createCriteria().andUserIdEqualTo(userId).andActivityOverGreaterThan(new Date()).andStatusEqualTo(0);
                break;
            case 2:
                // 查询用户已使用的优惠券
                example.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(2);
                break;
            case 3:
                // 查询用户未使用的已过期优惠券
                example.createCriteria().andUserIdEqualTo(userId).andActivityOverLessThan(new Date()).andStatusEqualTo(0);
                break;
            default:
                return null;
        }
        long count = viewUserCouponMapper.countByExample(example);
        if (count > 0){
            page.setTotalCount(count);
            page.setDataSource(viewUserCouponMapper.selectByExample(example));
        }
        return page;
    }
}
