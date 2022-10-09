package com.example.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String content;
    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToMany
    @JoinTable(
            name = "comment_likes",
            joinColumns = @JoinColumn(name="comment_id"),
            inverseJoinColumns = @JoinColumn(name = "like_id")
    )
    private Set<Like> likes;

    public Comment() {
    }

    public Comment(int id, User user, String content, Date createdAt, Set<Like> likes, Post post) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.createdAt = createdAt;
        this.likes = likes;
        this.post = post;
    }

    public Comment(User user, String content, Set<Like> likes, Post post) {
        this.user = user;
        this.content = content;
        this.likes = likes;
        this.post = post;

    }
}
