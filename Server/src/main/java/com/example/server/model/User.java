package com.example.server.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String description;
    private String username;
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Roles> role;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String country;
    private String avatarUrl;
    private LocalDateTime createdAt;



    public User() {
    }

    public User(String id, String description, String username, Set<Roles> role, String password, String email, String firstName, String lastName, String country, String avatarUrl, LocalDateTime createdAt) {
        this.id = id;
        this.description = description;
        this.username = username;
        this.role = role;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
    }

    public User(String description, String username, Set<Roles> role, String password, String email, String firstName, String lastName, String country, String avatarUrl) {
        this.description = description;
        this.username = username;
        this.role = role;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.avatarUrl = avatarUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


}
