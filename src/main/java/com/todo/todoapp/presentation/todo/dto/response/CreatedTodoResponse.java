package com.todo.todoapp.presentation.todo.dto.response;

import java.time.LocalDateTime;

public record CreatedTodoResponse(
        String title,
        String description,
        LocalDateTime createdDate,
        String manager
) {
}
