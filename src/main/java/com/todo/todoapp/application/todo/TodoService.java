package com.todo.todoapp.application.todo;

import com.todo.todoapp.domain.todo.model.Todo;
import com.todo.todoapp.domain.todo.repository.TodoRepository;
import com.todo.todoapp.presentation.todo.dto.request.CreateTodoRequest;
import com.todo.todoapp.presentation.todo.dto.request.UpdateTodoRequest;
import com.todo.todoapp.presentation.todo.dto.response.TodoResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    @Transactional
    public TodoResponse save(CreateTodoRequest request) {
        Todo todo = todoRepository.save(request.toEntity());
        return TodoResponse.from(todo);
    }

    public TodoResponse find(long id) {
        Todo todo = getTodoById(id);
        return TodoResponse.from(todo);
    }

    public List<TodoResponse> findAll() {
        List<Todo> todos = todoRepository.findAll();
        todos.sort(Comparator.comparing(Todo::getCreatedDate).reversed());
        return todos.stream().map((TodoResponse::from)).toList();
    }

    @Transactional
    public TodoResponse update(long id, UpdateTodoRequest request) {
        Todo todo = getTodoById(id);

        if (!request.password().equals(todo.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 올바르지 않습니다.");
        }

        todo.update(request);

        return TodoResponse.from(todo);
    }

    private Todo getTodoById(long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "로 조회되는 할 일은 없습니다"));
        return todo;
    }

}
