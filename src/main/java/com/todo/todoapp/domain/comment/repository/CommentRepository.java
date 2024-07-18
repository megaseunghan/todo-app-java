package com.todo.todoapp.domain.comment.repository;

import com.todo.todoapp.domain.comment.model.Comment;

import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);

    Optional<Comment> findById(long id);
}
