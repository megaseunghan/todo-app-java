package com.todo.todoapp.domain.todo.model;

import com.todo.todoapp.domain.user.model.User;
import com.todo.todoapp.global.entity.BaseEntity;
import com.todo.todoapp.presentation.todo.dto.request.UpdateTodoRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "todos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo extends BaseEntity {

    @Id
    @Column(name = "todo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;
    private String description;

    public void update(UpdateTodoRequest request) {
        this.title = request.title();
        this.description = request.description();
    }
}
