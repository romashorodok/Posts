package com.example.server.services.role;

import com.example.server.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    RoleDTO save(RoleDTO role);
    void delete(int id);
    RoleDTO getOne(int id);
    List<RoleDTO> getAll();
    RoleDTO update(RoleDTO role);

}
