package com.example.server.dto;


import com.example.server.model.Post;
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
    private PostDTO post;

    public CommentDTO() {
    }

    public CommentDTO(int id, UserDTO user, String content, Date createdAt, Set<LikeDTO> likes, PostDTO post) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.createdAt = createdAt;
        this.post = post;
        this.likes = likes;
    }

    public CommentDTO(UserDTO user, String content, Set<LikeDTO> likes, PostDTO post) {
        this.user = user;
        this.content = content;
        this.likes = likes;
        this.post = post;

    }
}