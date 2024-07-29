package com.todo.todoapp.application.comment;

import com.todo.todoapp.domain.comment.model.Comment;
import com.todo.todoapp.domain.comment.repository.CommentRepository;
import com.todo.todoapp.domain.todo.model.Todo;
import com.todo.todoapp.domain.todo.repository.TodoRepository;
import com.todo.todoapp.domain.user.model.User;
import com.todo.todoapp.domain.user.repository.UserRepository;
import com.todo.todoapp.global.auth.util.AuthUtil;
import com.todo.todoapp.global.exception.comment.NoSuchCommentException;
import com.todo.todoapp.global.exception.comment.code.CommentErrorCode;
import com.todo.todoapp.global.exception.todo.NoSuchTodoException;
import com.todo.todoapp.global.exception.todo.code.TodoErrorCode;
import com.todo.todoapp.global.exception.user.NoSuchUserException;
import com.todo.todoapp.global.exception.user.code.UserErrorCode;
import com.todo.todoapp.presentation.comment.dto.request.CreateCommentRequest;
import com.todo.todoapp.presentation.comment.dto.request.UpdateCommentRequest;
import com.todo.todoapp.presentation.comment.dto.response.CommentResponse;
import com.todo.todoapp.presentation.user.dto.response.AuthenticateUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentResponse save(long todoId, CreateCommentRequest request, AuthenticateUser user) {
        Todo todo = getTodoById(todoId);
        User currentUser = getUserByUserName(user.userName());
        Comment comment = commentRepository.save(request.toEntity(todo, currentUser));

        return CommentResponse.from(comment, currentUser.getUserName());
    }


    @Transactional
    public CommentResponse update(long id, UpdateCommentRequest request, AuthenticateUser user) {
        Comment comment = getCommentById(id);
        User currentUser = getUserByUserName(user.userName());
        AuthUtil.isAuthorize(comment.getUser(), currentUser);
        comment.update(request);

        return CommentResponse.from(comment, currentUser.getUserName());
    }

    @Transactional
    public void delete(long id, AuthenticateUser user) {
        Comment comment = getCommentById(id);
        User currentUser = getUserByUserName(user.userName());
        AuthUtil.isAuthorize(comment.getUser(), currentUser);
        commentRepository.delete(comment);
    }

    private Comment getCommentById(long id) {
        return commentRepository.findById(id).orElseThrow(() -> new NoSuchCommentException(CommentErrorCode.FIND_FAILED));
    }

    private Todo getTodoById(long todoId) {
        return todoRepository.findById(todoId).orElseThrow(() -> new NoSuchTodoException(TodoErrorCode.FIND_FAILED));
    }

    private User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(() -> new NoSuchUserException(UserErrorCode.FIND_FAILED));
    }
}
