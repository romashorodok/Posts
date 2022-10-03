package com.example.server.services.post.impls;

import com.example.server.dto.PostDTO;
import com.example.server.mappers.PostMapper;
import com.example.server.model.Post;
import com.example.server.repository.PostRepository;
import com.example.server.services.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository repository;
    @Autowired
    PostMapper mapper;

    @Override
    public PostDTO save(PostDTO post) {
        if(post.getId()!=null){
            return null;
        }
        return mapper.toDTO(repository.save(mapper.toEntity(new Post(), post)));
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public PostDTO getOne(int id) {
        return repository.findById(id).map(mapper::toDTO).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<PostDTO> getAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public PostDTO update(PostDTO post) {
        return mapper.toDTO(repository.save(mapper.toEntity(repository.findById(post.getId()).orElseThrow(NoSuchElementException::new), post)));
    }
}
