package com.applet.apply.config.interceptor;

import com.applet.common.util.PropertiesLoadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2020/5/6
 * Time: 16:01
 * To change this template use File | Settings | File Templates.
 * Description: 设置默认页面
 */
@Slf4j
@Component
public class NavigationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (uri.equals("/")) {
            String url = "http://localhost:8082/index.html";
            if (PropertiesLoadUtils.isRun()){
                url = "http://www.appletsite.com/index.html";
            }
            response.sendRedirect(url);
            log.info("默认访问路径：" + url);
            return false;
        } else {
            return true;
        }
    }
}
