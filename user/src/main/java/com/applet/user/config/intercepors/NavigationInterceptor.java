package com.applet.user.config.intercepors;

import com.applet.common.util.PropertiesLoadUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2020/3/11
 * Time: 11:41
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Slf4j
@Component
public class NavigationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (uri.equals("/")) {
            String url = "http://localhost:8080/index.html";
            if (PropertiesLoadUtils.isRun()){
                url = "http://www.appletsite.com:2273/index.html";
            }
            response.sendRedirect(url);
//            log.info("默认访问路径：" + url);
            return false;
        } else {
            return true;
        }
    }
}
