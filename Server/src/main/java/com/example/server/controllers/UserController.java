package com.example.server.controllers;

import com.example.server.dto.ProfileDTO;
import com.example.server.dto.UserDTO;
import com.example.server.services.user.impls.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<List<ProfileDTO>> getAllProfiles() {
        return new ResponseEntity<>(userService.getProfilesAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") int id) {
        return new ResponseEntity<>(userService.getOne(id), HttpStatus.OK);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<ProfileDTO> getProfileById(@PathVariable("id") int id) {
        return new ResponseEntity<>(userService.getOneProfileById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> saveUser(@RequestPart UserDTO user, @RequestPart(required = false) MultipartFile file) throws IOException {
        UserDTO savedUser = userService.save(user, file);

        // TODO: remove it
        if (savedUser == null) {
            return ResponseEntity.internalServerError().body(new HashMap<>() {{
                put("error", new HashMap<>() {{
                    put("email", new ArrayList<>() {{
                        add("Email already exists");
                    }});
                }});
            }});
        }

        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) throws IOException {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/")
    public ResponseEntity<UserDTO> updateUser(@RequestPart UserDTO user, @RequestPart(required = false) MultipartFile file) throws IOException {
        return new ResponseEntity<>(userService.update(user, file), HttpStatus.OK);
    }
}
