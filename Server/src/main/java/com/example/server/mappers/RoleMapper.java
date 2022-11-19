package com.example.server.mappers;


import com.example.server.dto.RoleDTO;
import com.example.server.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toEntity(RoleDTO role);
    RoleDTO toDTO(Role role);
    Role toEntity(@MappingTarget Role role, RoleDTO roleDTO);
}
