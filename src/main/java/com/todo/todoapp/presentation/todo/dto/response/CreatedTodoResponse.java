package com.todo.todoapp.presentation.todo.dto.response;

import com.todo.todoapp.domain.todo.model.Todo;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CreatedTodoResponse(
        long id,
        String title,
        String description,
        LocalDateTime createdDate,
        String manager
) {
    public static CreatedTodoResponse from(Todo todo) {
        return CreatedTodoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .manager(todo.getManager())
                .createdDate(todo.getCreatedDate())
                .build();
    }
}
