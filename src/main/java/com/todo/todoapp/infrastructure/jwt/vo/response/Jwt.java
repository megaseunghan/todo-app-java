package com.todo.todoapp.infrastructure.jwt.vo.response;

import lombok.Builder;

@Builder
public record Jwt(
        String accessToken
) {
}
