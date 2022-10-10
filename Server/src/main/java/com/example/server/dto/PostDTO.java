package com.example.server.dto;


import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class PostDTO {

    private Integer id;
    private String title;

    private Set<TagDTO> tags;

    private List<CommentDTO> comments;
    private String description;

    private UserDTO user;

    private Set<LikeDTO> likes;

    private Date createdAt;
    private String imageUrl;

    public PostDTO() {
    }

    public PostDTO(String title, int id, Set<TagDTO> tags, List<CommentDTO> comments, String description, UserDTO user, Set<LikeDTO> likes, Date createdAt, String imageUrl) {
        this.title = title;
        this.id = id;
        this.tags = tags;
        this.comments = comments;
        this.description = description;
        this.user = user;
        this.likes = likes;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
    }

    public PostDTO(String title, Set<TagDTO> tags, List<CommentDTO> comments, String description, UserDTO user, Set<LikeDTO> likes, String imageUrl) {
        this.title = title;
        this.tags = tags;
        this.comments = comments;
        this.description = description;
        this.user = user;
        this.likes = likes;
        this.imageUrl = imageUrl;
    }
}