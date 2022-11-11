package com.example.server.services.user;

import com.example.server.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    UserDTO save(UserDTO user, MultipartFile file) throws IOException;
    void delete(int id) throws IOException;
    UserDTO getOne(int id);
    List<UserDTO> getAll();
    UserDTO update(UserDTO user, MultipartFile file) throws IOException;
}
