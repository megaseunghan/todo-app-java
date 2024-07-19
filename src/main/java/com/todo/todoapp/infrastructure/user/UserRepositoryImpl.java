package com.todo.todoapp.infrastructure.user;

import com.todo.todoapp.domain.user.model.User;
import com.todo.todoapp.domain.user.repository.UserRepository;
import com.todo.todoapp.infrastructure.user.hibernate.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository jpaRepository;

    @Override
    public User save(User user) {
        return jpaRepository.save(user);
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return jpaRepository.existsByNickname(nickname);
    }
}
