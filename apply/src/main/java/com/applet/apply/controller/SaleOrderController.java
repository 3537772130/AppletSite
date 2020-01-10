package com.applet.apply.controller;

import com.applet.apply.service.SaleOrderService;
import com.applet.common.annotation.Resubmit;
import com.applet.common.bo.PageBo;
import com.applet.common.bo.SaleOrderBo;
import com.applet.common.util.Page;
import com.applet.common.vo.RestVo;
import com.applet.common.vo.SaleOrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 销售订单
 *
 * @author 谭良忠
 * @date 2019/12/31 13:51
 */
@Api(tags = "销售订单")
@RestController
@RequestMapping("/api/applet/sale/order")
@RequiredArgsConstructor
public class SaleOrderController extends BaseController {

    private final SaleOrderService service;

    @PostMapping("page")
    @ApiOperation("分页")
    public RestVo<Page<SaleOrderVo>> findPage(@ApiParam("分页对象") @RequestBody PageBo<SaleOrderBo> bo) {
        SaleOrderBo params = Optional.ofNullable(bo.getParam()).orElse(new SaleOrderBo());
        params.setUserId(this.getUserId());
        params.setAppletId(this.getAppletId());
        bo.setParam(params);
        return RestVo.SUCCESS(service.findPage(bo));
    }

    @PutMapping("update/status")
    @ApiOperation("更新订单状态")
    public RestVo<Boolean> updateOrderStatus(@ApiParam("订单对象") @RequestBody SaleOrderBo bo) {
        return RestVo.SUCCESS(service.updateOrderStatus(bo));
    }

    @GetMapping("detail/{orderId}")
    @ApiOperation("订单详情")
    public RestVo<SaleOrderVo> detail(@PathVariable Integer orderId) {
        return RestVo.SUCCESS(service.detail(orderId));
    }

    @Resubmit
    @PostMapping("create")
    @ApiOperation("用户下单")
    public RestVo<Integer> detail(@ApiParam("订单对象") @RequestBody SaleOrderBo bo) {
        bo.setUserId(this.getUserId());
        bo.setAppletId(this.getAppletId());
        return RestVo.SUCCESS(service.create(bo));
    }

}
