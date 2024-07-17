package com.todo.todoapp.presentation.todo;

import com.todo.todoapp.application.todo.TodoService;
import com.todo.todoapp.presentation.todo.dto.request.CreateTodoRequest;
import com.todo.todoapp.presentation.todo.dto.response.CreatedTodoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<CreatedTodoResponse> save(@RequestBody CreateTodoRequest request) {
        CreatedTodoResponse response = todoService.save(request);
        long id = response.id();
        URI uri = URI.create(String.format("/todos/%d", id));
        return ResponseEntity.created(uri).body(response);
    }
}
