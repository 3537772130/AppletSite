package com.applet.manage.config.argumentResolver;

import com.applet.manage.config.annotation.SessionScope;
import com.applet.manage.util.SerializeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @program: SpringBootDemo
 * @description: sessionScope函数参数分解器
 * @author: Mr.ZhouHuaHu
 * @create: 2019-07-08 09:28
 **/
public class SessionScopeMethod implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        // 让方法和参数，两种target通过
        if (methodParameter.hasParameterAnnotation(SessionScope.class))
            return true;
        else if (methodParameter.getMethodAnnotation(SessionScope.class) != null)
            return true;
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String annoVal = null;
        if (methodParameter.getParameterAnnotation(SessionScope.class) != null) {
            annoVal = methodParameter.getParameterAnnotation(SessionScope.class).value();
        } else if (methodParameter.getMethodAnnotation(SessionScope.class) != null) {
            annoVal = methodParameter.getMethodAnnotation(SessionScope.class) != null ?
                    StringUtils.defaultString(methodParameter.getMethodAnnotation(SessionScope.class).value()) : StringUtils.EMPTY;
        }

        if (nativeWebRequest.getAttribute(annoVal, RequestAttributes.SCOPE_SESSION) != null) {
            Object returnObj = nativeWebRequest.getAttribute(annoVal, RequestAttributes.SCOPE_SESSION);
            if (returnObj instanceof byte[]) {
                return SerializeUtil.unserialize((byte[]) returnObj);
            } else {
                return returnObj;
            }
        } else {
            return null;
        }
    }
}
