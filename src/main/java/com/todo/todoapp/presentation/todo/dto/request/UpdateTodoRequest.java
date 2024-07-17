package com.todo.todoapp.presentation.todo.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateTodoRequest(
        @NotBlank(message = "수정될 제목을 입력해주세요")
        String title,
        @NotBlank(message = "수정될 내용을 입력해주세요")
        String description,
        @NotBlank(message = "수정될 담당자를 입력해주세요")
        String manager,
        @NotBlank(message = "비밀번호는 필수 입력입니다")
        String password
) {
}
