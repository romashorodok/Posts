package com.example.server.mappers;


import com.example.server.dto.PostDTO;
import com.example.server.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        return dto;
    }

    public Post toEntity(Post post, PostDTO dto){
        post.setId(dto.getId());
        post.setTitle(dto.getTitle());
        post.setTags(dto.getTags().stream().map(elem -> tagMapper.toEntity(new Tag(), elem)).collect(Collectors.toSet()));
        post.setDescription(dto.getDescription());
        post.setUser(userMapper.toEntity(new User(), dto.getUser()));
        post.setLikes(dto.getLikes().stream().map(elem -> likeMapper.toEntity(new Like(), elem)).collect(Collectors.toSet()));
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
        return dto;
    }
}
