package com.example.server.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "posts")
public class Post {
    private String title;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;
    @OneToMany
    @JoinColumn(name = "post_id")
    private List<Comment> comments;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany
    @JoinTable(
            name = "post_likes",
            joinColumns = @JoinColumn(name="like_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private Set<Like> likes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Post() {
    }

    public Post(String title, int id, Set<Tag> tags, List<Comment> comments, String description, User user, Set<Like> likes, LocalDateTime createdAt) {
        this.title = title;
        this.id = id;
        this.tags = tags;
        this.comments = comments;
        this.description = description;
        this.user = user;
        this.likes = likes;
        this.createdAt = createdAt;
    }

    public Post(String title, Set<Tag> tags, List<Comment> comments, String description, User user, Set<Like> likes) {
        this.title = title;
        this.tags = tags;
        this.comments = comments;
        this.description = description;
        this.user = user;
        this.likes = likes;
    }
}
