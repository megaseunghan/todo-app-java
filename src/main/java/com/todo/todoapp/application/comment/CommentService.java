package com.todo.todoapp.application.comment;

import com.todo.todoapp.domain.comment.model.Comment;
import com.todo.todoapp.domain.comment.repository.CommentRepository;
import com.todo.todoapp.domain.todo.model.Todo;
import com.todo.todoapp.domain.todo.repository.TodoRepository;
import com.todo.todoapp.global.exception.comment.NoSuchCommentException;
import com.todo.todoapp.global.exception.comment.code.CommentErrorCode;
import com.todo.todoapp.global.exception.todo.NoSuchTodoException;
import com.todo.todoapp.global.exception.todo.code.TodoErrorCode;
import com.todo.todoapp.presentation.comment.dto.request.CreateCommentRequest;
import com.todo.todoapp.presentation.comment.dto.request.UpdateCommentRequest;
import com.todo.todoapp.presentation.comment.dto.response.CommentResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentResponse save(long todoId, CreateCommentRequest request) {
        Todo todo = getTodoById(todoId);
        Comment comment = commentRepository.save(request.toEntity(todo));

        return CommentResponse.from(comment);
    }

    private Todo getTodoById(long todoId) {
        return todoRepository.findById(todoId).orElseThrow(() -> new NoSuchTodoException(TodoErrorCode.FIND_FAILED));
    }

    @Transactional
    public CommentResponse update(long id, UpdateCommentRequest request) {
        Comment comment = getCommentById(id);
        comment.update(request);
        return CommentResponse.from(comment);
    }

    private Comment getCommentById(long id) {
        return commentRepository.findById(id).orElseThrow(() -> new NoSuchCommentException(CommentErrorCode.FIND_FAILED));
    }
}
