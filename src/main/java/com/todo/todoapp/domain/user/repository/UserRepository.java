package com.todo.todoapp.domain.user.repository;

import com.todo.todoapp.domain.user.model.User;

public interface UserRepository {
    User save(User user);

    boolean existsByNickname(String nickname);
}
