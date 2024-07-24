package com.todo.todoapp.infrastructure.jwt.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private final String issuer;
    private final String key;
    private final String accessTokenExpiredDeadLine;
}
