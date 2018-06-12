package com.leeves.app.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Description: swagger配置
 * Package: com.leeves.app.conf
 *
 * @author Leeves
 * @version 1.0.0  2018-06-12
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //扫描controller类
                .apis(RequestHandlerSelectors.basePackage("com.leeves.app.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API接口文档")
                .termsOfServiceUrl("http://localhost:8003")
                .description("API使用描述")
                .version("0.1")
                .build();
    }
}