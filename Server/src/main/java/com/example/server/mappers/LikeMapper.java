package com.example.server.mappers;

import com.example.server.dto.LikeDTO;
import com.example.server.dto.UserDTO;
import com.example.server.model.Like;
import com.example.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LikeMapper {
    @Autowired
    UserMapper userMapper;


    public LikeDTO toDTO(Like like){
        LikeDTO dto = new LikeDTO();
        dto.setId(like.getId());
        dto.setUser(userMapper.toDTO(like.getUser()));
        return dto;
    }
    public Like toEntity(Like like, LikeDTO dto){
        like.setId(dto.getId());
        like.setUser(userMapper.toEntity(new User(), dto.getUser()));
        return like;
    }
}
