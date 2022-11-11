package com.example.server.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;
@Data
public class ViewPostDTO {

    private Integer id;
    private String title;

    private Set<TagDTO> tags;

    private List<CommentDTO> comments;
    private String description;

    private UserDTO user;

    private Set<LikeDTO> likes;

    private Date createdAt;
    private byte[] image;

    public ViewPostDTO() {
    }

    public ViewPostDTO(String title, int id, Set<TagDTO> tags, List<CommentDTO> comments, String description, UserDTO user, Set<LikeDTO> likes, Date createdAt, byte[] image) {
        this.title = title;
        this.id = id;
        this.tags = tags;
        this.comments = comments;
        this.description = description;
        this.user = user;
        this.likes = likes;
        this.createdAt = createdAt;
        this.image = image;
    }

    public ViewPostDTO(String title, Set<TagDTO> tags, List<CommentDTO> comments, String description, UserDTO user, Set<LikeDTO> likes, byte[] image) {
        this.title = title;
        this.tags = tags;
        this.comments = comments;
        this.description = description;
        this.user = user;
        this.likes = likes;
        this.image = image;
    }
}
