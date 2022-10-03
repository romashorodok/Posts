package com.example.server.controllers;

import com.example.server.dto.RoleDTO;
import com.example.server.services.role.impls.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    RoleServiceImpl roleService;


    @GetMapping("/")
    public ResponseEntity<List<RoleDTO>> getAllRoles()  {
        return new ResponseEntity<>(roleService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable("id") int id)  {
        return new ResponseEntity<>(roleService.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<RoleDTO> saveRole(@RequestBody RoleDTO role)  {
        RoleDTO savedRole = roleService.save(role);
        if(savedRole==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(savedRole, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRole(@PathVariable("id") int id)  {
        roleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/")
    public ResponseEntity<RoleDTO> updateRole(@RequestBody RoleDTO role)  {
        return new ResponseEntity<>(roleService.update(role), HttpStatus.OK);
    }
}
