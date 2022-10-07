package com.example.server.mappers;

import com.example.server.dto.UserDTO;
import com.example.server.model.Role;
import com.example.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class UserMapper {
    @Autowired
    RoleMapper roleMapper;

    public UserDTO toDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setRole(user.getRole().stream().map(elem -> roleMapper.toDTO(elem)).collect(Collectors.toSet()));
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setAvatarUrl(user.getAvatarUrl());
        return dto;
    }
    public User toEntity(User user, UserDTO dto){
        user.setId(dto.getId());
        user.setRole(dto.getRole().stream().map(elem -> roleMapper.toEntity(new Role(), elem)).collect(Collectors.toSet()));
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setAvatarUrl(dto.getAvatarUrl());
        return user;
    }
}