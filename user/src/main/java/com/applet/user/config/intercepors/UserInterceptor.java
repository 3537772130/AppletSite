package com.applet.user.config.intercepors;

import com.applet.user.config.annotation.CancelAuthentication;
import com.applet.user.entity.UserInfo;
import com.applet.user.util.Constants;
import com.applet.user.util.SerializeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @program: SpringBootDemo
 * @description: vue用户信息拦截器
 * @author: Mr.ZhouHuaHu
 * @create: 2019-07-08 09:14
 **/
@Component
public class UserInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(UserInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handleMethod = (HandlerMethod) handler;
            CancelAuthentication cancel = handleMethod.getMethodAnnotation(CancelAuthentication.class);
            if (cancel != null) {
                return true;
            }
        }
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo) SerializeUtil.unserialize((byte[]) session.getAttribute(Constants.VUE_USER_INFO));
        if (user == null) {
            log.info("===> 登录过期");
            request.getRequestDispatcher("/api/illegal").forward(request, response);
            return false;
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
