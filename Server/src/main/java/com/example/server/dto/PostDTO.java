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

    public PostDTO() {
    }

    public PostDTO(String title, int id, Set<TagDTO> tags, List<CommentDTO> comments, String description, UserDTO user, Set<LikeDTO> likes, Date createdAt) {
        this.title = title;
        this.id = id;
        this.tags = tags;
        this.comments = comments;
        this.description = description;
        this.user = user;
        this.likes = likes;
        this.createdAt = createdAt;
    }

    public PostDTO(String title, Set<TagDTO> tags, List<CommentDTO> comments, String description, UserDTO user, Set<LikeDTO> likes) {
        this.title = title;
        this.tags = tags;
        this.comments = comments;
        this.description = description;
        this.user = user;
        this.likes = likes;
    }
}