package com.example.server.controllers;

import com.example.server.model.Like;
import com.example.server.services.like.impls.LikeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikeController {
    @Autowired
    LikeServiceImpl likeService;


    @GetMapping("/")
    public ResponseEntity<List<Like>> getAllLikes()  {
        return new ResponseEntity<>(likeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Like> getLikeById(@PathVariable("id") int id)  {
        return new ResponseEntity<>(likeService.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Like> saveLike(@RequestBody Like like)  {
        Like savedLike = likeService.save(like);
        if(savedLike==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(savedLike, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteLike(@PathVariable("id") int id)  {
        likeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/")
    public ResponseEntity<Like> updateLike(@RequestBody Like like)  {
        Like updatedLike = likeService.update(like);
        if(updatedLike==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updatedLike, HttpStatus.OK);
    }
}
