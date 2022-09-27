package com.example.server.services.role;

import com.example.server.model.Role;

import java.util.List;

public interface RoleService {
    Role save(Role role);
    void delete(int id);
    Role getOne(int id);
    List<Role> getAll();
    Role update(Role role);

}
