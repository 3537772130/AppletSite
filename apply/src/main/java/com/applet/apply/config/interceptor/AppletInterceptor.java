package com.applet.apply.config.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.applet.apply.config.annotation.CancelAuth;
import com.applet.common.entity.*;
import com.applet.apply.service.AppletService;
import com.applet.apply.service.RedisService;
import com.applet.apply.service.WeChantService;
import com.applet.common.util.Constants;
import com.applet.common.util.NullUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Created by zhouhuahu on 2018/6/27.
 */
@Slf4j
@Component
public class AppletInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private WeChantService weChantService;
    @Autowired
    private AppletService appletService;
    @Autowired
    private RedisService redisService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String ipAddress = request.getHeader("ipAddress");
            if (NullUtil.isNotNullOrEmpty(ipAddress)){
                request.getSession().setAttribute(Constants.CLIENT_PUBLIC_IP, ipAddress);
            }
            log.info("访问控制拦截\nURI: {} \n Params: {}", request.getRequestURI(), JSONObject.toJSONString(request.getParameterMap()));
            String appletCode = request.getHeader("appletCode");
            if (NullUtil.isNullOrEmpty(appletCode)) {
                request.getRequestDispatcher("/api/illegal").forward(request, response);
                return false;
            }
            //检查小程序信息
            ViewAppletInfo appletInfo = (ViewAppletInfo) Optional.ofNullable(redisService.getValue(appletCode)).orElse(new ViewAppletInfo());
            if (NullUtil.isNullOrEmpty(appletInfo.getId())) {
                log.error("未获取到redis缓存小程序信息，重新查询...");
                appletInfo = appletService.selectAppletInfo(appletCode);
                if (null == appletInfo) {
                    request.getRequestDispatcher("/api/appletNull").forward(request, response);
                    return false;
                }
                try {
                    redisService.setValue(appletCode, appletInfo);
                } catch (Exception e) {
                    log.error("设置redis出错(小程序信息){}", e);
                }
            }
            if (appletInfo.getStatus().intValue() == 0) {
                request.getRequestDispatcher("/api/appletNotOpen").forward(request, response);
                return false;
            }
            if (appletInfo.getStatus().intValue() == -1) {
                request.getRequestDispatcher("/api/appletProhibit").forward(request, response);
                return false;
            }
            if (!appletInfo.getIfSelling()) {
                request.getRequestDispatcher("/api/appletNotRelease").forward(request, response);
                return false;
            }
            request.getSession().setAttribute("appletInfo", appletInfo);

            if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
                return true;
            }

            // 检查登录用户信息
            // 取消用户登录认证
            HandlerMethod handleMethod = (HandlerMethod) handler;
            CancelAuth ca = handleMethod.getMethodAnnotation(CancelAuth.class);
            String loginCode = request.getParameter("loginCode");
            String wxCode = request.getHeader("wxCode");
            if (NullUtil.isNotNullOrEmpty(loginCode) && ca != null) {
                log.info("没有登陆记录，微信重新授权登陆小程序，loginCode: " + loginCode);
                return true;
            } else if (NullUtil.isNullOrEmpty(wxCode) && ca != null) {
                log.info("加载小程序信息,appletCode: " + appletCode);
                return true;
            } else if (NullUtil.isNullOrEmpty(wxCode) && ca == null) {
                log.info("小程序登陆过期......");
                request.getRequestDispatcher("/api/loginOverdue").forward(request, response);
                return false;
            }
            log.info("已有登陆记录，微信登陆信息使用redis加载,wxCode: " + wxCode);
            ViewWeChantInfo weChantInfo = (ViewWeChantInfo) Optional.ofNullable(redisService.getValue(wxCode)).orElse(new ViewWeChantInfo());
            if (NullUtil.isNullOrEmpty(weChantInfo.getId())) {
                log.info("未能从redis中获取到用户信息，重新查询...");
                weChantInfo = weChantService.selectViewWeChantInfo(appletInfo.getId(), wxCode);
                if (null == weChantInfo) {
                    request.getRequestDispatcher("/api/auth").forward(request, response);
                    return false;
                }
                if (weChantInfo.getStatus().intValue() != 1) {
                    request.getRequestDispatcher("/api/auth").forward(request, response);
                    return false;
                }
                try {
                    redisService.setValue(wxCode, weChantInfo);
                } catch (Exception e) {
                    log.error("设置redis出错(用户信息){}", e);
                }
            }
            request.getSession().setAttribute("weChantInfo", weChantInfo);
            return true;
        } catch (Exception e) {
            log.error("访问出错{}", e);
            request.getRequestDispatcher("/api/error").forward(request, response);
        }
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("清除访问者信息...");
        request.getSession().removeAttribute("appletInfo");
        request.getSession().removeAttribute("weChantInfo");
        request.getSession().removeAttribute(Constants.CLIENT_PUBLIC_IP);
    }
}
