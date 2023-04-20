package com.woopaca.webfluxtest.my;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
@RequiredArgsConstructor
public class MyFilterConfig {

    private final EventNotifier eventNotifier;

    @Bean
    public FilterRegistrationBean<Filter> addFilter1() {
        System.out.println("필터 등록됨");
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>(new MyFilter1(eventNotifier));
        bean.addUrlPatterns("/sse");
        return bean;

    }

    @Bean
    public FilterRegistrationBean<Filter> addFilter2() {
        System.out.println("필터 등록됨");
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>(new MyFilter2(eventNotifier));
        bean.addUrlPatterns("/add");
        return bean;
    }
}
