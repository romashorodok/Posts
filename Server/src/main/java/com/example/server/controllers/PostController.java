package com.example.server.controllers;

import com.example.server.dto.PostDTO;
import com.example.server.dto.RecentPostDTO;
import com.example.server.dto.ViewPostDTO;
import com.example.server.services.post.impls.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostServiceImpl postService;

    @GetMapping("/")
    public ResponseEntity<List<ViewPostDTO>> getAllPosts()  {
        return new ResponseEntity<>(postService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ViewPostDTO> getPostById(@PathVariable("id") int id)  {
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

    @PostMapping(value = "/")
    public ResponseEntity<PostDTO> savePost(@RequestPart PostDTO post, @RequestParam MultipartFile file) throws IOException {
        PostDTO savedPost = postService.save(post, file);
        if(savedPost==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(savedPost, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable("id") int id) throws IOException {
        postService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/")
    public ResponseEntity<PostDTO> updatePost(@RequestPart PostDTO post, @RequestParam MultipartFile file) throws IOException {
        return new ResponseEntity<>(postService.update(post, file), HttpStatus.OK);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<RecentPostDTO>> getRecentPosts(@RequestParam String tag,@RequestParam int size){
            return new ResponseEntity<>(postService.getRecentPosts(tag, size), HttpStatus.OK);
    }
    @GetMapping("/most-liked")
    public ResponseEntity<RecentPostDTO> getMostLikePost() throws IOException {
        return new ResponseEntity<>(postService.getMostLikedPost(), HttpStatus.OK);
    }

    @GetMapping("/paging")
    public ResponseEntity<List<PostDTO>> getPageableAsList( @RequestParam Integer page, @RequestParam Integer size){
        return new ResponseEntity<>(postService.getPostByPageListed(page, size), HttpStatus.OK);
    }
}
