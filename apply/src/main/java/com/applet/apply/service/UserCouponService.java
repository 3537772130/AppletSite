package com.applet.apply.service;

import com.applet.apply.entity.*;
import com.applet.apply.mapper.*;
import com.applet.common.util.NullUtil;
import com.applet.common.util.Page;
import jodd.datetime.JDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
@SuppressWarnings("ALL")
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
    @Autowired
    private FreightDeployMapper freightDeployMapper;
    @Autowired
    private SaleOrderDocMapper saleOrderDocMapper;

    /**
     * 分页查询用户可正常待使用的优惠券记录
     * @param userId
     * @return
     */
    public Page selectUserCouponList(Integer userId, Page page){
        ViewUserCouponExample example = new ViewUserCouponExample();
        example.setPage(page);
        example.setOrderByClause("activity_over desc");
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andActivityOverGreaterThan(new Date())
                .andStatusEqualTo(0);
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
        example.setOrderByClause("denomination desc");
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andUseAppletIdEqualTo(useAppletId)
                .andUsePriceLessThanOrEqualTo(mountPrice)
                .andActivityOverGreaterThan(new Date())
                .andStatusEqualTo(0);
        return viewUserCouponMapper.selectByExample(example);
    }

    /**
     * 查询用户优惠券详情
     * @param id
     * @param userId
     * @return
     */
    public ViewUserCoupon selectUserCoupon(Integer id, Integer userId){
        ViewUserCouponExample example = new ViewUserCouponExample();
        example.createCriteria()
                .andIdEqualTo(id)
                .andUserIdEqualTo(userId);
        List<ViewUserCoupon> list = viewUserCouponMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询用户优惠券详情（未过期、未使用）
     * @param id
     * @param userId
     * @return
     */
    public ViewUserCoupon selectUserCouponInfo(Integer id, Integer userId){
        ViewUserCouponExample example = new ViewUserCouponExample();
        example.createCriteria()
                .andIdEqualTo(id)
                .andUserIdEqualTo(userId)
                .andActivityOverGreaterThan(new Date())
                .andStatusEqualTo(0);
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
        example.createCriteria()
                .andGainAppletIdEqualTo(gainAppletId)
                .andActivityStartLessThan(new Date())
                .andActivityOverGreaterThan(new Date())
                .andStatusEqualTo(1);
        return couponInfoMapper.selectByExample(example);
    }

    /**
     * 添加用户优惠券
     * @param couponInfo
     * @param userId
     */
    @Transactional(rollbackFor = Exception.class)
    public void addUserCoupon(CouponInfo couponInfo, Integer userId){
        UserCoupon record = new UserCoupon();
        record.setUserId(userId);
        record.setCouponId(couponInfo.getId());
        record.setGainTime(new Date());
        record.setStatus(0);
        userCouponMapper.insertSelective(record);

        int status = (couponInfo.getAlreadyIssueNum().intValue() + 1) == couponInfo.getMakeIssueNum() ? 2 : 1;
        CouponInfo info = new CouponInfo();
        info.setId(couponInfo.getId());
        info.setAlreadyIssueNum(couponInfo.getAlreadyIssueNum() + 1);
        info.setStatus(status);
        couponInfoMapper.updateByPrimaryKeySelective(info);
    }

    /**
     * 查询小程序可派发的优惠券集合
     * @param appletId
     * @return
     */
    public List<ViewCouponInfo> selectCouponList(Integer appletId){
        ViewCouponInfoExample example = new ViewCouponInfoExample();
        example.setOrderByClause("denomination asc");
        example.createCriteria()
                .andGainAppletIdEqualTo(appletId)
                .andUseAppletIdEqualTo(appletId)
                .andActivityStartLessThan(new Date())
                .andActivityOverGreaterThan(new Date())
                .andStatusEqualTo(1);
        return viewCouponInfoMapper.selectByExample(example);
    }

    /**
     * 检测优惠券是否可被当前用户领取
     * 同一优惠券一天领一次，直到用完后第二天可再领
     * @param couponId
     * @param userId
     * @param useAppletId
     * @return
     */
    public Boolean checkUserCouponInfo(Integer couponId, Integer userId, Integer useAppletId){
        ViewUserCouponExample example = new ViewUserCouponExample();
        // 检测是否存在未使用的
        example.createCriteria()
                .andCouponIdEqualTo(couponId)
                .andUserIdEqualTo(userId)
                .andUseAppletIdEqualTo(useAppletId)
                .andStatusEqualTo(0);
        // 检测是否存在使用中的
        example.or()
                .andCouponIdEqualTo(couponId)
                .andUserIdEqualTo(userId)
                .andStatusEqualTo(1);
        // 检测今天是否已使用
        JDateTime time = new JDateTime(new Date());
        example.or()
                .andCouponIdEqualTo(couponId)
                .andUserIdEqualTo(userId)
                .andUseTimeGreaterThanOrEqualTo(time.setHour(0).setMinute(0).setSecond(0).convertToDate())
                .andUseTimeLessThanOrEqualTo(time.setHour(23).setMinute(59).setSecond(59).convertToDate())
                .andStatusEqualTo(2);
        List<ViewUserCoupon> list = viewUserCouponMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? false : true;
    }

    /**
     * 查询优惠券详情
     * @param id
     * @param appletId
     * @return
     */
    public CouponInfo selectCouponList(Integer id, Integer appletId){
        CouponInfoExample example = new CouponInfoExample();
        example.createCriteria()
                .andIdEqualTo(id)
                .andGainAppletIdEqualTo(appletId)
                .andUseAppletIdEqualTo(appletId)
                .andActivityStartLessThan(new Date())
                .andActivityOverGreaterThan(new Date())
                .andStatusEqualTo(1);
        List<CouponInfo> list = couponInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 更新用户优惠券状态
     * @param id
     * @param status
     */
    public void updateUserCouponStatus(Integer id, Integer status){
        UserCoupon coupon = new UserCoupon();
        coupon.setId(id);
        coupon.setUseTime(new Date());
        coupon.setStatus(status);
        userCouponMapper.updateByPrimaryKeySelective(coupon);
    }



    /**
     * 计算运费
     * @param appletId 小程序ID
     * @param distance 距离（米）
     * @return
     */
    public Double countFreight(Integer appletId, Integer distance) {
        FreightDeployExample example = new FreightDeployExample();
        FreightDeployExample.Criteria c = example.createCriteria();
        c.andAppletIdEqualTo(appletId);
        long count = freightDeployMapper.countByExample(example);
        if (count > 0){
            c.andMinimumLessThanOrEqualTo(distance).andMaximumGreaterThanOrEqualTo(distance);
            List<FreightDeploy> list = freightDeployMapper.selectByExample(example);
            if (NullUtil.isNotNullOrEmpty(list)) {
                return list.get(0).getFreight();
            }
            // 超出配送范围
            return -1.0d;
        } else {
            // 未设置运费
            return 0.0d;
        }
    }
}
