package com.example.server.services.post;


import com.example.server.model.Post;
import java.util.List;

public interface PostService {
    Post save(Post post);
    void delete(int id);
    Post getOne(int id);
    List<Post> getAll();
    Post update(Post post);
}
