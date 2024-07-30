package com.todo.todoapp.presentation.todo.dto.response;

import com.todo.todoapp.domain.todo.model.Todo;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TodoResponse(
        long id,
        String title,
        String description,
        LocalDateTime createdAt,
        String userName
) {
    public static TodoResponse from(Todo todo) {
        return TodoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .userName(todo.getUser().getUserName())
                .createdAt(todo.getCreatedAt())
                .build();
    }
}
