package com.applet.apply.controller;

import com.applet.apply.config.annotation.CancelAuth;
import com.applet.apply.config.annotation.SessionScope;
import com.applet.apply.entity.AppletInfo;
import com.applet.apply.entity.ViewAppletInfo;
import com.applet.apply.entity.ViewWeChantInfo;
import com.applet.apply.service.AppletService;
import com.applet.apply.service.RedisService;
import com.applet.common.util.NullUtil;
import com.applet.common.util.AjaxResponse;
import com.applet.common.util.TencentLocationUtils;
import com.applet.common.util.WeChatAppletUtil;
import com.applet.common.util.qiniu.QiNiuConfig;
import com.applet.common.util.qiniu.QiNiuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouhuahu
 * @date 2018/9/13
 */
@RestController
@RequestMapping(value = "/api/applet/")
public class AppletController {
    private static final Logger log = LoggerFactory.getLogger(AppletController.class);
    @Autowired
    private AppletService appletService;
    @Autowired
    private RedisService redisService;

    /**
     * 获取小程序基本信息
     * @param appletInfo
     * @return
     */
    @RequestMapping(value = "/getAppletInfo")
    @CancelAuth
    public Object getAppletInfo(@SessionScope("appletInfo") ViewAppletInfo appletInfo){
        Map map = new HashMap();
        map.put("id", appletInfo.getId());
        map.put("appletName", NullUtil.isNotNullOrEmpty(appletInfo.getAppletSimple()) ? appletInfo.getAppletSimple() : appletInfo.getAppletName());
        map.put("appletLogo", appletInfo.getAppletLogo());
        map.put("telephone", appletInfo.getTelephone());
        map.put("province", appletInfo.getProvince());
        map.put("city", appletInfo.getCity());
        map.put("county", appletInfo.getCounty());
        map.put("addressSimple", appletInfo.getAddressSimple());
        map.put("addressDetails", appletInfo.getAddressDetails());
        map.put("lon", appletInfo.getLon());
        map.put("lat", appletInfo.getLat());
        map.put("systemColor", appletInfo.getSystemColor());
        return AjaxResponse.success(map);
    }


    /**
     * 获取小程序地址信息
     * @param appletInfo
     * @return
     */
    @RequestMapping(value = "/getAppletAddress")
    @CancelAuth
    public Object getAppletAddress(@SessionScope("appletInfo") ViewAppletInfo appletInfo){
        AppletInfo applet = appletService.selectAppletInfo(appletInfo.getId());
        if (NullUtil.isNotNullOrEmpty(applet.getProvince())){
            Map map = new HashMap();
            map.put("province", applet.getProvince());
            map.put("city", applet.getCity());
            map.put("county", applet.getCounty());
            map.put("lon", applet.getLon());
            map.put("lat", applet.getLat());
            map.put("title", applet.getAddressSimple());
            map.put("address", applet.getAddressDetails());
            return AjaxResponse.success(map);
        }
        return AjaxResponse.error("未设置地址信息");
    }

    /**
     * 设置小程序微信信息
     * @param appletInfo
     * @param weChantInfo
     * @param address
     * @param title
     * @param lat
     * @param lon
     * @return
     */
    @RequestMapping(value = "/setAppletAddress")
    public Object setAppletAddress(@SessionScope("appletInfo") ViewAppletInfo appletInfo, @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                   @RequestParam String address, @RequestParam String title, @RequestParam String lat, @RequestParam String lon){
        try {
            if (!NullUtil.isNotNullOrEmpty(weChantInfo.getUserId())){
                return AjaxResponse.error("未绑定账号");
            }
            if (appletInfo.getUserId().intValue() != weChantInfo.getUserId()){
                return AjaxResponse.error("您没有权限设置");
            }
            Map map = TencentLocationUtils.getLocation(lon, lat);
            map.put("address", address);
            map.put("title", title);
            map.put("lon", lon);
            map.put("lat", lat);
            appletService.updateAppletAddress(appletInfo.getId(), map);
            // 更新redis信息
            ViewAppletInfo appletInfo1 = appletService.selectAppletInfo(appletInfo.getAppletCode());
            redisService.setValue(appletInfo1.getAppletCode(), appletInfo1);
            return AjaxResponse.success("设置成功");
        } catch (Exception e) {
            log.error("商家设置小程序位置信息出错{}", e);
        }
        return AjaxResponse.error("设置失败");
    }

    /**
     * 获取小程序二维码
     * @param appletInfo
     * @param weChantInfo
     * @return
     */
    @RequestMapping(value = "/getAppletQrCode")
    public Object getAppletQrCode(@SessionScope("appletInfo") ViewAppletInfo appletInfo, @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo){
        if (NullUtil.isNotNullOrEmpty(weChantInfo.getUserId()) && weChantInfo.getUserId().intValue() == appletInfo.getUserId()){
            String path = "/upload/applet/qrCode/" + appletInfo.getAppletCode() + ".jpg";
            if (!QiNiuUtil.existsFile(QiNiuConfig.bucketAppletImage, path)){
                try {
                    Object obj = WeChatAppletUtil.getAppletQrCode(appletInfo.getAppId(), appletInfo.getAppSecret());
                    QiNiuUtil.zipAndUpload(path, obj, 0.3f, QiNiuConfig.bucketAppletImage);
                } catch (Exception e) {
                    log.error("生成小程序二维码出错{}", e);
                    return AjaxResponse.error("获取小程序二维码失败");
                }
            }
            return AjaxResponse.success(path);
        }
        return AjaxResponse.error("您没有权限");
    }

    /**
     * 设置小程序主题色彩
     * @param appletInfo
     * @param weChantInfo
     * @param color
     * @return
     */
    @RequestMapping(value = "setAppletColor")
    public Object setAppletColor(@SessionScope("appletInfo") ViewAppletInfo appletInfo, @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, String color){
        try {
            AppletInfo info = appletService.selectAppletInfo(appletInfo.getId(), weChantInfo.getUserId());
            if (null == info){
                return AjaxResponse.error("没有权限");
            }
            appletService.updateAppletColor(info.getId(), color);

            // 更新redis信息
            ViewAppletInfo appletInfo1 = appletService.selectAppletInfo(appletInfo.getAppletCode());
            redisService.setValue(appletInfo1.getAppletCode(), appletInfo1);
            return AjaxResponse.success("设置成功");
        } catch (Exception e) {
            log.error("设置小程序主题色彩出错{}", e);
            return AjaxResponse.error("设置失败");
        }
    }
}
