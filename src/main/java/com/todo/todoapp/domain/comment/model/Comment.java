package com.todo.todoapp.domain.comment.model;

import com.todo.todoapp.domain.todo.model.Todo;
import com.todo.todoapp.global.entity.BaseEntity;
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

    private String comment;

    private String writer;
}
