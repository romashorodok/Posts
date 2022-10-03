package com.example.server.services.user;

import com.example.server.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO save(UserDTO user);
    void delete(int id);
    UserDTO getOne(int id);
    List<UserDTO> getAll();
    UserDTO update(UserDTO user);
}
