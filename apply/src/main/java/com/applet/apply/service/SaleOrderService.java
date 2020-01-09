package com.applet.apply.service;

import com.applet.common.bo.PageBo;
import com.applet.common.bo.SaleOrderBo;
import com.applet.common.util.Page;
import com.applet.common.vo.SaleOrderVo;
import org.springframework.transaction.annotation.Transactional;

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
    Page<SaleOrderVo> findPage(PageBo<SaleOrderBo> bo);

    /**
     * 更新订单状态
     *
     * @param bo 订单对象
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
    boolean updateOrderStatus(SaleOrderBo bo);

    /**
     * 订单详情
     *
     * @param orderId 订单Id
     * @return
     */
    SaleOrderVo detail(Integer orderId);

    /**
     * 创建销售订单
     *
     * @param bo
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
    boolean create(SaleOrderBo bo);
}
