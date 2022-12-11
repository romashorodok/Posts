package com.example.server.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private Set<RoleDTO> role = new HashSet<>();
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 8, max = 32, message = "Password should contain from 8 to 32 characters")
    private String password;
    @Email(message = "Bad email format")
    private String email;
    private String firstName;
    private String lastName;
    private String avatarUrl;
}