package com.todo.todoapp.domain.user.repository;

import com.todo.todoapp.domain.user.model.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    boolean existsByNickname(String nickname);

    Optional<User> findByUserName(String userName);
}
