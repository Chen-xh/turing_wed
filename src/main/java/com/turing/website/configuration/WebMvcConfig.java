package com.turing.website.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author CHEN
 * 文件映射组件
 * 使前端可以通过url直接访问服务器的静态资源
 */
@Component
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${photo.dir}")
    String fileUrl;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决swagger无法访问
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");


        registry.addResourceHandler("/images/**").addResourceLocations("file:"+fileUrl);
    }
}

