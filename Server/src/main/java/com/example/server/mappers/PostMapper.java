package com.example.server.mappers;


import com.example.server.dto.PostDTO;
import com.example.server.dto.RecentPostDTO;
import com.example.server.dto.ViewPostDTO;
import com.example.server.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Component
public class PostMapper {
    @Autowired
    TagMapper tagMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    LikeMapper likeMapper;

    public PostDTO toDTO(Post post){
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setTags(post.getTags().stream().map(elem -> tagMapper.toDTO(elem)).collect(Collectors.toSet()));
        dto.setComments(post.getComments().stream().map(elem -> commentMapper.toDTO(elem)).collect(Collectors.toList()));
        dto.setDescription(post.getDescription());
        dto.setUser(userMapper.toDTO(post.getUser()));
        dto.setLikes(post.getLikes().stream().map(elem -> likeMapper.toDTO(elem)).collect(Collectors.toSet()));
        dto.setCreatedAt(post.getCreatedAt());
        dto.setImageUrl(post.getImageUrl());
        return dto;
    }
    public ViewPostDTO toViewPostDTO(Post post) throws IOException {
        ViewPostDTO dto = new ViewPostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setTags(post.getTags().stream().map(elem -> tagMapper.toDTO(elem)).collect(Collectors.toSet()));
        dto.setComments(post.getComments().stream().map(elem -> commentMapper.toDTO(elem)).collect(Collectors.toList()));
        dto.setDescription(post.getDescription());
        dto.setUser(userMapper.toDTO(post.getUser()));
        dto.setLikes(post.getLikes().stream().map(elem -> likeMapper.toDTO(elem)).collect(Collectors.toSet()));
        dto.setCreatedAt(post.getCreatedAt());
        if(!post.getImageUrl().equals("")){
            if(Files.exists(Paths.get("Server/src/main/resources/images/" + post.getImageUrl()))){
                dto.setImage(Base64Utils.decode(Files.readAllBytes(Paths.get("Server/src/main/resources/images/" + post.getImageUrl()))));
            }
        }
        return dto;
    }
    public Post toEntity(Post post, PostDTO dto){
        post.setId(dto.getId());
        post.setTitle(dto.getTitle());
        post.setTags(dto.getTags().stream().map(elem -> tagMapper.toEntity(new Tag(), elem)).collect(Collectors.toSet()));
        post.setDescription(dto.getDescription());
        post.setUser(userMapper.toEntity(new User(), dto.getUser()));
        post.setLikes(dto.getLikes().stream().map(elem -> likeMapper.toEntity(new Like(), elem)).collect(Collectors.toSet()));
        post.setImageUrl(dto.getImageUrl());
        post.setCreatedAt(dto.getCreatedAt());
        return post;
    }

    public PostDTO toCommonPostDTO(Post post){
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setTags(post.getTags().stream().map(elem -> tagMapper.toDTO(elem)).collect(Collectors.toSet()));
        dto.setDescription(post.getDescription());
        dto.setUser(userMapper.toDTO(post.getUser()));
        dto.setLikes(post.getLikes().stream().map(elem -> likeMapper.toDTO(elem)).collect(Collectors.toSet()));
        dto.setCreatedAt(post.getCreatedAt());
        dto.setImageUrl(post.getImageUrl());
        return dto;
    }

    public RecentPostDTO toRecentPostDTO(Post post) throws IOException {
        RecentPostDTO dto = new RecentPostDTO();
        dto.setId(post.getId());
        dto.setDescription(post.getDescription());
        dto.setTitle(post.getTitle());
        dto.setTags(post.getTags().stream().map(elem -> tagMapper.toDTO(elem)).collect(Collectors.toSet()));
        dto.setCreatedAt(post.getCreatedAt());
        if(!post.getImageUrl().equals("")){
            if(Files.exists(Paths.get("Server/src/main/resources/images/" + post.getImageUrl()))){
                dto.setImage(Files.readAllBytes(Paths.get("Server/src/main/resources/images/" + post.getImageUrl())));
            }
        }
        return dto;
    }
}
