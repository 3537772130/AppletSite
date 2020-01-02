package com.applet.manage.service.impl;

import com.applet.common.bo.PageBo;
import com.applet.common.bo.SaleOrderBo;
import com.applet.common.util.ObjectUtils;
import com.applet.common.vo.PageVo;
import com.applet.common.vo.SaleOrderDtlVo;
import com.applet.common.vo.SaleOrderVo;
import com.applet.manage.entity.SaleOrderDoc;
import com.applet.manage.entity.SaleOrderDtl;
import com.applet.manage.mapper.SaleOrderDocMapper;
import com.applet.manage.mapper.SaleOrderDtlMapper;
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

    @Override
    public PageVo<SaleOrderVo> findPage(PageBo<SaleOrderBo> bo) {
        PageVo<SaleOrderVo> pageVo = new PageVo<>();
        BeanUtils.copyProperties(bo, pageVo);
        Long total = saleOrderDocMapper.selectSearchCount(ObjectUtils.objToMap(bo.getPage()));
        pageVo.setTotal(total);
        if (bo.getOffset() >= total) {
            pageVo.setDatas(new ArrayList<>(0));
            return pageVo;
        }
        List<SaleOrderDoc> entitys = saleOrderDocMapper.selectSearchData(ObjectUtils.objToMap(bo.getPage()), bo.getOffset(), bo.getSize());
        List<SaleOrderVo> vos = new ArrayList<>();
        entitys.forEach(it -> {
            SaleOrderVo vo = new SaleOrderVo();
            BeanUtils.copyProperties(it, vo);
            vos.add(vo);
        });
        pageVo.setDatas(vos);
        return pageVo;
    }

    @Override
    public boolean updateOrderStatus(Integer orderId, Byte status) {
        SaleOrderDoc order = new SaleOrderDoc();
        order.setGmtModified(new Date());
        order.setOrderId(orderId);
        order.setOrderStatus(status);
        return saleOrderDocMapper.updateByPrimaryKeySelective(order) > 0;
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
