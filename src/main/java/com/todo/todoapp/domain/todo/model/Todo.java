package com.todo.todoapp.domain.todo.model;

import com.todo.todoapp.presentation.todo.dto.request.UpdateTodoRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity(name = "todos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String manager;
    private String password;
    private LocalDateTime createdDate;

    public void update(UpdateTodoRequest request) {
        this.title = request.title();
        this.description = request.description();
        this.manager = request.manager();
    }
}
