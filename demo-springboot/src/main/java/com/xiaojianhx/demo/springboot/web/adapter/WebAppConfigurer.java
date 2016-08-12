package com.xiaojianhx.demo.springboot.web.adapter;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.xiaojianhx.demo.springboot.web.interceptor.LogInterceptor;

@Configurable
public class WebAppConfigurer extends WebMvcConfigurerAdapter {

    public WebAppConfigurer() {
        System.out.println("1212121");
    }

    private LogInterceptor logInterceptor = new LogInterceptor();

    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("WebAppConfigurer执行了");
        registry.addInterceptor(logInterceptor).addPathPatterns("/**/**");
        super.addInterceptors(registry);
    }
}
