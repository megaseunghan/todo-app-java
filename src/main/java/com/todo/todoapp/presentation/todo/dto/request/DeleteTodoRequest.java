package com.todo.todoapp.presentation.todo.dto.request;

import jakarta.validation.constraints.NotBlank;

public record DeleteTodoRequest(
        @NotBlank(message = "비밀번호는 필수 입력입니다")
        String password
) {
}
