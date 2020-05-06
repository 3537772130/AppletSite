package com.applet.apply.config;

import com.applet.apply.config.argumentResolver.SessionScopeMethod;
import com.applet.apply.config.interceptor.AppletInterceptor;
import com.applet.apply.config.interceptor.FileInterceptor;
import com.applet.apply.config.interceptor.NavigationInterceptor;
import com.applet.common.util.ObjectRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.io.Serializable;
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

    @Bean(name = "springSessionDefaultRedisSerializer")
    public GenericJackson2JsonRedisSerializer getGenericJackson2JsonRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }

    @Bean
    public RedisTemplate<Serializable, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Serializable, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);

        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer =
                new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置value的序列化规则和 key的序列化规则
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new ObjectRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
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
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
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
        registry.addInterceptor(new NavigationInterceptor()).addPathPatterns("/");
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
