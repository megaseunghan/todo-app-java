package com.todo.todoapp.domain.todo.repository;

import com.todo.todoapp.domain.todo.model.Todo;

public interface TodoRepository {
    Todo save(Todo todo);
}
