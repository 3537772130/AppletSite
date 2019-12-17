package com.applet.apply.controller;

import com.applet.apply.config.annotation.SessionScope;
import com.applet.apply.entity.ShippingAddress;
import com.applet.apply.entity.WeChantInfo;
import com.applet.apply.service.ShippingAddressService;
import com.applet.apply.util.NullUtil;
import com.applet.apply.util.RegularUtil;
import com.applet.apply.util.TencentLocationUtils;
import com.applet.apply.util.AjaxResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author zhouhuahu
 * @date 2018/9/28
 */
@RestController
@RequestMapping(value = "/api/applet/user")
public class ShippingAddressController {

    private static final Logger log = LoggerFactory.getLogger(ShippingAddressController.class);
    @Autowired
    private ShippingAddressService shippingAddressService;

    /**
     * 查询收货人列表
     * @param weChantInfo
     * @return
     */
    @RequestMapping(value = "/queryShippingAddressList")
    public Object queryShippingAddressList(@SessionScope("weChantInfo") WeChantInfo weChantInfo){
        if (NullUtil.isNotNullOrEmpty(weChantInfo.getUserId())){
            List<ShippingAddress> list = shippingAddressService.selectShippingAddressList(weChantInfo.getUserId());
            return AjaxResponse.success(list);
        }
        return AjaxResponse.msg("0","未绑定账号");
    }

    /**
     * 查询收货人信息
     * @param weChantInfo
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryShippingAddressInfo")
    public Object queryShippingAddressInfo(@SessionScope("weChantInfo") WeChantInfo weChantInfo, @RequestParam Integer id){
        if (NullUtil.isNotNullOrEmpty(weChantInfo.getUserId())){
            ShippingAddress shippingAddress = shippingAddressService.selectShippingAddressInfo(id, weChantInfo.getUserId());
            if (shippingAddress == null){
                return AjaxResponse.error("未找到相关记录");
            }
            return AjaxResponse.success(shippingAddress);
        }
        return AjaxResponse.msg("0","未绑定账号");
    }

    /**
     * 保存收货人信息
     * @param weChantInfo
     * @param record
     * @return
     */
    @RequestMapping(value = "/addShippingAddress")
    public Object addShippingAddress(@SessionScope("weChantInfo") WeChantInfo weChantInfo, ShippingAddress record){
        try {
            if (!NullUtil.isNotNullOrEmpty(weChantInfo.getUserId())){
                return AjaxResponse.msg("0","未绑定账号");
            }
            if (record == null){
                return AjaxResponse.error("参数错误");
            }
            if (NullUtil.isNullOrEmpty(record.getUserName())){
                return AjaxResponse.error("收货人不能为空");
            }
            if (NullUtil.isNullOrEmpty(record.getMobile())){
                return AjaxResponse.error("联系电话不能为空");
            }
            if (!RegularUtil.checkMobile(record.getMobile())){
                return AjaxResponse.error("联系电话格式不正确");
            }
            if (NullUtil.isNullOrEmpty(record.getProvince())){
                return AjaxResponse.error("请选择省份");
            }
            if (NullUtil.isNullOrEmpty(record.getCity())){
                return AjaxResponse.error("请选择城市");
            }
            if (NullUtil.isNullOrEmpty(record.getCounty())){
                return AjaxResponse.error("请选择区县");
            }
            if (NullUtil.isNullOrEmpty(record.getAddress())){
                return AjaxResponse.error("详细地址不能为空");
            }
            record.setUserId(weChantInfo.getUserId());
            shippingAddressService.saveShippingAddress(record);
            return AjaxResponse.success("保存成功");
        } catch (Exception e) {
            log.error("保存收货人信息出错{}", e);
        }
        return AjaxResponse.error("保存失败");
    }

    /**
     * 设置默认收货地址
     * @param weChantInfo
     * @param id
     * @return
     */
    @RequestMapping(value = "/setShippingAddressByIsDefault")
    public Object setShippingAddressByIsDefault(@SessionScope("weChantInfo") WeChantInfo weChantInfo, @RequestParam Integer id){
        try {
            if (!NullUtil.isNotNullOrEmpty(weChantInfo.getUserId())){
                return AjaxResponse.msg("0","未绑定账号");
            }
            ShippingAddress record = shippingAddressService.selectShippingAddressInfo(id, weChantInfo.getUserId());
            if (record == null){
                return AjaxResponse.error("未找到相关记录");
            }
            shippingAddressService.updateShippingAddressToDefault(id, weChantInfo.getUserId());
            return AjaxResponse.success();
        } catch (Exception e) {
            log.error("设置默认收货地址出错{}", e);
        }
        return AjaxResponse.error("设置失败");
    }

    /**
     * 删除收货地址
     * @param weChantInfo
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteShippingAddress")
    public Object deleteShippingAddress(@SessionScope("weChantInfo") WeChantInfo weChantInfo, @RequestParam Integer id){
        try {
            if (!NullUtil.isNotNullOrEmpty(weChantInfo.getUserId())){
                return AjaxResponse.msg("0","未绑定账号");
            }
            ShippingAddress record = shippingAddressService.selectShippingAddressInfo(id, weChantInfo.getUserId());
            if (record == null){
                return AjaxResponse.error("未找到相关记录");
            }
            shippingAddressService.deleteShippingAddressInfo(record);
            return AjaxResponse.success();
        } catch (Exception e) {
            log.error("删除收货地址出错{}", e);
        }
        return AjaxResponse.error("操作失败");
    }

    /**
     * 获取城市信息
     * @param lon
     * @param lat
     * @return
     */
    @RequestMapping(value = "/getRegionInfo")
    public Object getRegionInfo(@RequestParam String lon, @RequestParam String lat){
        try {
            Map map = TencentLocationUtils.getLocation(lon, lat);
            return AjaxResponse.success(map);
        } catch (Exception e) {
            log.error("获取城市信息出错{}", e);
        }
        return AjaxResponse.error("获取信息失败");
    }
}
