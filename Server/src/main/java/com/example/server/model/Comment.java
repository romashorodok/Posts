package com.example.server.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String content;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @OneToMany
    @JoinColumn(name = "post_id")
    private List<Post> posts;

    @ManyToMany
    @JoinTable(
            name = "post_likes",
            joinColumns = @JoinColumn(name="like_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id")
    )
    private Set<Like> likes;

    public Comment() {
    }

    public Comment(int id, User user, String content, LocalDateTime createdAt, List<Post> posts, Set<Like> likes) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.createdAt = createdAt;
        this.posts = posts;
        this.likes = likes;
    }

    public Comment(User user, String content, List<Post> posts, Set<Like> likes) {
        this.user = user;
        this.content = content;
        this.posts = posts;
        this.likes = likes;
    }
}
