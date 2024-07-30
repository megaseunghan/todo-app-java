package com.todo.todoapp.application.todo;

import com.todo.todoapp.domain.todo.model.Todo;
import com.todo.todoapp.domain.todo.repository.TodoRepository;
import com.todo.todoapp.domain.user.model.User;
import com.todo.todoapp.domain.user.repository.UserRepository;
import com.todo.todoapp.global.auth.util.AuthUtil;
import com.todo.todoapp.global.exception.todo.NoSuchTodoException;
import com.todo.todoapp.global.exception.todo.code.TodoErrorCode;
import com.todo.todoapp.global.exception.user.NoSuchUserException;
import com.todo.todoapp.global.exception.user.code.UserErrorCode;
import com.todo.todoapp.presentation.todo.dto.request.CreateTodoRequest;
import com.todo.todoapp.presentation.todo.dto.request.UpdateTodoRequest;
import com.todo.todoapp.presentation.todo.dto.response.TodoResponse;
import com.todo.todoapp.presentation.user.dto.response.AuthenticateUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Transactional
    public TodoResponse save(CreateTodoRequest request, AuthenticateUser authenticateUser) {
        User currentUser = getUserByUserName(authenticateUser.userName());
        Todo todo = todoRepository.save(request.toEntity(currentUser));
        return TodoResponse.from(todo);
    }

    public TodoResponse find(long id) {
        Todo todo = getTodoById(id);
        return TodoResponse.from(todo);
    }

    public List<TodoResponse> findAll() {
        List<Todo> todos = todoRepository.findAll();
        todos.sort(Comparator.comparing(Todo::getCreatedAt).reversed());
        return todos.stream().map((TodoResponse::from)).toList();
    }

    @Transactional
    public TodoResponse update(long id, UpdateTodoRequest request, AuthenticateUser authenticateUser) {
        Todo todo = getTodoById(id);
        User currentUser = getUserByUserName(authenticateUser.userName());
        AuthUtil.isAuthorize(todo.getUser(), currentUser);

        todo.update(request);

        return TodoResponse.from(todo);
    }

    @Transactional
    public void delete(long id, AuthenticateUser authenticateUser) {
        Todo todo = getTodoById(id);
        User currentUser = getUserByUserName(authenticateUser.userName());
        AuthUtil.isAuthorize(todo.getUser(), currentUser);

        todoRepository.delete(todo);
    }

    private Todo getTodoById(long id) {
        return todoRepository.findById(id).orElseThrow(() -> new NoSuchTodoException(TodoErrorCode.FIND_FAILED));
    }

    private User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(() -> new NoSuchUserException(UserErrorCode.FIND_FAILED));
    }
}
