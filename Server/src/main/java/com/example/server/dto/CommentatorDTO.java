package com.example.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentatorDTO {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private byte[] avatar;
}
