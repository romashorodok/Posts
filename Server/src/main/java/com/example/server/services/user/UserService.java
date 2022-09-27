package com.example.server.services.user;

import com.example.server.model.User;

import java.util.List;

public interface UserService {
    User save(User user);
    void delete(int id);
    User getOne(int id);
    List<User> getAll();
    User update(User user);
}
