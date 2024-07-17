package com.todo.todoapp.application.todo;

import com.todo.todoapp.domain.todo.model.Todo;
import com.todo.todoapp.domain.todo.repository.TodoRepository;
import com.todo.todoapp.global.exception.todo.IncorrectPasswordException;
import com.todo.todoapp.global.exception.todo.NoSuchTodoException;
import com.todo.todoapp.global.exception.todo.code.TodoErrorCode;
import com.todo.todoapp.presentation.todo.dto.request.CreateTodoRequest;
import com.todo.todoapp.presentation.todo.dto.request.DeleteTodoRequest;
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
        isCorrectPassword(todo.getPassword(), request.password());
        todo.update(request);

        return TodoResponse.from(todo);
    }

    @Transactional
    public void delete(long id, DeleteTodoRequest request) {
        Todo todo = getTodoById(id);
        isCorrectPassword(todo.getPassword(), request.password());
        todoRepository.delete(todo);
    }

    private Todo getTodoById(long id) {
        return todoRepository.findById(id).orElseThrow(() -> new NoSuchTodoException(TodoErrorCode.FIND_FAILED));
    }

    private void isCorrectPassword(String origin, String comparison) {
        if (!origin.equals(comparison)) {
            throw new IncorrectPasswordException(TodoErrorCode.INCORRECT_PASSWORD);
        }
    }
}
