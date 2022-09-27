package com.example.server.services.post.impls;

import com.example.server.model.Post;
import com.example.server.repository.PostRepository;
import com.example.server.services.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Override
    public Post save(Post post) {
        if(post.getId()!=null){
            return null;
        }
        return postRepository.save(post);
    }

    @Override
    public void delete(int id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post getOne(int id) {
        return postRepository.findById(id).get();
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public Post update(Post post) {
        if(post.getId()==null){
            return null;
        }
        return postRepository.save(post);
    }
}
