package com.todo.todoapp.presentation.todo.dto.request;

import com.todo.todoapp.domain.todo.model.Todo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Objects;

public record CreateTodoRequest(

        @Size(max = 200)
        @NotBlank(message = "제목을 입력해주세요")
        String title,
        @Size(max = 200)
        @NotBlank(message = "내용을 입력해주세요")
        String description,
        @Email(regexp = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}", message = "올바른 이메일 형식을 입력해주세요")
        @NotBlank(message = "담당자를 입력해주세요")
        String manager,
        @NotBlank(message = "비밀번호는 필수 입력입니다.(4글자)")
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

    public Todo toEntity() {
        return Todo.builder()
                .title(title)
                .description(description)
                .manager(manager)
                .password(password)
                .createdDate(createdDate)
                .build();
    }
}
