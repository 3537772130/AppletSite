package com.applet.apply.config;

import com.applet.apply.config.argumentResolver.SessionScopeMethod;
import com.applet.apply.config.interceptor.AppletInterceptor;
import com.applet.apply.config.interceptor.FileInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: SpringBootDemo
 * @description:
 * @author: Mr.ZhouHuaHu
 * @create: 2019-07-08 10:12
 **/
@Configuration
@ComponentScan
public class ApplicationConfigurer extends WebMvcConfigurationSupport {
    private static final Logger log = LoggerFactory.getLogger(ApplicationConfigurer.class);

    public ApplicationConfigurer() {
        log.info("apply管理容器初始化...");
    }

    /**
     * 定义异步线程任务
     *
     * @date 2018/6/27
     **/
    @Bean("taskExecutor")
    public Executor taskExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        return executorService;
    }

    /**
     * 对拦截器中的Service进行注入
     * @return
     */
    @Bean("myAppletInterceptor")
    public AppletInterceptor myAppletInterceptor(){
        log.info("对拦截器AppletInterceptor中的Service进行注入");
        return new AppletInterceptor();
    }

    /**
     * 初始化函数参数分解器
     *
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new SessionScopeMethod());
        log.info("已注入函数参数分解器");
    }

    /**
     * 初始化姿态资源映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
        log.info("已注入静态资源映射");
    }

    /**
     * 初始化拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myAppletInterceptor()).addPathPatterns("/api/applet/**");
        registry.addInterceptor(new FileInterceptor()).addPathPatterns("/api/public/**");
        registry.addInterceptor(new FileInterceptor()).addPathPatterns("/api/image/**");
        registry.addInterceptor(new FileInterceptor()).addPathPatterns("/api/audio/**");
        registry.addInterceptor(new FileInterceptor()).addPathPatterns("/api/video/**");
        registry.addInterceptor(new FileInterceptor()).addPathPatterns("/api/zip/**");
        super.addInterceptors(registry);
        log.info("已注入拦截器");
    }

}
