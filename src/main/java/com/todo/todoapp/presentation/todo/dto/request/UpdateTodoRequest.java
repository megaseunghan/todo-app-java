package com.todo.todoapp.presentation.todo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateTodoRequest(
        @Size(max = 200)
        @NotBlank(message = "수정될 제목을 입력해주세요")
        String title,
        @Size(max = 200)
        @NotBlank(message = "수정될 내용을 입력해주세요")
        String description,

        @Email(regexp = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}", message = "올바른 이메일 형식을 입력해주세요")
        @NotBlank(message = "수정될 담당자를 입력해주세요")
        String manager,
        @NotBlank(message = "비밀번호는 필수 입력입니다")
        String password
) {
}
