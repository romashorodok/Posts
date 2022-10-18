package com.example.server.services.user.impls;

import com.example.server.dto.UserDTO;
import com.example.server.mappers.UserMapper;
import com.example.server.model.User;
import com.example.server.repository.UserRepository;
import com.example.server.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder encoder;

    @Override
    public UserDTO save(UserDTO user) {
        if(user.getId()!=null){
            return null;
        }
        user.setPassword(encoder.encode(user.getPassword()));
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
        user.setPassword(encoder.encode(user.getPassword()));
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(userRepository.findById(user.getId()).orElseThrow(NoSuchElementException::new), user)));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(NoSuchElementException::new);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE"+role));
        });
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
