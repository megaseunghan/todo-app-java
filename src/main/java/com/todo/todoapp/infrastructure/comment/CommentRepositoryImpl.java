package com.todo.todoapp.infrastructure.comment;

import com.todo.todoapp.domain.comment.model.Comment;
import com.todo.todoapp.domain.comment.repository.CommentRepository;
import com.todo.todoapp.infrastructure.comment.hibernate.CommentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {
    private final CommentJpaRepository jpaRepository;

    @Override
    public Comment save(Comment comment) {
        return jpaRepository.save(comment);
    }

    @Override
    public Optional<Comment> findById(long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public void delete(Comment comment) {
        jpaRepository.delete(comment);
    }
}
