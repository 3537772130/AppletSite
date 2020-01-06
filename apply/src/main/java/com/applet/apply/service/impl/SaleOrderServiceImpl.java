package com.applet.apply.service.impl;

import com.applet.apply.entity.*;
import com.applet.apply.mapper.SaleOrderDocMapper;
import com.applet.apply.mapper.SaleOrderDtlMapper;
import com.applet.apply.mapper.SaleOrderTimelineMapper;
import com.applet.apply.mapper.ViewUserCartMapper;
import com.applet.apply.service.SaleOrderService;
import com.applet.apply.service.UserCouponService;
import com.applet.apply.service.UserService;
import com.applet.common.bo.PageBo;
import com.applet.common.bo.SaleOrderBo;
import com.applet.common.enums.OrderEnums;
import com.applet.common.excepion.BusinessException;
import com.applet.common.util.EnumUtil;
import com.applet.common.util.ObjectUtils;
import com.applet.common.util.Page;
import com.applet.common.vo.SaleOrderDtlVo;
import com.applet.common.vo.SaleOrderVo;
import com.google.common.util.concurrent.AtomicDouble;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
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
@Slf4j
@Service
@RequiredArgsConstructor
public class SaleOrderServiceImpl implements SaleOrderService {

    private final UserCouponService userCouponService;
    private final UserService userService;

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
        log.info("用户下单, Params: {}", bo);

        if (CollectionUtils.isEmpty(bo.getCartIdList()) || bo.getAddressId() == null) {
            log.warn("缺少必要参数, SaleOrderBo: {}", bo);
            throw BusinessException.of("缺少必要参数");
        }

        // 处理订单详情
        List<SaleOrderDtl> dtls = new ArrayList<>();
        List<ViewUserCart> carts = viewUserCartMapper.findByIds(bo.getCartIdList());
        AtomicDouble totalAmount = new AtomicDouble();
        carts.forEach(it -> {
            double discountPrice = it.getIfDiscount() ? it.getSellPrice() * it.getDiscount() / 100 : it.getSellPrice();
            totalAmount.addAndGet(discountPrice);
            dtls.add(new SaleOrderDtl(null,
                    null,
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


        ReceiveAddress address = userService.selectReceiveAddressInfo(bo.getAddressId(), bo.getUserId());
        if (address == null) {
            log.warn("地址输入错误");
            throw BusinessException.of("地址输入错误");
        }

        ViewUserCoupon userCoupon = userCouponService.selectUserCouponInfo(bo.getCouponId(), bo.getUserId());
        Date now = new Date();
        if (userCoupon.getActivityOver().getTime() < now.getTime()) {
            log.warn("优惠卷过期, UserCouponId: {}", userCoupon.getId());
            throw BusinessException.of("优惠卷过期");
        }

        // save 订单
        OrderEnums.OrderStatus orderStatus = OrderEnums.OrderStatus.PENDING;
        SaleOrderDoc order = new SaleOrderDoc(bo.getUserId(),
                address.getName(),
                address.getMobile(),
                String.format("%s %s %s %s", address.getCity(), address.getCounty(), address.getRegion(), address.getAddress()),
                Double.valueOf(address.getLat()),
                Double.valueOf(address.getLon()),
                BigDecimal.valueOf(0),
                bo.getAppletId(),
                orderStatus.getCode(),
                orderStatus.getName(),
                BigDecimal.valueOf(totalAmount.get()),
                BigDecimal.valueOf(userCoupon.getDenomination()),
                userCoupon.getCouponId(),
                (byte) 1
        );
        saleOrderDocMapper.insertSelective(order);

        // 生成订单编号
        String orderNo = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYYMMDD")) + String.format("%04d", bo.getAppletId()) + String.format("%04d", bo.getUserId()) + String.format("%04d", order.getOrderId());
        SaleOrderDoc orderDoc = new SaleOrderDoc();
        orderDoc.setOrderId(order.getOrderId());
        orderDoc.setOrderNo(orderNo);
        saleOrderDocMapper.updateByPrimaryKeySelective(order);

        // save 订单详情
        dtls.forEach(it -> it.setOrderId(order.getOrderId()));
        saleOrderDtlMapper.batchInsert(dtls);
        return false;
    }


}
