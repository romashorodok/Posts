package com.example.server.mappers;

import com.example.server.dto.CommentDTO;
import com.example.server.model.Comment;
import com.example.server.model.Like;
import com.example.server.model.Post;
import com.example.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CommentMapper {
    @Autowired
    UserMapper userMapper;
    @Autowired
    LikeMapper likeMapper;
    @Lazy
    @Autowired
    PostMapper postMapper;

    public CommentDTO toDTO(Comment comment){
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setUser(userMapper.toDTO(comment.getUser()));
        dto.setContent(comment.getContent());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setLikes(comment.getLikes().stream().map(elem -> likeMapper.toDTO(elem)).collect(Collectors.toSet()));
        dto.setPost(postMapper.toCommonPostDTO(comment.getPost()));
        return dto;
    }

    public Comment toEntity(Comment comment, CommentDTO dto){
        comment.setId(dto.getId());
        comment.setUser(userMapper.toEntity(new User(), dto.getUser()));
        comment.setContent(dto.getContent());
        comment.setCreatedAt(dto.getCreatedAt());
        comment.setLikes(dto.getLikes().stream().map(elem -> likeMapper.toEntity(new Like(), elem)).collect(Collectors.toSet()));
        comment.setPost(postMapper.toEntity(new Post(), dto.getPost()));
        return comment;
    }

}
