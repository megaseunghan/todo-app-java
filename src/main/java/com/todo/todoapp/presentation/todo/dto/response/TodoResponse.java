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
        String manager
) {
    public static TodoResponse from(Todo todo) {
        return TodoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .manager(todo.getManager())
                .createdAt(todo.getCreatedAt())
                .build();
    }
}
