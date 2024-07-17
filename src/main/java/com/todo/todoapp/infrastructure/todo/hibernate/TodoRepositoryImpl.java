package com.todo.todoapp.infrastructure.todo.hibernate;

import com.todo.todoapp.domain.todo.model.Todo;
import com.todo.todoapp.domain.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TodoRepositoryImpl implements TodoRepository {
    private final TodoJPARepository jpaRepository;

    @Override
    public Todo save(Todo todo) {
        return jpaRepository.save(todo);
    }
}
