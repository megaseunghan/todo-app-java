package com.todo.todoapp.application.todo;

import com.todo.todoapp.domain.todo.model.Todo;
import com.todo.todoapp.domain.todo.repository.TodoRepository;
import com.todo.todoapp.presentation.todo.dto.request.CreateTodoRequest;
import com.todo.todoapp.presentation.todo.dto.response.CreatedTodoResponse;
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
    public CreatedTodoResponse save(CreateTodoRequest request) {
        Todo todo = todoRepository.save(request.toEntity());
        return CreatedTodoResponse.from(todo);
    }

    public CreatedTodoResponse find(long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "로 조회되는 할 일은 없습니다"));
        return CreatedTodoResponse.from(todo);
    }

    public List<CreatedTodoResponse> findAll() {
        List<Todo> todos = todoRepository.findAll();
        todos.sort(Comparator.comparing(Todo::getCreatedDate).reversed());
        return todos.stream().map((todo -> CreatedTodoResponse.from(todo))).toList();
    }
}
