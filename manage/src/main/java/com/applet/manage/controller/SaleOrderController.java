package com.applet.manage.controller;

import com.applet.common.bo.PageBo;
import com.applet.common.bo.SaleOrderBo;
import com.applet.common.vo.PageVo;
import com.applet.common.vo.RestVo;
import com.applet.common.vo.SaleOrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 销售订单
 *
 * @author 谭良忠
 * @date 2019/12/31 13:51
 */
@Api(tags = "销售订单")
@RestController
@RequestMapping("api/sale/order")
public class SaleOrderController {

    @PostMapping("page")
    @ApiOperation("分页")
    public RestVo<PageVo<SaleOrderVo>> findPage(@ApiParam("分页对象") @RequestBody PageBo<SaleOrderBo> bo) {
        return null;
    }

}
