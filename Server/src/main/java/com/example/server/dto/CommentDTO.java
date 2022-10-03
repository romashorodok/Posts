package com.example.server.dto;


import lombok.Data;
import java.util.Date;
import java.util.Set;

@Data
public class CommentDTO {
    private Integer id;
    private UserDTO user;
    private String content;
    private Date createdAt;

    private Set<LikeDTO> likes;

    public CommentDTO() {
    }

    public CommentDTO(int id, UserDTO user, String content, Date createdAt, Set<LikeDTO> likes) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.createdAt = createdAt;

        this.likes = likes;
    }

    public CommentDTO(UserDTO user, String content, Set<LikeDTO> likes) {
        this.user = user;
        this.content = content;
        this.likes = likes;
    }
}