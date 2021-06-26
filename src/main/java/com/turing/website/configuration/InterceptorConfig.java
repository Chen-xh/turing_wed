package com.turing.website.configuration;

import com.turing.website.filter.ResumeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author CHEN
 * @date 2020/3/7 21:30
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //需要拦截的路径
        String[]addPathPatterns={"/guest/resume**"};
        //不需要拦截的路径
        //注册一个拦截器
        registry.addInterceptor(new ResumeInterceptor())
                .addPathPatterns(addPathPatterns);
    }
}
