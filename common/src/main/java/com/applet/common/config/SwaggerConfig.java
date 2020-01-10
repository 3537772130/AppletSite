package com.applet.common.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger 配置
 *
 * @author 谭良忠
 * @date 2019/12/25 14:56
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        List<Parameter> params = new ArrayList<>();
        ParameterBuilder wxCode = new ParameterBuilder().name("wxCode").modelRef(new ModelRef("string")).parameterType("header").required(false);
        ParameterBuilder appletCode = new ParameterBuilder().name("appletCode").modelRef(new ModelRef("string")).parameterType("header").required(false);
        params.add(wxCode.build());
        params.add(appletCode.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(params)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger API")
                .contact(new Contact("LiangZhong.Tan", "", "liangzhong.tan@outlook.com"))
                .version("0.0.1")
                .build();
    }

}
