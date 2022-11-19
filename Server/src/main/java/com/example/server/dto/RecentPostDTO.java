package com.example.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecentPostDTO {
    private Integer id;
    private String title;
    private Set<TagDTO> tags;
    private String description;
    private Date createdAt;
    private byte[] image;
}
