package com.example.server.dto;

import lombok.Data;

@Data
public class ProfileDTO {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private byte[] avatar;

    public ProfileDTO(Integer id, String email, String firstName, String lastName, byte[] avatar) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    public ProfileDTO() {
    }
}
