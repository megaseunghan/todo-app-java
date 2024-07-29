package com.todo.todoapp.presentation.comment.dto.request;

import com.todo.todoapp.domain.comment.model.Comment;
import com.todo.todoapp.domain.todo.model.Todo;
import com.todo.todoapp.domain.user.model.User;
import jakarta.validation.constraints.NotBlank;

public record CreateCommentRequest(
        @NotBlank(message = "내용을 입력해주세요")
        String comment
) {
    public Comment toEntity(Todo todo, User user) {
        return Comment.builder()
                .comment(comment)
                .todo(todo)
                .user(user)
                .build();
    }
}
