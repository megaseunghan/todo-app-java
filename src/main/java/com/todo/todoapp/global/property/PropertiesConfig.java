package com.todo.todoapp.global.property;

import com.todo.todoapp.infrastructure.jwt.config.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {JwtProperties.class,})
public class PropertiesConfig {
}
