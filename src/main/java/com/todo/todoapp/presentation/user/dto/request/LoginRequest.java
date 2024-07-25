package com.todo.todoapp.presentation.user.dto.request;

public record LoginRequest(
        String userName,
        String password
) {
}
