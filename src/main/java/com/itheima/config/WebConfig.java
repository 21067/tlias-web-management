package com.itheima.config;

import com.itheima.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration//代表当前类是一个配置类
public class WebConfig implements WebMvcConfigurer {

    @Autowired//此时这个拦截器已经是IOC容器的一个bean对象了，直接注入就可以用了
    private LoginCheckInterceptor loginCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {//调用api
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**");
    }
}
