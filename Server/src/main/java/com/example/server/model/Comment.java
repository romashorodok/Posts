package com.example.server.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String content;
    private int likeCount;
    private LocalDateTime createdAt;

    public Comment() {
    }

    public Comment(String id, User user, String content, int likeCount, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
    }

    public Comment(User user, String content, int likeCount) {
        this.user = user;
        this.content = content;
        this.likeCount = likeCount;
    }
}
