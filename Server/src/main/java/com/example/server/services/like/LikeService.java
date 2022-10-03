package com.example.server.services.like;


import com.example.server.dto.LikeDTO;

import java.util.List;

public interface LikeService {
    LikeDTO save(LikeDTO like);
    void delete(int id);
    LikeDTO getOne(int id);
    List<LikeDTO> getAll();
    LikeDTO update(LikeDTO like);
}
