package com.example.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
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


    @ManyToMany
    @JoinTable(
            name = "post_likes",
            joinColumns = @JoinColumn(name="like_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id")
    )
    private Set<Like> likes;

    public Comment() {
    }

    public Comment(int id, User user, String content, Date createdAt, Set<Like> likes) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.createdAt = createdAt;
        this.likes = likes;
    }

    public Comment(User user, String content, Set<Like> likes) {
        this.user = user;
        this.content = content;
        this.likes = likes;
    }
}
