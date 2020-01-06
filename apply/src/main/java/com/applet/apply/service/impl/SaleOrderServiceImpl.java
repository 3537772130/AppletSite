package com.applet.apply.service.impl;

import com.applet.apply.entity.*;
import com.applet.apply.mapper.SaleOrderDocMapper;
import com.applet.apply.mapper.SaleOrderDtlMapper;
import com.applet.apply.mapper.SaleOrderTimelineMapper;
import com.applet.apply.mapper.ViewUserCartMapper;
import com.applet.apply.service.SaleOrderService;
import com.applet.apply.service.UserCouponService;
import com.applet.common.bo.PageBo;
import com.applet.common.bo.SaleOrderBo;
import com.applet.common.enums.OrderEnums;
import com.applet.common.util.EnumUtil;
import com.applet.common.util.ObjectUtils;
import com.applet.common.util.Page;
import com.applet.common.vo.SaleOrderDtlVo;
import com.applet.common.vo.SaleOrderVo;
import com.google.common.util.concurrent.AtomicDouble;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    private final UserCouponService userCouponService;

    private final SaleOrderDocMapper saleOrderDocMapper;
    private final SaleOrderDtlMapper saleOrderDtlMapper;
    private final SaleOrderTimelineMapper saleOrderTimelineMapper;
    private final ViewUserCartMapper viewUserCartMapper;

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

    @Override
    public boolean create(SaleOrderBo bo) {

        // TODO

        ViewUserCoupon coupon = userCouponService.selectUserCouponInfo(bo.getAddressId(), bo.getUserId());
        SaleOrderDoc order = new SaleOrderDoc();
/*        SaleOrderDoc order = new SaleOrderDoc(LocalDate.now().format(DateTimeFormatter.ofPattern("YYYYMMDD")),
                bo.getUserId(),

                );*/
        List<SaleOrderDtl> dtls = new ArrayList<>();

        List<ViewUserCart> carts = viewUserCartMapper.findByIds(bo.getCartIdList());
        AtomicDouble totalAmount = new AtomicDouble();
        carts.forEach(it -> {
            double discountPrice = it.getIfDiscount() ? it.getSellPrice() * it.getDiscount() / 100 : it.getSellPrice();
            totalAmount.addAndGet(discountPrice);
            dtls.add(new SaleOrderDtl(null,
                    order.getOrderId(),
                    it.getGoodsId(),
                    it.getGoodsName(),
                    it.getSpecsId(),
                    it.getSpecsText(),
                    it.getSpecsSrc(),
                    it.getAmount(),
                    BigDecimal.valueOf(discountPrice).setScale(2, BigDecimal.ROUND_HALF_UP),
                    BigDecimal.valueOf(it.getSellPrice()).setScale(2, BigDecimal.ROUND_HALF_UP)
            ));
        });

//        LocalDate.now().format(DateTimeFormatter.ofPattern("YYYYMMDD"));


        saleOrderDocMapper.insertSelective(order);




/*        bo.getDtls().forEach(it -> {
            SaleOrderDtl dtl = new SaleOrderDtl();
            BeanUtils.copyProperties(it, dtl);
            dtls.add(dtl);
        });*/
        saleOrderDtlMapper.batchInsert(dtls);
        return false;
    }


}
