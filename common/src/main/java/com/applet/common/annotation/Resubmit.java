package com.applet.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 拦截重复提交注解
 *
 * @author liangzhong.tan
 * @date 2020-1-8 17:10:28
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Resubmit {

    /**
     * 储存前缀
     *
     * @return
     */
    String prefix() default "RESUBMIT_LOCK:";

    /**
     * 锁定时间，单位：秒
     *
     * @return
     */
    long expire() default 7;
}
