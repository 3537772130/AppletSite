package com.applet.apply.config.interceptor;

import com.applet.apply.config.annotation.CancelAuth;
import com.applet.apply.entity.ViewAppletInfo;
import com.applet.apply.entity.WeChantInfo;
import com.applet.apply.service.AppletService;
import com.applet.apply.service.RedisService;
import com.applet.apply.service.WeChantService;
import com.applet.apply.util.NullUtil;
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
    @Autowired
    private RedisService redisService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            logger.info("初始化访问者信息...");
            String appletCode = request.getParameter("appletCode");
            if (NullUtil.isNullOrEmpty(appletCode)){
                request.getRequestDispatcher("/api/illegal").forward(request, response);
                return false;
            }
            //检查小程序信息
            ViewAppletInfo appletInfo = (ViewAppletInfo) redisService.getRedisValue(appletCode);
            if (null == appletInfo){
                appletInfo = appletService.selectAppletInfo(appletCode);
                if (null == appletInfo){
                    request.getRequestDispatcher("/api/appletNull").forward(request, response);
                    return false;
                }
                redisService.setRedisValue(appletCode, appletInfo);
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
            CancelAuth ca  = handleMethod.getMethodAnnotation(CancelAuth.class);
            if (ca != null){
                return true;
            }

            //检查登录用户信息
            String wxCode = request.getParameter("wxCode");
            if (NullUtil.isNullOrEmpty(wxCode)){
                request.getRequestDispatcher("/api/loginOverdue").forward(request, response);
                return false;
            }
            WeChantInfo weChantInfo = (WeChantInfo) redisService.getRedisValue(wxCode);
            if (null == weChantInfo){
                weChantInfo = weChantService.selectWeChantInfo(appletInfo.getId(), wxCode);
                if (null == weChantInfo){
                    request.getRequestDispatcher("/api/auth").forward(request, response);
                    return false;
                }
                if (!weChantInfo.getStatus()){
                    request.getRequestDispatcher("/api/auth").forward(request, response);
                    return false;
                }
                redisService.setRedisValue(wxCode, weChantInfo);
            }
            request.getSession().setAttribute("weChantInfo", weChantInfo);
            return true;
        } catch (Exception e) {
            logger.error("访问出错{}", e);
            request.getRequestDispatcher("/api/error").forward(request, response);
        }
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("清除访问者信息...");
        request.getSession().removeAttribute("appletInfo");
        request.getSession().removeAttribute("weChantInfo");
    }
}
