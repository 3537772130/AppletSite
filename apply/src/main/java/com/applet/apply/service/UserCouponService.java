package com.applet.apply.service;

import com.applet.common.entity.*;
import com.applet.common.util.enums.OrderEnums;
import com.applet.common.mapper.*;
import com.applet.common.util.NullUtil;
import com.applet.common.util.Page;
import jodd.datetime.JDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
     * 查询用户订单获取的优惠券数量
     * @param userId
     * @param orderId
     * @return
     */
    public long selectUserCouponCountByOrder(Integer userId, Integer orderId){
        ViewUserCouponExample example = new ViewUserCouponExample();
        example.setOrderByClause("denomination desc");
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andOrderIdEqualTo(orderId)
                .andActivityOverGreaterThan(new Date())
                .andStatusEqualTo(0);
        return viewUserCouponMapper.countByExample(example);
    }

    /**
     * 查询用户订单获取的优惠券集合
     * @param userId
     * @param orderId
     * @return
     */
    public List<ViewUserCoupon> selectUserCouponListByOrder(Integer userId, Integer orderId){
        ViewUserCouponExample example = new ViewUserCouponExample();
        example.setOrderByClause("denomination desc");
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andOrderIdEqualTo(orderId)
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
     * 用户支付成功，被动获取优惠券
     * @param appletId
     * @param userId
     * @param totalAmount
     */
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void userGainCoupon(ViewOrderDetails order, Integer storeUserId){
        // 查询其它商家发布的点对点优惠券
//        List<Map> list = selectCouponListByOrderToOther(order, storeUserId);
        // 查询商家自己发布的满送优惠券
        List<Map> list = new ArrayList<>();
        CouponInfo couponInfo = selectCouponListByOrder(order, storeUserId);
        if (null != couponInfo){
            list.add(couponInfo.getCouponMap());
        }
        for (Map map:list) {
            int makeIssueNum = Integer.parseInt(map.get("makeIssueNum").toString());
            int alreadyIssueNum = Integer.parseInt(map.get("alreadyIssueNum").toString());
            if (makeIssueNum == 0 || makeIssueNum > alreadyIssueNum){
                int couponId = Integer.parseInt(map.get("id").toString());
                // 存在可以获取的优惠券，并且不限量或未派发完毕
                UserCoupon record = new UserCoupon();
                record.setUserId(order.getUserId());
                record.setOrderId(order.getId());
                record.setCouponId(couponId);
                record.setGainTime(new Date());
                record.setStatus(OrderEnums.UserCouponStatus.UNUSED.getCode());
                userCouponMapper.insertSelective(record);

                int status = (alreadyIssueNum + 1) == makeIssueNum ? 3 : null;
                CouponInfo info = new CouponInfo();
                info.setId(couponId);
                info.setAlreadyIssueNum(alreadyIssueNum + 1);
                info.setStatus(status);
                couponInfoMapper.updateByPrimaryKeySelective(info);
            }
        }
    }


    /**
     * 添加用户优惠券
     * @param couponInfo
     * @param userId
     */
    @Transactional(rollbackFor = Exception.class)
    public void addUserCoupon(CouponInfo couponInfo, Integer userId, Integer orderId){
        UserCoupon record = new UserCoupon();
        record.setUserId(userId);
        record.setOrderId(orderId);
        record.setCouponId(couponInfo.getId());
        record.setGainTime(new Date());
        record.setStatus(OrderEnums.UserCouponStatus.UNUSED.getCode());
        userCouponMapper.insertSelective(record);

        Integer status = (couponInfo.getAlreadyIssueNum().intValue() + 1) == couponInfo.getMakeIssueNum().intValue() ? 3 : null;
        CouponInfo info = new CouponInfo();
        info.setId(couponInfo.getId());
        info.setAlreadyIssueNum(couponInfo.getAlreadyIssueNum() + 1);
        info.setStatus(status);
        couponInfoMapper.updateByPrimaryKeySelective(info);
    }

    public void addUserCoupon(CouponInfo couponInfo, Integer userId){
        addUserCoupon(couponInfo, userId, null);
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
                .andGainTypeEqualTo(1)
                .andUseAppletIdEqualTo(appletId)
                .andActivityStartLessThan(new Date())
                .andActivityOverGreaterThan(new Date())
                .andStatusEqualTo(1);
        return viewCouponInfoMapper.selectByExample(example);
    }

    /**
     * 查询商家自己发布的有效的被动获取优惠券集合
     * @param order
     * @param storeUserId
     * @return
     */
    public CouponInfo selectCouponListByOrder(ViewOrderDetails order, Integer storeUserId){
        CouponInfoExample example = new CouponInfoExample();
        example.setOrderByClause("denomination desc");
        example.createCriteria().andUserIdEqualTo(storeUserId)
                .andGainTypeEqualTo(2)
                .andGainAppletIdEqualTo(order.getAppletId())
                .andGainPriceLessThanOrEqualTo(order.getTotalAmount())
                .andActivityOverGreaterThanOrEqualTo(new Date())
                .andStatusEqualTo(1);
        List<CouponInfo> list = couponInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询其它商家发布的有效被动获取优惠券集合
     * @param order
     * @param storeUserId
     * @return
     */
    public CouponInfo selectCouponListByOrderToOther(ViewOrderDetails order, Integer storeUserId){
        CouponInfoExample example = new CouponInfoExample();
        example.setOrderByClause("denomination desc");
        example.createCriteria().andUserIdNotEqualTo(storeUserId)
                .andGainTypeEqualTo(2)
                .andGainAppletIdEqualTo(order.getAppletId())
                .andGainPriceLessThanOrEqualTo(order.getTotalAmount())
                .andActivityOverGreaterThanOrEqualTo(new Date())
                .andStatusEqualTo(1);
        List<CouponInfo> list = couponInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
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
                .andGainTypeEqualTo(1)
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
        if (NullUtil.isNotNullOrEmpty(id)){
            UserCoupon coupon = new UserCoupon();
            coupon.setId(id);
            if (status == OrderEnums.UserCouponStatus.USED.getCode()){
                coupon.setUseTime(new Date());
            }
            coupon.setStatus(status);
            userCouponMapper.updateByPrimaryKeySelective(coupon);
        }
    }



    /**
     * 计算运费
     * @param appletId 小程序ID
     * @param distance 距离（米）
     * @param goodsAmount 商品总金额
     * @return
     */
    public Double countFreight(Integer appletId, Integer distance, Double goodsAmount) {
        //计算公里
        FreightDeployExample example = new FreightDeployExample();
        example.setOrderByClause("minimum desc");
        example.createCriteria().andAppletIdEqualTo(appletId).andStatusEqualTo(1);
        List<FreightDeploy> list = freightDeployMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)){
            for (FreightDeploy fd:list) {
                // 开始检索范围
                if (fd.getMinimum().intValue() <= distance && fd.getMaximum().intValue() >= distance.intValue()){
                    if (fd.getExemptAmount().doubleValue() > 0){
                        // 排查是否免额
                        return goodsAmount.doubleValue() >= fd.getExemptAmount().doubleValue() ? 0.0d : fd.getFreight();
                    } else{
                        return fd.getFreight();
                    }
                }
            }
            // 超出配送范围
            return -1.0d;
        } else {
            // 未设置运费
            return 0.0d;
        }
    }
}
