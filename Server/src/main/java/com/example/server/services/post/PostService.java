package com.example.server.services.post;


import com.example.server.dto.PostDTO;
import com.example.server.dto.ViewPostDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    PostDTO save(PostDTO post, MultipartFile file) throws IOException;
    void delete(int id) throws IOException;
    ViewPostDTO getOne(int id);
    List<ViewPostDTO> getAll();
    PostDTO update(PostDTO post, MultipartFile file) throws IOException;
}
