package com.example.server.services.role.impls;

import com.example.server.dto.RoleDTO;
import com.example.server.mappers.RoleMapper;
import com.example.server.model.Role;
import com.example.server.repository.RoleRepository;
import com.example.server.services.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository repository;
    @Autowired
    RoleMapper mapper;

    @Override
    public RoleDTO save(RoleDTO role) {
        if(role.getId()!=null){
            return null;
        }
        return mapper.toDTO(repository.save(mapper.toEntity(new Role(), role)));
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public RoleDTO getOne(int id) {
        return repository.findById(id).map(mapper::toDTO).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<RoleDTO> getAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public RoleDTO update(RoleDTO role) {
        return mapper.toDTO(repository.save(mapper.toEntity(repository.findById(role.getId()).orElseThrow(NoSuchElementException::new), role)));
    }
}
