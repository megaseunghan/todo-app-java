package com.todo.todoapp.presentation.todo;

import com.todo.todoapp.application.todo.TodoService;
import com.todo.todoapp.global.auth.annotation.Authenticate;
import com.todo.todoapp.presentation.todo.dto.request.CreateTodoRequest;
import com.todo.todoapp.presentation.todo.dto.request.UpdateTodoRequest;
import com.todo.todoapp.presentation.todo.dto.response.TodoResponse;
import com.todo.todoapp.presentation.user.dto.response.AuthenticateUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponse> save(
            @Valid @RequestBody CreateTodoRequest request,
            @Authenticate AuthenticateUser authenticateUser) {
        TodoResponse response = todoService.save(request, authenticateUser);
        long id = response.id();
        URI uri = URI.create(String.format("/todos/%d", id));
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> find(@PathVariable long id) {
        TodoResponse response = todoService.find(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> findAll() {
        List<TodoResponse> response = todoService.findAll();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> update(
            @PathVariable long id,
            @Valid @RequestBody UpdateTodoRequest request,
            @Authenticate AuthenticateUser authenticateUser) {
        TodoResponse response = todoService.update(id, request, authenticateUser);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable long id,
            @Authenticate AuthenticateUser authenticateUser) {
        todoService.delete(id, authenticateUser);
        return ResponseEntity.noContent().build();
    }


}
