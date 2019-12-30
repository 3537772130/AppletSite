package com.applet.apply.service;

import com.applet.apply.entity.*;
import com.applet.apply.mapper.CouponInfoMapper;
import com.applet.apply.mapper.UserCouponMapper;
import com.applet.apply.mapper.ViewCouponInfoMapper;
import com.applet.apply.mapper.ViewUserCouponMapper;
import com.applet.common.util.NullUtil;
import com.applet.common.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * 分页查询用户可正常使用的优惠券记录
     * @param userId
     * @return
     */
    public Page selectUserCouponList(Integer userId, Page page){
        ViewUserCouponExample example = new ViewUserCouponExample();
        example.setPage(page);
        example.setOrderByClause("activity_over desc");
        example.createCriteria().andUserIdEqualTo(userId).andActivityOverGreaterThan(new Date()).andStatusEqualTo(0);
        long count = viewUserCouponMapper.countByExample(example);
        if (count > 0){
            page.setTotalCount(count);
            page.setDataSource(viewUserCouponMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 查询用户在当前小程序可使用的优惠券
     * @param userId
     * @param useAppletId
     * @param mountPrice
     * @return
     */
    public List<ViewUserCoupon> selectUserCouponList(Integer userId, Integer useAppletId, Double mountPrice){
        ViewUserCouponExample example = new ViewUserCouponExample();
        example.createCriteria().andUserIdEqualTo(userId).andUseAppletIdEqualTo(useAppletId).andUsePriceLessThanOrEqualTo(mountPrice)
                .andActivityOverGreaterThan(new Date()).andStatusEqualTo(0);
        return viewUserCouponMapper.selectByExample(example);
    }

    /**
     * 查询用户优惠券详情
     * @param id
     * @param userId
     * @return
     */
    public ViewUserCoupon selectUserCouponInfo(Integer id, Integer userId){
        ViewUserCouponExample example = new ViewUserCouponExample();
        example.createCriteria().andIdEqualTo(id).andUserIdEqualTo(userId)
                .andActivityOverGreaterThan(new Date()).andStatusEqualTo(0);
        List<ViewUserCoupon> list = viewUserCouponMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询小程序当前可获取的优惠券 面额由高到低
     * @param gainAppletId
     * @return
     */
    public List<CouponInfo> selectGainAppletCouponList(Integer gainAppletId){
        CouponInfoExample example = new CouponInfoExample();
        example.setOrderByClause("denomination desc");
        example.createCriteria().andGainAppletIdEqualTo(gainAppletId).andActivityStartLessThan(new Date())
                .andActivityOverGreaterThan(new Date()).andStatusEqualTo(1);
        return couponInfoMapper.selectByExample(example);
    }

    /**
     * 添加用户优惠券
     * @param coupon
     */
    public void insertUserCouponInfo(UserCoupon coupon){
        userCouponMapper.insertSelective(coupon);
    }

    /**
     * 更新用户优惠券状态
     * @param id
     * @param status
     */
    public void updateUserCouponStatus(Integer id, Integer status){
        UserCoupon coupon = new UserCoupon();
        coupon.setId(id);
        coupon.setStatus(status);
        userCouponMapper.updateByPrimaryKeySelective(coupon);
    }
}
