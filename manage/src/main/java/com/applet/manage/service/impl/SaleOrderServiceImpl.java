package com.applet.manage.service.impl;

import com.applet.common.bo.PageBo;
import com.applet.common.bo.SaleOrderBo;
import com.applet.common.enums.OrderEnums;
import com.applet.common.util.EnumUtil;
import com.applet.common.util.ObjectUtils;
import com.applet.common.util.Page;
import com.applet.common.vo.SaleOrderDtlVo;
import com.applet.common.vo.SaleOrderVo;
import com.applet.manage.entity.SaleOrderDoc;
import com.applet.manage.entity.SaleOrderDtl;
import com.applet.manage.entity.SaleOrderTimeline;
import com.applet.manage.mapper.SaleOrderDocMapper;
import com.applet.manage.mapper.SaleOrderDtlMapper;
import com.applet.manage.mapper.SaleOrderTimelineMapper;
import com.applet.manage.service.SaleOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 销售订单 - Impl
 *
 * @author 谭良忠
 * @date 2020/1/2 9:50
 */
@Service
@RequiredArgsConstructor
public class SaleOrderServiceImpl implements SaleOrderService {

    private final SaleOrderDocMapper saleOrderDocMapper;
    private final SaleOrderDtlMapper saleOrderDtlMapper;
    private final SaleOrderTimelineMapper saleOrderTimelineMapper;

    @Override
    public Page<SaleOrderVo> findPage(PageBo<SaleOrderBo> bo) {
        Long total = saleOrderDocMapper.selectSearchCount(ObjectUtils.objToMap(bo.getPage()));
        Page<SaleOrderVo> pageVo = new Page<>(bo.getPage(), bo.getSize(), total);
        if (bo.getOffset() >= total) {
            pageVo.setDataSource(new ArrayList<>(0));
            return pageVo;
        }
        List<SaleOrderDoc> entitys = saleOrderDocMapper.selectSearchData(ObjectUtils.objToMap(bo.getPage()), bo.getOffset(), bo.getSize());
        List<SaleOrderVo> vos = new ArrayList<>();
        entitys.forEach(it -> {
            SaleOrderVo vo = new SaleOrderVo();
            BeanUtils.copyProperties(it, vo);
            vos.add(vo);
        });
        pageVo.setDataSource(vos);
        return pageVo;
    }

    @Override
    public boolean updateOrderStatus(Integer orderId, Byte status) {
        OrderEnums.OrderStatus orderStatus = EnumUtil.getEnumByCode(status, OrderEnums.OrderStatus.class);
        if (orderStatus == null) {
            return false;
        }
        SaleOrderDoc order = new SaleOrderDoc();
        order.setGmtModified(new Date());
        order.setOrderId(orderId);
        order.setOrderStatus(status);
        order.setOrderStatusCn(orderStatus.getName());
        saleOrderDocMapper.updateByPrimaryKeySelective(order);
        saleOrderTimelineMapper.insertSelective(new SaleOrderTimeline(orderId, status, orderStatus.getName()));
        return true;
    }

    @Override
    public SaleOrderVo detail(Integer orderId) {
        SaleOrderVo docVo = new SaleOrderVo();
        SaleOrderDoc orderDoc = saleOrderDocMapper.selectByPrimaryKey(orderId);
        if (orderDoc == null) {
            return docVo;
        }
        BeanUtils.copyProperties(orderDoc, docVo);
        List<SaleOrderDtlVo> dtlVos = new ArrayList<>();
        List<SaleOrderDtl> orderDtls = saleOrderDtlMapper.findByOrderId(orderId);
        orderDtls.forEach(it -> {
            SaleOrderDtlVo vo = new SaleOrderDtlVo();
            BeanUtils.copyProperties(it, vo);
            dtlVos.add(vo);
        });
        docVo.setDtls(dtlVos);
        return docVo;
    }


}
