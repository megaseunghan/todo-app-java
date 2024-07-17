package com.todo.todoapp.infrastructure.todo;

import com.todo.todoapp.domain.todo.model.Todo;
import com.todo.todoapp.domain.todo.repository.TodoRepository;
import com.todo.todoapp.infrastructure.todo.hibernate.TodoJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TodoRepositoryImpl implements TodoRepository {
    private final TodoJPARepository jpaRepository;

    @Override
    public Todo save(Todo todo) {
        return jpaRepository.save(todo);
    }

    @Override
    public Optional<Todo> findById(long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Todo> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public void delete(Todo todo) {
        jpaRepository.delete(todo);
    }
}
