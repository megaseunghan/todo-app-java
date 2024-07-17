package com.todo.todoapp.domain.todo.repository;

import com.todo.todoapp.domain.todo.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    Todo save(Todo todo);

    Optional<Todo> findById(long id);

    List<Todo> findAll();

    void delete(Todo todo);
}
