package com.turing.website.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SWAGGER配置类
 * @author CHEN
 * 这是后台与前端对接的接口文档
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    @Bean
    public Docket guestApiDocket() {
        return new Docket(DocumentationType.SPRING_WEB)
                .groupName("前台匿名用户API接口文档")
                .apiInfo(guestApi())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.turing.website.controller.guest"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket AdminApiDocket() {
        return new Docket(DocumentationType.SPRING_WEB)
                .groupName("后台管理API接口文档")
                .apiInfo(adminApi())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.turing.website.controller.admin"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo adminApi() {
        return new ApiInfoBuilder()
                .title("管理员操作数据的接口文档")
                .description("用于给维护图灵智能创新团队网站")
                .termsOfServiceUrl("https://github.com/SmallPineApp1e")
                .version("1.0")
                .build();
    }

    private ApiInfo guestApi(){
        return new ApiInfoBuilder()
                .title("可匿名访问的查询接口文档")
                .description("用于给游览网站的用户查看各种信息")
                .termsOfServiceUrl("https://github.com/SmallPineApp1e")
                .version("1.0")
                .build();

    }
}
