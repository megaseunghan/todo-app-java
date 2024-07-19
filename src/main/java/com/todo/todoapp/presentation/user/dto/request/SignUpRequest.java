package com.todo.todoapp.presentation.user.dto.request;

import com.todo.todoapp.domain.user.model.User;
import com.todo.todoapp.domain.user.vo.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SignUpRequest(
        @NotBlank(message = "닉네임을 입력해주세요")
        String nickname,

        @NotBlank(message = "사용자 이름을 입력해주세요")
        @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "올바른 양식을 입력해주세요")
        String userName,
        String password,
        Role role
) {
    public User toEntity() {
        return User.builder()
                .nickname(nickname)
                .userName(userName)
                .password(password)
                .userRole(role)
                .build();
    }
}
