package com.todo.todoapp.infrastructure.comment.hibernate;

import com.todo.todoapp.domain.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {
}
