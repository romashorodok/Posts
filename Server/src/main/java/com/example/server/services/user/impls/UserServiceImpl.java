package com.example.server.services.user.impls;

import com.example.server.dto.UserDTO;
import com.example.server.mappers.UserMapper;
import com.example.server.model.User;
import com.example.server.repository.UserRepository;
import com.example.server.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDTO save(UserDTO user) {
        if(user.getId()!=null){
            return null;
        }
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(new User(), user)));
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO getOne(int id) {
        return userRepository.findById(id).map(userMapper::toDTO).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO update(UserDTO user) {
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(userRepository.findById(user.getId()).orElseThrow(NoSuchElementException::new), user)));
    }
}
