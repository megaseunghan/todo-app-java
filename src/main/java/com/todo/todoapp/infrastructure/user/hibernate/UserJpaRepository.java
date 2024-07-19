package com.todo.todoapp.infrastructure.user.hibernate;

import com.todo.todoapp.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    boolean existsByNickname(String nickname);
}
