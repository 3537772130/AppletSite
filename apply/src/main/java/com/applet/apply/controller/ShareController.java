package com.applet.apply.controller;

import com.applet.apply.entity.RegionInfo;
import com.applet.apply.service.RegionService;
import com.applet.common.util.AjaxResponse;
import com.applet.common.util.Constants;
import com.applet.common.util.NullUtil;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: AppletSite
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
     * 未找到小程序拦截，返回错误码
     * @return
     */
    @RequestMapping(value = "appletNull")
    public Object appletNull(){
        return AjaxResponse.error("未找到相关小程序信息");
    }

    /**
     * 小程序未开通拦截，返回错误码
     * @return
     */
    @RequestMapping(value = "appletNotOpen")
    public Object appletNotOpen(){
        return AjaxResponse.error("小程序尚未开通");
    }

    /**
     * 小程序未发布拦截，返回错误码
     * @return
     */
    @RequestMapping(value = "appletProhibit")
    public Object appletProhibit(){
        return AjaxResponse.error("小程序已被禁用");
    }

    /**
     * 小程序已禁用拦截，返回错误码
     * @return
     */
    @RequestMapping(value = "appletNotRelease")
    public Object appletNotRelease(){
        return AjaxResponse.msg("0", "小程序正在整顿中，敬请期待哟");
    }

    /**
     * 登录过期拦截，返回错误码
     * @return
     */
    @RequestMapping(value = "loginOverdue")
    public Object loginOverdue(){
        return AjaxResponse.error("亲,登录过期啦");
    }

    /**
     * 权限拦截，返回错误码
     * @return
     */
    @RequestMapping(value = "auth")
    public Object auth() {
        return AjaxResponse.error("亲,账号未开通哦");
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
