package com.applet.apply.config.interceptor;

import com.applet.apply.config.annotation.CancelAuthentication;
import com.applet.apply.entity.ViewAppletInfo;
import com.applet.apply.entity.WeChantApplet;
import com.applet.apply.service.AppletService;
import com.applet.apply.service.WeChantService;
import com.applet.apply.util.AjaxResponse;
import com.applet.apply.util.NullUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhouhuahu on 2018/6/27.
 */
@Component
public class AppletInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(AppletInterceptor.class);
    @Autowired
    private WeChantService weChantService;
    @Autowired
    private AppletService appletService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            logger.info("初始化访问者信息...");
            String appletCode = request.getParameter("appletCode");
            if (NullUtil.isNullOrEmpty(appletCode)){
                request.getRequestDispatcher("/api/error").forward(request, response);
                return false;
            }
            //检查小程序及当前应用模板状态
            ViewAppletInfo appletInfo = appletService.selectAppletInfo(appletCode);
            if (appletInfo == null){
                request.getRequestDispatcher("/api/appletNull").forward(request, response);
                return false;
            }
            if (appletInfo.getStatus().intValue() == 0){
                request.getRequestDispatcher("/api/appletNotOpen").forward(request, response);
                return false;
            }
            if (appletInfo.getStatus().intValue() == -1){
                request.getRequestDispatcher("/api/appletProhibit").forward(request, response);
                return false;
            }
            if (!appletInfo.getIfSelling()){
                request.getRequestDispatcher("/api/appletNotRelease").forward(request, response);
                return false;
            }
            request.getSession().setAttribute("appletInfo", appletInfo);

            //取消用户登录认证
            HandlerMethod handleMethod = (HandlerMethod) handler;
            CancelAuthentication ca  = handleMethod.getMethodAnnotation(CancelAuthentication.class);
            if (ca != null){
                return true;
            }

            //检查登录用户信息
            String wxLogo = request.getParameter("wxLogo");
            if (NullUtil.isNullOrEmpty(wxLogo)){
                request.getRequestDispatcher("/api/loginOverdue").forward(request, response);
                return false;
            }
            WeChantApplet weChantApplet = weChantService.selectWeChantApplet(appletInfo.getId(), wxLogo);
            if (weChantApplet == null || !weChantApplet.getStatus()){
                request.getRequestDispatcher("/api/auth").forward(request, response);
                return false;
            }
            request.getSession().setAttribute("weChantApplet", weChantApplet);
            return true;
        } catch (Exception e) {
            logger.error("访问出错{}", e);
        }
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("清除访问者信息...");
        request.getSession().removeAttribute("appletInfo");
        request.getSession().removeAttribute("weChantApplet");
    }
}
