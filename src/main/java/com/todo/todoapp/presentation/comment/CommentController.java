package com.todo.todoapp.presentation.comment;

import com.todo.todoapp.application.comment.CommentService;
import com.todo.todoapp.presentation.comment.dto.request.CreateCommentRequest;
import com.todo.todoapp.presentation.comment.dto.request.UpdateCommentRequest;
import com.todo.todoapp.presentation.comment.dto.response.CommentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{todoId}")
    public ResponseEntity<CommentResponse> save(
            @PathVariable long todoId,
            @Valid @RequestBody CreateCommentRequest request) {
        CommentResponse response = commentService.save(todoId, request);
        long id = response.id();
        URI uri = URI.create(String.format("/comments/%d/%d", todoId, id));
        return ResponseEntity.created(uri).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommentResponse> update(
            @PathVariable long id,
            @Valid @RequestBody UpdateCommentRequest request) {
        CommentResponse response = commentService.update(id, request);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();

    }
}