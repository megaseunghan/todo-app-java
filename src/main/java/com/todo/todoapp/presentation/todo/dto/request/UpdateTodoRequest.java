package com.todo.todoapp.presentation.todo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateTodoRequest(
        @Size(max = 200)
        @NotBlank(message = "수정될 제목을 입력해주세요")
        String title,
        @Size(max = 200)
        @NotBlank(message = "수정될 내용을 입력해주세요")
        String description
) {
}
