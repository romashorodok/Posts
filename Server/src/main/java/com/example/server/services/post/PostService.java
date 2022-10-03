package com.example.server.services.post;


import com.example.server.dto.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO save(PostDTO post);
    void delete(int id);
    PostDTO getOne(int id);
    List<PostDTO> getAll();
    PostDTO update(PostDTO post);
}
