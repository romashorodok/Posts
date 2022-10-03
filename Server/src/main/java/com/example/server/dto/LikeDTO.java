package com.example.server.dto;

import lombok.Data;

@Data
public class LikeDTO {

    private Integer id;
    private UserDTO user;

    public LikeDTO(int id, UserDTO user) {
        this.id = id;
        this.user = user;
    }

    public LikeDTO() {
    }
}
