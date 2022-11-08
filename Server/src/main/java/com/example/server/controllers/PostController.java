package com.example.server.controllers;

import com.example.server.dto.PostDTO;
import com.example.server.dto.RecentPostDTO;
import com.example.server.services.post.impls.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostServiceImpl postService;

    @GetMapping("/")
    public ResponseEntity<List<PostDTO>> getAllPosts()  {
        return new ResponseEntity<>(postService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("id") int id)  {
        return new ResponseEntity<>(postService.getOne(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostDTO>> getByUsername(@RequestParam("title") String title){
        return new ResponseEntity<>(postService.getByUsername(title), HttpStatus.OK);
    }
    @GetMapping("/sort-by-likes")
    public ResponseEntity<List<PostDTO>> sortByLikes(){
        return new ResponseEntity<>(postService.getAllOrderedByLikes(), HttpStatus.OK);
    }

    @GetMapping("/find-by-tag")
    public ResponseEntity<List<PostDTO>> findByTag(@RequestParam("tag") String tag){
        return new ResponseEntity<>(postService.findByTag(tag), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<PostDTO> savePost(@RequestBody PostDTO post)  {
        PostDTO savedPost = postService.save(post);
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
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO post)  {
        return new ResponseEntity<>(postService.update(post), HttpStatus.OK);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<RecentPostDTO>> getRecentPosts(@RequestParam String tag,@RequestParam int size){
            return new ResponseEntity<>(postService.getRecentPosts(tag, size), HttpStatus.OK);
    }
    @GetMapping("/most-liked")
    public ResponseEntity<RecentPostDTO> getMostLikePost(){
        return new ResponseEntity<>(postService.getMostLikedPost(), HttpStatus.OK);
    }
}
