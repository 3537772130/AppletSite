package com.applet.manage.controller;

import com.applet.common.bo.PageBo;
import com.applet.common.bo.SaleOrderBo;
import com.applet.common.vo.PageVo;
import com.applet.common.vo.RestVo;
import com.applet.common.vo.SaleOrderVo;
import com.applet.manage.service.SaleOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 销售订单
 *
 * @author 谭良忠
 * @date 2019/12/31 13:51
 */
@Api(tags = "销售订单")
@RestController
@RequestMapping("api/sale/order")
@RequiredArgsConstructor
public class SaleOrderController {

    private final SaleOrderService service;

    @PostMapping("page")
    @ApiOperation("分页")
    public RestVo<PageVo<SaleOrderVo>> findPage(@ApiParam("分页对象") @RequestBody PageBo<SaleOrderBo> bo) {
        return RestVo.SUCCESS(service.findPage(bo));
    }

    @PutMapping("update/status")
    @ApiOperation("更新订单状态")
    public RestVo<Boolean> updateOrderStatus(@RequestParam Integer orderId, @RequestParam Byte status) {
        return RestVo.SUCCESS(service.updateOrderStatus(orderId, status));
    }

    @PutMapping("detail/{orderId}")
    @ApiOperation("订单详情")
    public RestVo<SaleOrderVo> detail(@PathVariable Integer orderId) {
        return RestVo.SUCCESS(service.detail(orderId));
    }
}
