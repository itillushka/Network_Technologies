package com.illia.project.ntilliaproject.infrastructure.entity;

import com.illia.project.ntilliaproject.commonTypes.UserRole;
import jakarta.persistence.*;

@Entity
@Table(name = "auth", schema = "library")
public class AuthEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authID")
    private long authID;

    @Basic
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Basic
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;

    @OneToOne
    @JoinColumn(name = "userID")
    private UserEntity user;

    public AuthEntity() {
    }

    public AuthEntity(long authID, String username, String password, UserRole role, UserEntity user) {
        this.authID = authID;
        this.username = username;
        this.password = password;
        this.role = role;
        this.user = user;
    }

    public long getAuthID() {
        return authID;
    }

    public void setAuthID(long authID) {
        this.authID = authID;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
