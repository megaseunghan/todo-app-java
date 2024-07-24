package com.todo.todoapp.presentation.user.dto.response;

import com.todo.todoapp.domain.user.vo.Role;
import lombok.Builder;

import java.util.Set;

@Builder
public record VerifyUserResponse(
        String userName,
        Set<Role> roles
) {
}
