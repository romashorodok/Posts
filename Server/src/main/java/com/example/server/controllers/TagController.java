package com.example.server.controllers;

import com.example.server.dto.TagDTO;
import com.example.server.services.tag.impls.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {
    @Autowired
    TagServiceImpl tagService;


    @GetMapping("/")
    public ResponseEntity<List<TagDTO>> getAllTags()  {
        return new ResponseEntity<>(tagService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDTO> getTagById(@PathVariable("id") int id)  {
        return new ResponseEntity<>(tagService.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<TagDTO> saveTag(@RequestBody TagDTO tag)  {
        TagDTO savedTag = tagService.save(tag);
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
    public ResponseEntity<TagDTO> updateTag(@RequestBody TagDTO tag)  {
        return new ResponseEntity<>(tagService.update(tag), HttpStatus.OK);
    }
}
