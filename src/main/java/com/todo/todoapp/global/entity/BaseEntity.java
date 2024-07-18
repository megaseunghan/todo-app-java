package com.todo.todoapp.global.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Column(columnDefinition = "TIMESTAMP(6)", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP(6)", nullable = false)
    @CreatedDate
    private LocalDateTime updatedAt;
}

