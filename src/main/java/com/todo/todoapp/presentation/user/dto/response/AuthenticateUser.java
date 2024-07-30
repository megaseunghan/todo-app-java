package com.todo.todoapp.presentation.user.dto.response;

import com.todo.todoapp.domain.user.vo.Role;
import lombok.Builder;

@Builder
public record AuthenticateUser(
        String userName,
        Role role,
        String password
) {
}
