package com.orange.bootdemo;

import com.orange.bootdemo.handle.Interceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BootdemoApplication implements WebMvcConfigurer {

    public static void main(String[] args) {SpringApplication.run(BootdemoApplication.class, args);}

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new Interceptor());
        interceptorRegistration.addPathPatterns("/interceptor");
    }
}
