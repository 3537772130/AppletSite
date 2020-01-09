package com.applet.apply.aop;

import com.applet.apply.service.RedisService;
import com.applet.common.annotation.Resubmit;
import com.applet.common.constant.ResultMsg;
import com.applet.common.excepion.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;

/**
 * 处理重复提交
 *
 * @author liangzhong.tan
 * @date 2020-1-8 17:10:28
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ResubmitAspect {

    private final RedisService redisService;

    @Before("@annotation(com.applet.common.annotation.Resubmit)")
    public void doBefore(final JoinPoint joinPoint) {
        log.info("处理重复提交");
        final MethodSignature methodSignature = (MethodSignature) joinPoint.getStaticPart().getSignature();
        final Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(Resubmit.class)) {
            Resubmit resubmit = method.getAnnotation(Resubmit.class);
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            String appletCode = Optional.ofNullable(request.getHeader("appletCode")).orElse("");
            String wxCode = Optional.ofNullable(request.getHeader("wxCode")).orElse("");
            String key = resubmit.prefix() + appletCode + "_" + wxCode + "_" + request.getRequestURI().hashCode();
            if (!redisService.setNx(key, "1", resubmit.expire())) {
                log.info("操作频繁, appletCode: {}, wxCode: {}, RequestURI: {}", appletCode, wxCode, request.getRequestURI());
                throw BusinessException.of(ResultMsg.FREQUENT_OPERATIONS);
            }
        }
    }

}
