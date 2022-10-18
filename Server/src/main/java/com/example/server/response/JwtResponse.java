package com.example.server.response;

import lombok.Data;

import java.util.List;
@Data
public class JwtResponse {
    private String accessToken;
    private String refreshToken;

    private Integer id;
    private String username;
    private List<String> roles;

    public JwtResponse(String accessToken, String refreshToken,Integer id, String username, List<String> roles) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}
