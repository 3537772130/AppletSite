package com.applet.user.controller;

import com.applet.common.entity.RegionInfo;
import com.applet.user.service.RegionService;
import com.applet.common.util.AjaxResponse;
import com.applet.common.util.Constants;
import com.applet.common.util.NullUtil;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: demo
 * @description: 共享控制层
 * @author: zhouhuahu
 * @create: 2019-08-17 16:59
 **/
@RestController
@RequestMapping(value = "/api/")
public class ShareController {
    @Autowired
    private RegionService regionService;

    /**
     * 错误拦截，返回错误码
     * @return
     */
    @RequestMapping(value = "error")
    public Object error() {
        return AjaxResponse.error("请求出错");
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "illegal")
    public Object illegal(){
        return AjaxResponse.error("非法请求");
    }

    /**
     * 权限拦截，返回错误码
     *
     * @return
     */
    @RequestMapping(value = "auth")
    public Object auth() {
        return AjaxResponse.msg("-2", "没有权限！");
    }

    /**
     * 登录过期拦截，返回错误码
     * @return
     */
    @RequestMapping(value = "loginOverdue")
    public Object loginOverdue(){
        return AjaxResponse.msg("0","亲,登录过期啦");
    }

    /**
     * 查询地域信息集合
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "selectRegionList")
    public Object selectRegionList(String id) {
        List<RegionInfo> list = null;
        if (NullUtil.isNotNullOrEmpty(id)) {
            list = regionService.selectRegionList(Integer.parseInt(id), null);
        } else {
            list = regionService.selectProvinceList();
        }
        return AjaxResponse.success(list);
    }

    /**
     * 查询地域信息JSON
     *
     * @return
     */
    @RequestMapping(value = "selectRegionJson")
    public Object selectRegionJson() {
        return AjaxResponse.success(new JSONArray(Constants.REGION_MAP_TO_NAME).toString());
    }
}
