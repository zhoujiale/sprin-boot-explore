package com.github.zhoujiale.spring.boot.security.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @name: SwaggerConfiguration
 * @description:
 * @author: zhou
 * @create: 2020-10-07 15:00
 */
@EnableOpenApi
@Configuration
public class SwaggerConfiguration implements WebMvcConfigurer {

    @Bean
    public Docket createRestApi(){
        List<RequestParameter> list = new ArrayList<>();
        RequestParameterBuilder tokenBuilder = new RequestParameterBuilder();
        tokenBuilder.name(RequestConstant.TOKEN).description("token")
                .in(ParameterType.HEADER).build();
        RequestParameterBuilder remarkBuilder = new RequestParameterBuilder();
        remarkBuilder.name(RequestConstant.REMARK).description("remark")
                .in(ParameterType.HEADER).build();
        list.add(tokenBuilder.build());
        list.add(remarkBuilder.build());
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(list);
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("security项目")
                .description("security项目接口")
                .version("1.0")
                .build();
    }
}
