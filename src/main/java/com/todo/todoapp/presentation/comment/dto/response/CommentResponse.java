package com.todo.todoapp.presentation.comment.dto.response;

import lombok.Builder;

@Builder
public record CommentResponse(
        long id,
        String description,
        String writer,
        String createdAt
) {
}
