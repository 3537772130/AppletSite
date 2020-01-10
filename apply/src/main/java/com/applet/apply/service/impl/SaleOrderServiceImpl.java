package com.applet.apply.service.impl;

import com.applet.apply.entity.*;
import com.applet.apply.mapper.*;
import com.applet.apply.service.RedisService;
import com.applet.apply.service.SaleOrderService;
import com.applet.apply.service.UserCouponService;
import com.applet.apply.service.UserService;
import com.applet.common.bo.PageBo;
import com.applet.common.bo.SaleOrderBo;
import com.applet.common.constant.RedisKey;
import com.applet.common.enums.OrderEnums;
import com.applet.common.excepion.BusinessException;
import com.applet.common.util.DateUtils;
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
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

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
    private final RedisService redisService;

    private final SaleOrderDocMapper saleOrderDocMapper;
    private final SaleOrderDtlMapper saleOrderDtlMapper;
    private final SaleOrderTimelineMapper saleOrderTimelineMapper;
    private final ViewUserCartMapper viewUserCartMapper;
    private final UserCouponMapper userCouponMapper;

    @Override
    public Page<SaleOrderVo> findPage(PageBo<SaleOrderBo> bo) {
        Map<String, Object> params = ObjectUtils.objToMap(bo.getParam());
        long total = Optional.ofNullable(saleOrderDocMapper.selectSearchCount(params)).orElse(0L);
        Page<SaleOrderVo> pageVo = new Page<>(bo.getPage(), bo.getSize(), total);
        if (bo.getOffset() >= total) {
            pageVo.setDataSource(new ArrayList<>(0));
            return pageVo;
        }
        List<SaleOrderDoc> entitys = saleOrderDocMapper.selectSearchData(params, bo.getOffset(), bo.getSize());
        List<SaleOrderVo> vos = new ArrayList<>();
        entitys.forEach(it -> {
            SaleOrderVo vo = new SaleOrderVo();
            BeanUtils.copyProperties(it, vo);
            String[] addr = it.getDetailAddr().split(" ");
            vo.setRegion(addr[0]);
            vo.setAddress(addr[1]);
            vos.add(vo);
        });
        pageVo.setDataSource(vos);
        return pageVo;
    }

    @Override
    public boolean updateOrderStatus(SaleOrderBo bo) {
        Integer orderId = bo.getOrderId();
        Byte status = bo.getOrderStatus();
        log.info("更新订单状态, orderId: {} , status: {}", orderId, status);
        OrderEnums.OrderStatus orderStatus = EnumUtil.getEnumByCode(status, OrderEnums.OrderStatus.class);
        if (orderStatus == null) {
            log.warn("参数错误, orderId: {} , status: {}", orderId, status);
            throw BusinessException.of("参数错误");
        }
        SaleOrderDoc orderDoc = saleOrderDocMapper.selectByPrimaryKey(orderId);
        if (orderDoc == null) {
            log.warn("订单不存在, orderId: {} , status: {}", orderId, status);
            throw BusinessException.of("订单不存在");
        }
        SaleOrderDoc order = new SaleOrderDoc();
        order.setGmtModified(new Date());
        order.setOrderId(orderId);
        order.setOrderStatus(status);
        order.setOrderStatusCn(orderStatus.getName());
        if (OrderEnums.OrderStatus.CANCEL.getCode().equals(status)) {
            order.setCancelReason(bo.getCancelReason());
        }
        if (OrderEnums.OrderStatus.DENIAL.getCode().equals(status)) {
            order.setDenialReason(bo.getDenialReason());
        }

        saleOrderDocMapper.updateByPrimaryKeySelective(order);
        saleOrderTimelineMapper.insertSelective(new SaleOrderTimeline(orderId, status, orderStatus.getName()));

        // 用户签收订单结束, 更新优惠券状态
        if (OrderEnums.OrderStatus.RECEIVED.getCode().equals(status)) {
            userCouponMapper.updateByPrimaryKeySelective(new UserCoupon(orderDoc.getUserCouponId(), OrderEnums.UserCouponStatus.USED.getCode()));
        }
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
        String[] addr = orderDoc.getDetailAddr().split(" ");
        docVo.setRegion(addr[0]);
        docVo.setAddress(addr[1]);
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
    public Integer create(SaleOrderBo bo) {
        log.info("用户下单, Params: {}", bo);

        if (CollectionUtils.isEmpty(bo.getCartIdList()) || bo.getAddressId() == null) {
            log.warn("缺少必要参数, SaleOrderBo: {}", bo);
            throw BusinessException.of("缺少必要参数");
        }

        // 处理订单详情
        List<SaleOrderDtl> dtls = new ArrayList<>();
        List<ViewUserCart> carts = viewUserCartMapper.findByIds(bo.getCartIdList());
        AtomicDouble totalAmount = new AtomicDouble();
        AtomicBoolean couponFlag = new AtomicBoolean(true);
        carts.forEach(it -> {
            if (!it.getIfDiscount()) {
                couponFlag.set(false);
            }
            double discountPrice = it.getSellPrice() * it.getDiscount() / 100;
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

        ViewUserCoupon userCoupon = null;
        if (couponFlag.get()) {
            userCoupon = userCouponService.selectUserCouponInfo(bo.getCouponId(), bo.getUserId());
            Date now = new Date();
            if (userCoupon == null) {
                log.warn("无此优惠卷, CouponId: {}, UserId: {}", bo.getCouponId(), bo.getUserId());
                throw BusinessException.of("无此优惠卷");
            }
            if (userCoupon.getActivityOver().getTime() < now.getTime()) {
                log.warn("优惠卷过期, UserCouponId: {}", userCoupon.getId());
                throw BusinessException.of("优惠卷过期");
            }
        }

        // 运费
        Double carriersFee = userCouponService.countFreight(bo.getAppletId(), bo.getDistance());
        if (carriersFee < 0) {
            log.warn("超出配送范围, AppletId: {}, Distance:{}", bo.getAppletId(), bo.getDistance());
            throw BusinessException.of("超出配送范围");
        }

        // save 订单
        OrderEnums.OrderStatus orderStatus = OrderEnums.OrderStatus.PENDING;
        SaleOrderDoc order = new SaleOrderDoc(
                DateUtils.now("YYYYMMDD") + String.format("%04d", bo.getAppletId()) + String.format("%04d", redisService.incrBy(RedisKey.ORDER_ON_KEY)),
                bo.getUserId(),
                address.getName(),
                address.getMobile(),
                String.format("%s %s", address.getRegion(), address.getAddress()),
                Double.valueOf(address.getLat()),
                Double.valueOf(address.getLon()),
                BigDecimal.valueOf(carriersFee),
                bo.getAppletId(),
                orderStatus.getCode(),
                orderStatus.getName(),
                BigDecimal.valueOf(totalAmount.addAndGet(carriersFee)),
                BigDecimal.valueOf(0.00),
                null,
                (byte) 1,
                bo.getOrderRemark()
        );
        if (userCoupon != null) {
            order.setTicketAmount(BigDecimal.valueOf(userCoupon.getDenomination()));
            order.setUserCouponId(userCoupon.getCouponId());
        }
        saleOrderDocMapper.insertSelective(order);

        // save 订单详情
        dtls.forEach(it -> it.setOrderId(order.getOrderId()));
        saleOrderDtlMapper.batchInsert(dtls);

        if (userCoupon != null) {
            // 更新优惠券状态
            userCouponMapper.updateByPrimaryKeySelective(new UserCoupon(userCoupon.getId(), OrderEnums.UserCouponStatus.USING.getCode()));
        }
        return order.getOrderId();
    }


}


