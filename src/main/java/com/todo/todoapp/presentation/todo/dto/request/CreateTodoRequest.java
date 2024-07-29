package com.todo.todoapp.presentation.todo.dto.request;

import com.todo.todoapp.domain.todo.model.Todo;
import com.todo.todoapp.domain.user.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTodoRequest(

        @Size(max = 200)
        @NotBlank(message = "제목을 입력해주세요")
        String title,
        @Size(max = 200)
        @NotBlank(message = "내용을 입력해주세요")
        String description
) {
    public Todo toEntity(User user) {
        return Todo.builder()
                .title(title)
                .description(description)
                .user(user)
                .build();
    }
}
