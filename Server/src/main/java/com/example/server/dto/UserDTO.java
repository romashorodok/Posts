package com.example.server.dto;


import lombok.Data;
import java.util.Set;

@Data
public class UserDTO {

    private Integer id;
    private Set<RoleDTO> role;
    private String password;
    private String email;

    private String firstName;

    private String lastName;

    private String avatarUrl;

    public UserDTO() {
    }

    public UserDTO(int id, Set<RoleDTO> role, String password, String email, String firstName, String lastName, String avatarUrl) {
        this.id = id;
        this.role = role;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarUrl = avatarUrl;

    }

    public UserDTO(Set<RoleDTO> role, String password, String email, String firstName, String lastName, String avatarUrl) {
        this.role = role;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarUrl = avatarUrl;
    }

}