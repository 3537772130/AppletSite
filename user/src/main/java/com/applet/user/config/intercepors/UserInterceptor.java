package com.applet.user.config.intercepors;

import com.applet.common.util.NullUtil;
import com.applet.user.config.annotation.CancelAuth;
import com.applet.common.entity.UserInfo;
import com.applet.common.util.Constants;
import com.applet.common.util.SerializeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

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
public class UserInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(UserInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddress = request.getHeader("ipAddress");
        if (NullUtil.isNotNullOrEmpty(ipAddress)){
            request.getSession().setAttribute(Constants.CLIENT_PUBLIC_IP, ipAddress);
        }
        String cityCode = request.getHeader("cityCode");
        if (NullUtil.isNotNullOrEmpty(cityCode)){
            request.getSession().setAttribute(Constants.CLIENT_CITY_CODE, cityCode);
        }
        if (handler instanceof HandlerMethod) {
            HandlerMethod handleMethod = (HandlerMethod) handler;
            CancelAuth cancel = handleMethod.getMethodAnnotation(CancelAuth.class);
            if (cancel != null) {
                return true;
            }
        }
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo) SerializeUtil.unserialize((byte[]) session.getAttribute(Constants.VUE_USER_INFO));
        if (user == null) {
            log.info("===> 登录过期");
            request.getRequestDispatcher("/api/loginOverdue").forward(request, response);
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
//        request.getSession().removeAttribute(Constants.CLIENT_PUBLIC_IP);
    }
}
