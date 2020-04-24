package com.applet.manage.config.intercepors;

import com.applet.common.util.NullUtil;
import com.applet.common.util.RandomUtil;
import com.applet.common.util.qiniu.QiNiuUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: SpringBootDemo
 * @description: vue静态资源拦截器
 * @author: Mr.ZhouHuaHu
 * @create: 2019-08-07 15:49
 **/
@Slf4j
@Component
public class FileInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (NullUtil.isNotNullOrEmpty(uri)) {
            String url = "";
            if (uri.indexOf("/public") >= 0) {
                url = QiNiuUtil.getPublicDownURL(uri) + "?1=1";
            } else if (uri.indexOf("/image") >= 0) {
                url = QiNiuUtil.getImageDownURL(uri);
            } else if (uri.indexOf("/audio") >= 0) {
                url = QiNiuUtil.getAudioDownURL(uri);
            } else if (uri.indexOf("/video") >= 0) {
                url = QiNiuUtil.getVideoDownURL(uri);
            } else if (uri.indexOf("/zip") >= 0) {
                url = QiNiuUtil.getZipDownURL(uri);
            } else {
                uri = uri.replace("/api", "");
                request.getRequestDispatcher(uri).forward(request, response);
                return true;
            }
            response.sendRedirect(url + "&appletToken=" + RandomUtil.getRandomStr32());
//            log.info("七牛云文件路径：" + url);
        }
        return false;
    }
}
