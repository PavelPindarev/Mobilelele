package com.example.mobilelele.model.entity;

import com.example.mobilelele.model.enums.RoleType;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType role;

    public UserRole() {
    }

    public long getId() {
        return id;
    }

    public UserRole setId(long id) {
        this.id = id;
        return this;
    }

    public RoleType getRole() {
        return role;
    }

    public UserRole setRole(RoleType role) {
        this.role = role;
        return this;
    }
}
