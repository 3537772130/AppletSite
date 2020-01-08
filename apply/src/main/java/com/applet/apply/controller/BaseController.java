package com.applet.apply.controller;

import com.applet.apply.entity.ViewAppletInfo;
import com.applet.apply.entity.ViewWeChantInfo;
import com.applet.apply.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liangzhong
 * @date 2020/1/5 14:50
 */
public class BaseController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private HttpServletRequest request;

    public Integer getUserId() {
        return ((ViewWeChantInfo) redisService.getValue(request.getHeader("wxCode"))).getUserId();
    }

    public Integer getAppletId() {
        return ((ViewAppletInfo) redisService.getValue(request.getHeader("appletCode"))).getId();
    }
}
