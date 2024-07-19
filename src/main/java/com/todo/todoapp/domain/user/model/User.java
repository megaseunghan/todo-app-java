package com.todo.todoapp.domain.user.model;

import com.todo.todoapp.domain.user.vo.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nickname;
    private String userName;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role userRole;
}
