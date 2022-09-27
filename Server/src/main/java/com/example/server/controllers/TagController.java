package com.example.server.controllers;

import com.example.server.model.Tag;
import com.example.server.services.tag.impls.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    @Autowired
    TagServiceImpl tagService;


    @GetMapping("/")
    public ResponseEntity<List<Tag>> getAllTags()  {
        return new ResponseEntity<>(tagService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable("id") int id)  {
        return new ResponseEntity<>(tagService.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Tag> saveTag(@RequestBody Tag tag)  {
        Tag savedTag = tagService.save(tag);
        if(savedTag==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(savedTag, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTag(@PathVariable("id") int id)  {
        tagService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/")
    public ResponseEntity<Tag> updateTag(@RequestBody Tag tag)  {
        Tag updatedTag = tagService.update(tag);
        if(updatedTag==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updatedTag, HttpStatus.OK);
    }
}
