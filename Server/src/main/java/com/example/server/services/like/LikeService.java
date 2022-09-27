package com.example.server.services.like;

import com.example.server.model.Like;

import java.util.List;

public interface LikeService {
    Like save(Like like);
    void delete(int id);
    Like getOne(int id);
    List<Like> getAll();
    Like update(Like like);
}
