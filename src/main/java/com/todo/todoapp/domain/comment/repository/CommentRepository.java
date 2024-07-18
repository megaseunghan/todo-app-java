package com.todo.todoapp.domain.comment.repository;

import com.todo.todoapp.domain.comment.model.Comment;

public interface CommentRepository {
    Comment save(Comment comment);
}
