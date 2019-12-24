package com.applet.manage.config.intercepors;

import com.applet.manage.config.annotation.CancelAuthentication;
import com.applet.manage.entity.ManagerInfo;
import com.applet.common.util.Constants;
import com.applet.common.util.NullUtil;
import com.applet.common.util.SerializeUtil;
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
import java.util.Map;

/**
 * @program: demo
 * @description: web管理员登陆信息拦截器
 * @author: zhouhuahu
 * @create: 2019-08-17 16:22
 **/
@Component
public class ManagerInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(ManagerInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handleMethod = (HandlerMethod) handler;
            CancelAuthentication cancel = handleMethod.getMethodAnnotation(CancelAuthentication.class);
            if (cancel != null) {
                return true;
            }
        }
        HttpSession session = request.getSession();
        ManagerInfo managerInfo = (ManagerInfo) SerializeUtil.unserialize((byte[]) session.getAttribute(Constants.WEB_MANAGER_INFO));
        if (managerInfo == null) {
            log.info("===> 登录过期");
            request.getRequestDispatcher("/api/loginOverdue").forward(request, response);
            return false;
        } else {
//                if (menuService == null) {//解决service为null无法注入问题
//                    System.out.println("menuService is null!!!");
//                    BeanFactory factory = WebApplicationContextUtils
//                            .getRequiredWebApplicationContext(request.getServletContext());
//                    menuService = (MenuService) factory.getBean("menuService");
//                }
            String uri = request.getRequestURI();
            int lastIndex = uri.lastIndexOf("/");
            String logo = uri.substring((lastIndex + 1), uri.length());
            String result = Constants.MANAGER_ROLE_AUTH_LOGO_MAP.get(logo);
            boolean bool = true;
            if (NullUtil.isNotNullOrEmpty(result)) {
                bool = false;
                Map<String, String> map = Constants.MANAGER_ROLE_AUTH_MAP.get(managerInfo.getRoleId());
                result = map.get(logo);
                if (NullUtil.isNotNullOrEmpty(result)) {
                    bool = true;
                }
            }
            if (!bool) {
                request.getRequestDispatcher("/api/auth").forward(request, response);
            }
            return bool;
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
