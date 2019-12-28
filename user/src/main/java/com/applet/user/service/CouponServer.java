package com.applet.user.service;

import com.applet.common.util.NullUtil;
import com.applet.common.util.Page;
import com.applet.user.entity.*;
import com.applet.user.mapper.CouponInfoMapper;
import com.applet.user.mapper.ViewCouponInfoMapper;
import com.applet.user.mapper.ViewUserCouponMapper;
import io.swagger.models.auth.In;
import jodd.datetime.JDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2019/12/26
 * Time: 11:27
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Service
public class CouponServer {
    @Autowired
    private CouponInfoMapper couponInfoMapper;
    @Autowired
    private ViewCouponInfoMapper viewCouponInfoMapper;
    @Autowired
    private ViewUserCouponMapper viewUserCouponMapper;

    /**
     * 分页查询优惠券创建记录
     * @param userId
     * @param couponName
     * @param couponType
     * @param startDate
     * @param endDate
     * @param status
     * @param page
     * @return
     */
    public Page selectCouponInfoByPage(Integer userId, String couponName, Integer couponType, String startDate, String endDate, Integer status, Page page){
        ViewCouponInfoExample example = new ViewCouponInfoExample();
        example.setOrderByClause("id desc");
        ViewCouponInfoExample.Criteria c = example.createCriteria();
        if (NullUtil.isNotNullOrEmpty(couponName)){
            c.andCouponNameLike("%" + couponName + "%");
        }
        if (NullUtil.isNotNullOrEmpty(couponType)){
            c.andCouponTypeEqualTo(couponType);
        }
        if (NullUtil.isNotNullOrEmpty(startDate)){
            JDateTime time = new JDateTime(startDate);
            c.andCreateTimeLessThanOrEqualTo(time.convertToDate());
        }
        if (NullUtil.isNotNullOrEmpty(endDate)){
            JDateTime time = new JDateTime(endDate);
            c.andCreateTimeGreaterThanOrEqualTo(time.convertToDate());
        }
        if (NullUtil.isNotNullOrEmpty(status)){
            c.andStatusEqualTo(status);
        }
        c.andUserIdEqualTo(userId);
        long count = viewCouponInfoMapper.countByExample(example);
        if (count > 0){
            page.setTotalCount(count);
            page.setDataSource(viewCouponInfoMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 查询优惠券详情
     * @param id
     * @param userId
     * @return
     */
    public ViewCouponInfo selectCouponDetails(Integer id, Integer userId){
        ViewCouponInfoExample example = new ViewCouponInfoExample();
        example.createCriteria().andIdEqualTo(id).andUserIdEqualTo(userId);
        List<ViewCouponInfo> list = viewCouponInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询优惠券信息
     * @param id
     * @param userId
     * @param couponType
     * @return
     */
    public CouponInfo selectCouponInfo(Integer id, Integer userId, Integer couponType){
        CouponInfoExample example = new CouponInfoExample();
        CouponInfoExample.Criteria c = example.createCriteria();
        if (NullUtil.isNotNullOrEmpty(userId)){
            c.andUserIdEqualTo(userId);
        }
        if (NullUtil.isNotNullOrEmpty(couponType)){
            c.andCouponTypeEqualTo(couponType);
        }
        c.andIdEqualTo(id);
        List<CouponInfo> list = couponInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 更新创建的优惠券信息
     * @param info
     */
    public void updateCouponInfo(CouponInfo info){
        if (NullUtil.isNotNullOrEmpty(info.getId())){
            info.setAlreadyIssueNum(null);
            couponInfoMapper.updateByPrimaryKeySelective(info);
        } else {
            info.setAlreadyIssueNum(0);
            info.setCreateTime(new Date());
            couponInfoMapper.insertSelective(info);
        }
    }

    /**
     * 查询用户优惠券记录
     * @param userId
     * @param type
     * @return
     */
    public List<ViewUserCoupon> selectUserCouponList(Integer userId, Integer type){
        ViewUserCouponExample example = new ViewUserCouponExample();
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
        return viewUserCouponMapper.selectByExample(example);
    }

}
