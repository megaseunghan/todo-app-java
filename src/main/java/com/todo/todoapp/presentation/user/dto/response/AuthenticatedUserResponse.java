package com.todo.todoapp.presentation.user.dto.response;

import com.todo.todoapp.domain.user.vo.Role;
import lombok.Builder;

@Builder
public record AuthenticatedUserResponse(
        String userName,
        Role role,
        String password
) {
}
