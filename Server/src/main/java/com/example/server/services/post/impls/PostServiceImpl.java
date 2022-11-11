package com.example.server.services.post.impls;

import com.example.server.dto.PostDTO;
import com.example.server.dto.RecentPostDTO;
import com.example.server.dto.ViewPostDTO;
import com.example.server.mappers.PostMapper;
import com.example.server.model.Post;
import com.example.server.model.User;
import com.example.server.repository.PostRepository;
import com.example.server.services.post.PostService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository repository;
    @Autowired
    PostMapper mapper;

    @Override
    public PostDTO save(PostDTO post, MultipartFile file) throws IOException{
        if(post.getId()!=null){
            return null;
        }
        String filename = UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        Files.write(Paths.get( "Server/src/main/resources/images/" + filename), file.getBytes());
        post.setImageUrl(filename);
        post.setCreatedAt(new Date());
        return mapper.toCommonPostDTO(repository.save(mapper.toEntity(new Post(), post)));
    }

    @Override
    public void delete(int id) throws IOException {
        Files.deleteIfExists(
                Paths.get("Server/src/main/resources/images/" +  repository.findById(id).orElseThrow(NoSuchElementException::new).getImageUrl()));
        repository.deleteById(id);
    }

    @Override
    public ViewPostDTO getOne(int id) {
        return repository.findById(id).map(elem -> {
            try {
                return mapper.toViewPostDTO(elem);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<ViewPostDTO> getAll() {
        return repository.findAll().stream().map(elem -> {
            try {
                return mapper.toViewPostDTO(elem);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    @Override
    public PostDTO update(PostDTO post, MultipartFile file) throws IOException {
        Post post1 = repository.findById(post.getId()).orElseThrow(NoSuchElementException::new);
        if(!file.isEmpty()){
            if(!Objects.equals(post1.getImageUrl(), "")){
            Files.deleteIfExists(
                    Paths.get("Server/src/main/resources/images/" + post1.getImageUrl()));
            }
            String filename = UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
            Files.write(Paths.get( "Server/src/main/resources/images/" + filename), file.getBytes());
            post.setImageUrl(filename);
        }
        else{
            post.setImageUrl(post1.getImageUrl());
        }
        post.setCreatedAt(post1.getCreatedAt());
        return mapper.toDTO(repository.save(mapper.toEntity(post1, post)));
    }

    public List<RecentPostDTO> getRecentPosts(String tag, int size){
        return repository.findAllRecentByTags(tag, PageRequest.of(0, size)).stream().map(elem -> {
            try {
                return mapper.toRecentPostDTO(elem);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public RecentPostDTO getMostLikedPost() throws IOException {
        return mapper.toRecentPostDTO(repository.findMostLiked());
    }



}
