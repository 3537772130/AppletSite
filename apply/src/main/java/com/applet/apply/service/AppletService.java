package com.applet.apply.service;

import com.applet.apply.entity.*;
import com.applet.apply.mapper.AppletInfoMapper;
import com.applet.apply.mapper.ViewAppletInfoMapper;
import com.applet.apply.util.NullUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zhouhuahu on 2018/6/27.
 */
@Service
public class AppletService {
    private static final Logger log = LoggerFactory.getLogger(AppletService.class);
    @Autowired
    private AppletInfoMapper appletInfoMapper;
    @Autowired
    private ViewAppletInfoMapper viewAppletInfoMapper;

    /**
     * 查询小程序信息
     * @param id
     * @return
     */
    public AppletInfo selectAppletInfo(Integer id) {
        return appletInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询小程序信息
     * @param id
     * @param userId
     * @return
     */
    public AppletInfo selectAppletInfo(Integer id, Integer userId) {
        AppletInfoExample example = new AppletInfoExample();
        example.createCriteria().andIdEqualTo(id).andUserIdEqualTo(userId);
        List<AppletInfo> list = appletInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询登录小程序信息
     * @param appletCode
     * @return
     */
    public ViewAppletInfo selectAppletInfo(String appletCode){
        ViewAppletInfoExample example = new ViewAppletInfoExample();
        example.createCriteria().andAppletCodeEqualTo(appletCode);
       List<ViewAppletInfo> list = viewAppletInfoMapper.selectByExample(example);
       if (NullUtil.isNotNullOrEmpty(list)){
           return list.get(0);
       }
       return null;
    }

    /**
     * 更新小程序位置信息
     * @param appletId
     * @param map
     */
    public void updateAppletAddress(Integer appletId, Map map){
        AppletInfo applet = selectAppletInfo(appletId);
        applet.setProvince(map.get("province").toString());
        applet.setCity(map.get("city").toString());
        applet.setCounty(map.get("district").toString());
        applet.setAddressDetails(map.get("address").toString());
        applet.setAddressSimple(map.get("title").toString());
        applet.setLon(Double.parseDouble(map.get("lon").toString()));
        applet.setLat(Double.parseDouble(map.get("lat").toString()));
        appletInfoMapper.updateByPrimaryKeySelective(applet);
    }

    /**
     * 更新小程序主题颜色
     * @param id
     * @param color
     */
    public void updateAppletColor(Integer id, String color){
        AppletInfo info = new AppletInfo();
        info.setId(id);
        info.setSystemColor(color);
        appletInfoMapper.updateByPrimaryKeySelective(info);
    }
}
