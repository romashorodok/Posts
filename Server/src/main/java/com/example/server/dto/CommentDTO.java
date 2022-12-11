package com.example.server.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Integer id;
    private CommentatorDTO user;
    private String content;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createdAt;
    private Set<LikeDTO> likes = new HashSet<>();
    private IdDTO post;
}