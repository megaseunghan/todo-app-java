package com.todo.todoapp.presentation.user;

import com.todo.todoapp.application.user.UserService;
import com.todo.todoapp.presentation.user.dto.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> signup(@RequestBody SignUpRequest request) {
        long id = userService.signup(request);
        URI uri = URI.create(String.format("/users/%d", id));
        return ResponseEntity.created(uri).body("회원가입 완료!");
    }
}
