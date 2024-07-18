package com.todo.todoapp.presentation.comment.dto.request;


import com.todo.todoapp.domain.comment.model.Comment;
import com.todo.todoapp.domain.todo.model.Todo;
import jakarta.validation.constraints.NotBlank;

public record CreateCommentRequest(
        @NotBlank(message = "내용을 입력해주세요")
        String comment,
        @NotBlank(message = "댓글 작성자의 ID를 입력해주세요")
        String writerId
) {
    public Comment toEntity(Todo todo) {
        return Comment.builder()
                .comment(comment)
                .writerId(writerId)
                .todo(todo)
                .build();
    }
}
