package com.example.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private byte[] avatar;
    private PageDTO<RecentPostDTO> posts;
}
