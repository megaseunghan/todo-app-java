package com.todo.todoapp.domain.comment.model;

import com.todo.todoapp.domain.todo.model.Todo;
import com.todo.todoapp.domain.user.model.User;
import com.todo.todoapp.global.entity.BaseEntity;
import com.todo.todoapp.presentation.comment.dto.request.UpdateCommentRequest;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@Entity(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String comment;

    public void update(UpdateCommentRequest request) {
        this.comment = request.comment();
    }
}
