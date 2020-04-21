package com.applet.manage.service;

import com.applet.common.entity.ViewOrderInfo;
import com.applet.common.entity.ViewOrderInfoExample;
import com.applet.common.mapper.ViewOrderInfoMapper;
import com.applet.common.util.NullUtil;
import com.applet.common.util.Page;
import jodd.datetime.JDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2020/4/20
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@SuppressWarnings("ALL")
@Service
public class ManageOrderService {
    @Autowired
    private ViewOrderInfoMapper viewOrderInfoMapper;

    /**
     * 分页查询订单列表
     *
     * @param orderNo
     * @param courierNo
     * @param appletName
     * @param userMobile
     * @param startTime
     * @param endTime
     * @param payRelationId
     * @param orderStatus
     * @param page
     * @return
     */
    public Page selectOrderInfoByPage(String orderNo, String courierNo, String appletName,
                                      String userMobile, String startTime, String endTime,
                                      String payRelationId, Integer orderStatus, Page page) {
        ViewOrderInfoExample example = new ViewOrderInfoExample();
        example.setPage(page);
        example.setOrderByClause("create_time desc");
        ViewOrderInfoExample.Criteria c = example.createCriteria();
        if (NullUtil.isNotNullOrEmpty(orderNo)) {
            c.andOrderNoLike(orderNo + "%");
        }
        if (NullUtil.isNotNullOrEmpty(courierNo)) {
            c.andCourierNoLike(courierNo + "%");
        }
        if (NullUtil.isNotNullOrEmpty(appletName)) {
            c.andAppletNameLike("%" + appletName + "%");
        }
        if (NullUtil.isNotNullOrEmpty(userMobile)) {
            c.andUserMobileEqualTo(userMobile);
        }
        if (NullUtil.isNotNullOrEmpty(startTime)) {
            JDateTime time = new JDateTime(startTime);
            c.andCreateTimeLessThanOrEqualTo(time.convertToDate());
        }
        if (NullUtil.isNotNullOrEmpty(endTime)) {
            JDateTime time = new JDateTime(endTime);
            c.andCreateTimeGreaterThanOrEqualTo(time.convertToDate());
        }
        if (NullUtil.isNotNullOrEmpty(payRelationId)) {
            c.andPayRelationIdEqualTo(payRelationId);
        }
        if (NullUtil.isNotNullOrEmpty(orderStatus)) {
            c.andOrderStatusEqualTo(orderStatus);
        }
        long count = viewOrderInfoMapper.countByExample(example);
        if (count > 0) {
            page.setTotalCount(count);
            page.setDataSource(viewOrderInfoMapper.selectByExample(example));
        }
        return page;
    }
}
