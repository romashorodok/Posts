package com.example.server.controllers;

import com.example.server.dto.UserDTO;
import com.example.server.services.user.impls.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserServiceImpl userService;


    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers()  {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> geUserById(@PathVariable("id") int id)  {
        return new ResponseEntity<>(userService.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO user)  {
        UserDTO savedUser = userService.save(user);
        if(savedUser==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id)  {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO user)  {
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }
}
