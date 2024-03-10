package com.github.zhoujiale.spring.boot.web.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
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
 * @author zhou
 * @className SwaggerConfiguration
 * @descrption swagger配置
 * @date 2022/9/5 17:22
 */
@EnableOpenApi
@Configuration
public class SwaggerConfiguration implements WebMvcConfigurer {

    /**
     * swagger开放配置
     **/
    @Value("${swagger.enable}")
    private Boolean enable;

    @Bean
    public Docket createRestApi(){
        List<RequestParameter> parameterList = new ArrayList<>();
        parameterList.add(new RequestParameterBuilder()
                .name("token")
                .required(false)
                .in(ParameterType.HEADER)
                .required(false)
                .build());
        return new Docket(DocumentationType.OAS_30)
                .enable(enable)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(parameterList);
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("web商店")
                .description("web商店接口")
                .version("1.0")
                .build();
    }
}
