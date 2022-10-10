package com.example.server.dto;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class RecentPostDTO {
    private Integer id;
    private String title;
    private Set<TagDTO> tags;
    private String description;
    private Date createdAt;
    private String imageUrl;


    public RecentPostDTO() {
    }

    public RecentPostDTO(Integer id, String title, Set<TagDTO> tags, String description, Date createdAt, String imageUrl) {
        this.id = id;
        this.title = title;
        this.tags = tags;
        this.description = description;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
    }
}
