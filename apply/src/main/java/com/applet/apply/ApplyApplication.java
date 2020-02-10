package com.applet.apply;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@MapperScan(basePackages = "com.applet.apply.mapper.**")
public class ApplyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplyApplication.class, args);
    }

}
