package com.todo.todoapp.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.todoapp.application.user.UserService;
import com.todo.todoapp.global.auth.JwtAuthorizationFilter;
import com.todo.todoapp.global.auth.JwtLoginFilter;
import com.todo.todoapp.global.auth.VerifyUserFilter;
import com.todo.todoapp.infrastructure.jwt.JwtUtil;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean verifyUserFilter(ObjectMapper mapper, UserService userService) {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new VerifyUserFilter(mapper, userService));
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/users/login");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean jwtLoginFilter(JwtUtil jwtUtil, UserService userService) {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new JwtLoginFilter(jwtUtil, userService));
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/users/login");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean jwtAuthorizationFilter(JwtUtil jwtUtil) {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new JwtAuthorizationFilter(jwtUtil));
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }
}
