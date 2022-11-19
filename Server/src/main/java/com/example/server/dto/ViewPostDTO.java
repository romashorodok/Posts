package com.example.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewPostDTO {
    private Integer id;
    private String title;
    private Set<TagDTO> tags;
    private List<CommentDTO> comments;
    private String description;
    private ProfileDTO user;
    private Set<LikeDTO> likes;
    private Date createdAt;
    private byte[] image;
}
