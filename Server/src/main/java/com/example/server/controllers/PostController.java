package com.example.server.controllers;

import com.example.server.model.Post;
import com.example.server.model.User;
import com.example.server.repository.PostRepository;
import com.example.server.services.post.impls.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    PostServiceImpl postService;

    @GetMapping("/")
    public ResponseEntity<List<Post>> getAllPosts()  {
        return new ResponseEntity<>(postService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> gePostById(@PathVariable("id") int id)  {
        return new ResponseEntity<>(postService.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Post> savePost(@RequestBody Post post)  {
        Post savedPost = postService.save(post);
        if(savedPost==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(savedPost, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable("id") int id)  {
        postService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/")
    public ResponseEntity<Post> updatePost(@RequestBody Post post)  {
        Post updatedPost = postService.update(post);
        if(updatedPost==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

}
