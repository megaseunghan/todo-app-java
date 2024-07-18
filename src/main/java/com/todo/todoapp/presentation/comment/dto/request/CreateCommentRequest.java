package com.todo.todoapp.presentation.comment.dto.request;


import jakarta.validation.constraints.NotBlank;

public record CreateCommentRequest(
        @NotBlank(message = "내용을 입력해주세요")
        String description,
        @NotBlank(message = "댓글 작성자의 ID를 입력해주세요")
        String writerId
) {
}
