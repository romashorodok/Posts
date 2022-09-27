package com.example.server.services.role.impls;

import com.example.server.model.Role;
import com.example.server.repository.RoleRepository;
import com.example.server.services.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        if(role.getId()!=null){
            return null;
        }
        return roleRepository.save(role);
    }

    @Override
    public void delete(int id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role getOne(int id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role update(Role role) {
        if(role.getId()==null){
            return null;
        }
        return roleRepository.save(role);
    }
}
