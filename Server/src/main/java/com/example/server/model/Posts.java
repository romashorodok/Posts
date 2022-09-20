package com.example.server.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String title;
    @ManyToMany
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tags> tags;
    @OneToMany
    @JoinColumn(name = "post_id")
    private List<Comment> comments;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private int likeCount;
    private String content;

    public Posts() {
    }

    public Posts(String id, String title, Set<Tags> tags, List<Comment> comments, String description, User user, int likeCount, String content) {
        this.id = id;
        this.title = title;
        this.tags = tags;
        this.comments = comments;
        this.description = description;
        this.user = user;
        this.likeCount = likeCount;
        this.content = content;
    }

    public Posts(String title, Set<Tags> tags, List<Comment> comments, String description, User user, int likeCount, String content) {
        this.title = title;
        this.tags = tags;
        this.comments = comments;
        this.description = description;
        this.user = user;
        this.likeCount = likeCount;
        this.content = content;
    }
}
