package com.example.server.controllers;

import com.example.server.dto.CommentDTO;
import com.example.server.dto.CommentSaveDTO;
import com.example.server.services.comment.impls.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentServiceImpl commentService;


    @GetMapping("/")
    public ResponseEntity<List<CommentDTO>> getAllComments()  {
        return new ResponseEntity<>(commentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable("id") int id)  {
        return new ResponseEntity<>(commentService.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CommentSaveDTO> saveComment(@RequestBody CommentSaveDTO comment)  {
        CommentSaveDTO savedComment = commentService.save(comment);
        if(savedComment==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(savedComment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") int id)  {
        commentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/")
    public ResponseEntity<CommentSaveDTO> updateComment(@RequestBody CommentSaveDTO comment)  {
        return new ResponseEntity<>(commentService.update(comment), HttpStatus.OK);
    }
}
