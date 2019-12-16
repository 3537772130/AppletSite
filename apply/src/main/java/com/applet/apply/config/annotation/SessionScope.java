package com.applet.apply.config.annotation;

import java.lang.annotation.*;

/**
 * @program: SpringBootDemo
 * @description: 注册session获取为注解获取
 * @author: Mr.ZhouHuaHu
 * @create: 2019-07-08 09:21
 **/
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SessionScope {
    String value();
}
