package com.applet.apply.config.error;

import com.applet.common.util.AjaxResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: SpringBootDemo
 * @description: 系统异常捕获
 * @author: Mr.ZhouHuaHu
 * @create: 2019-09-04 14:14
 **/
//@ControllerAdvice(annotations = {RestController.class})
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        try {
            String uri = request.getRequestURI();
            logger.error("请求地址：{}", uri);
            logger.error("访问出错：{}", e);
            return AjaxResponse.error("请求出错: " + e.getMessage());
        } catch (Exception ex) {
            logger.error("获取请求错误码出错{}", e);
            return AjaxResponse.error("请求出错!");
        }
    }
}
