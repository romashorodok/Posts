package com.example.server.dto;

import lombok.Data;

@Data
public class RoleDTO {
    private Integer id;
    private String name;

    public RoleDTO() {
    }

    public RoleDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
}