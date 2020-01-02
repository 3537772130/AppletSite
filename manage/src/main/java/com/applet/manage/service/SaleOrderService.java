package com.applet.manage.service;

import com.applet.common.bo.PageBo;
import com.applet.common.bo.SaleOrderBo;
import com.applet.common.vo.PageVo;
import com.applet.common.vo.SaleOrderVo;

/**
 * 销售订单 - Service
 *
 * @author 谭良忠
 * @date 2020/1/2 9:50
 */
public interface SaleOrderService {


    /**
     * 分页查询
     *
     * @param bo
     * @return
     */
    PageVo<SaleOrderVo> findPage(PageBo<SaleOrderBo> bo);

    /**
     * 更新订单状态
     *
     * @param orderId 订单Id
     * @param status  状态
     * @return
     */
    boolean updateOrderStatus(Integer orderId, Byte status);

    /**
     * 订单详情
     *
     * @param orderId 订单Id
     * @return
     */
    SaleOrderVo detail(Integer orderId);
}
