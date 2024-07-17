package com.todo.todoapp.presentation.todo.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Objects;

public record CreateTodoRequest(
        @NotNull(message = "제목을 입력해주세요")
        String title,
        @NotNull(message = "내용을 입력해주세요")
        String description,
        @NotNull(message = "담당자를 입력해주세요")
        String manager,
        @NotNull(message = "비밀번호를 입력해주세요(4글자")
        @Size(min = 4, max = 4)
        String password,

        LocalDateTime createdDate
) {

    @Builder
    public CreateTodoRequest {
        if (Objects.isNull(createdDate)) {
            createdDate = LocalDateTime.now();
        }
    }
}
