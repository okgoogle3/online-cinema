package com.cinema.repo.model;

import javax.validation.constraints.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "users")
public final class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(columnDefinition = "ENUM('ADMIN', 'MODERATOR', 'WRITER', 'CRITIC', 'MANAGER', 'USER')")
    @Enumerated(EnumType.STRING)
    @NotNull
    private UserType type;

    @NotNull
    @Column(unique=true)
    private String username;

    @NotNull
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.type = UserType.USER;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
