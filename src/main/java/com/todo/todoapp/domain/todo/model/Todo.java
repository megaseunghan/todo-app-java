package com.todo.todoapp.domain.todo.model;

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

    public Todo(String title, String description, String manager, LocalDateTime createdDate) {
        this.title = title;
        this.description = description;
        this.manager = manager;
        this.createdDate = createdDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String manager;
    private String password;
    private LocalDateTime createdDate;
}
