package com.applet.apply.config.interceptor;

import com.applet.apply.config.annotation.CancelAuth;
import com.applet.apply.entity.ViewAppletInfo;
import com.applet.apply.entity.ViewWeChantInfo;
import com.applet.apply.entity.WeChantInfo;
import com.applet.apply.service.AppletService;
import com.applet.apply.service.RedisService;
import com.applet.apply.service.WeChantService;
import com.applet.common.util.NullUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            log.info("初始化访问者信息...");
            String appletCode = request.getParameter("appletCode");
            if (NullUtil.isNullOrEmpty(appletCode)) {
                request.getRequestDispatcher("/api/illegal").forward(request, response);
                return false;
            }
            //检查小程序信息
            ViewAppletInfo appletInfo = (ViewAppletInfo) Optional.ofNullable(redisService.getValue(appletCode)).orElse(new ViewAppletInfo());
            if (NullUtil.isNullOrEmpty(appletInfo.getId())) {
                appletInfo = appletService.selectAppletInfo(appletCode);
                if (null == appletInfo) {
                    request.getRequestDispatcher("/api/appletNull").forward(request, response);
                    return false;
                }
                redisService.setValue(appletCode, appletInfo);
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

            // 检查登录用户信息
            // 取消用户登录认证
            HandlerMethod handleMethod = (HandlerMethod) handler;
            CancelAuth ca = handleMethod.getMethodAnnotation(CancelAuth.class);
            String loginCode = request.getParameter("loginCode");
            String wxCode = request.getParameter("wxCode");
            if (NullUtil.isNotNullOrEmpty(loginCode) && ca != null) {
                log.info("没有登陆记录，微信重新授权登陆小程序，loginCode: " + loginCode);
                return true;
            } else if (NullUtil.isNullOrEmpty(wxCode) && ca != null){
                log.info("加载小程序信息,appletCode: " + appletCode);
                return true;
            } else if (NullUtil.isNullOrEmpty(wxCode) && ca == null){
                log.info("小程序登陆过期......");
                request.getRequestDispatcher("/api/loginOverdue").forward(request, response);
                return false;
            }
            log.info("已有登陆记录，微信登陆信息使用redis加载,wxCode: " + wxCode);
            ViewWeChantInfo weChantInfo = new ViewWeChantInfo();
            try {
                weChantInfo = (ViewWeChantInfo) Optional.ofNullable(redisService.getValue(wxCode)).orElse(new WeChantInfo());
            } catch (Exception e) {
                log.info("未能从redis中获取到用户信息");
            }
            if (NullUtil.isNullOrEmpty(weChantInfo.getId())) {
                weChantInfo = weChantService.selectViewWeChantInfo(appletInfo.getId(), wxCode);
                if (null == weChantInfo) {
                    request.getRequestDispatcher("/api/auth").forward(request, response);
                    return false;
                }
                if (weChantInfo.getStatus().intValue() != 1) {
                    request.getRequestDispatcher("/api/auth").forward(request, response);
                    return false;
                }
                redisService.setValue(wxCode, weChantInfo);
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
    }
}
