package com.example.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @ManyToMany(cascade = CascadeType.MERGE)
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
            joinColumns = @JoinColumn(name="post_id"),
            inverseJoinColumns = @JoinColumn(name = "like_id")
    )
    private Set<Like> likes;
    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;


    public Post() {
    }

    public Post(String title, int id, Set<Tag> tags, List<Comment> comments, String description, User user, Set<Like> likes, Date createdAt) {
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
