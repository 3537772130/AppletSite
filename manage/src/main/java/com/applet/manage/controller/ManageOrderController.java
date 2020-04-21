package com.applet.manage.controller;

import com.applet.common.util.AjaxResponse;
import com.applet.common.util.Page;
import com.applet.common.util.PageUtil;
import com.applet.manage.service.ManageOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2020/4/20
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@RestController
@RequestMapping(value = "/api/manage/order/")
public class ManageOrderController {
    @Autowired
    private ManageOrderService manageOrderService;

    /**
     * 分页查询订单列表
     * @param orderNo
     * @param courierNo
     * @param appletName
     * @param userMobile
     * @param startTime
     * @param endTime
     * @param payRelationId
     * @param orderStatus
     * @param request
     * @return
     */
    @RequestMapping(value = "queryOrderInfoByPage")
    public Object queryOrderInfoByPage(String orderNo, String courierNo, String appletName,
                                       String userMobile, String startTime, String endTime,
                                       String payRelationId, Integer orderStatus, HttpServletRequest request){
        Page page = PageUtil.initPage(request);
        page = manageOrderService.selectOrderInfoByPage(orderNo, courierNo, appletName, userMobile, startTime, endTime, payRelationId, orderStatus, page);
        return AjaxResponse.success(page);
    }


}
