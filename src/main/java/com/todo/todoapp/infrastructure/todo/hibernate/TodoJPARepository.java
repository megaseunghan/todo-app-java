package com.todo.todoapp.infrastructure.todo.hibernate;

import com.todo.todoapp.domain.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoJPARepository extends JpaRepository<Todo, Long> {
}
