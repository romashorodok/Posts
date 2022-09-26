package com.example.server.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> role;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    @Column(name = "avatar_url")
    private String avatarUrl;

    public User() {
    }

    public User(int id, Set<Role> role, String password, String email, String firstName, String lastName, String avatarUrl) {
        this.id = id;
        this.role = role;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarUrl = avatarUrl;

    }

    public User(Set<Role> role, String password, String email, String firstName, String lastName, String avatarUrl) {
        this.role = role;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarUrl = avatarUrl;
    }

}
