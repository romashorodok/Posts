package com.example.server.mappers;


import com.example.server.dto.RoleDTO;
import com.example.server.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleDTO toDTO(Role role){
        RoleDTO dto = new RoleDTO();
        dto.setId(role.getId());
        dto.setName(role.getName());
        return dto;
    }
    public Role toEntity(Role role, RoleDTO dto){
        role.setId(dto.getId());
        role.setName(dto.getName());
        return role;
    }
}
