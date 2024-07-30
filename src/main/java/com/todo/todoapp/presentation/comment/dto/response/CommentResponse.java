package com.todo.todoapp.presentation.comment.dto.response;

import com.todo.todoapp.domain.comment.model.Comment;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommentResponse(
        long id,
        String comment,
        String userName,
        LocalDateTime createdAt
) {
    public static CommentResponse from(Comment comment, String userName) {
        return CommentResponse.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .userName(userName)
                .createdAt(comment.getCreatedAt())
                .build();

    }
}
