package com.example.server.dto;

import lombok.Data;


@Data
public class TagDTO {
    private Integer id;
    private String name;

    public TagDTO() {
    }

    public TagDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
}