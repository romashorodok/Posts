package com.example.server.services.user.impls;

import com.example.server.dto.ProfileDTO;
import com.example.server.dto.UserDTO;
import com.example.server.mappers.UserMapper;
import com.example.server.model.User;
import com.example.server.repository.UserRepository;
import com.example.server.services.user.UserService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
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
    public UserDTO save(UserDTO user, MultipartFile file) throws IOException {
        if(user.getId()!=null){
            return null;
        }
        String filename = UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        Files.write(Paths.get( "Server/src/main/resources/images/" + filename), file.getBytes());
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAvatarUrl(filename);
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(new User(), user)));
    }

    @Override
    public void delete(int id) throws IOException {
        Files.deleteIfExists(
                Paths.get("Server/src/main/resources/images/" +  userRepository.findById(id).orElseThrow(NoSuchElementException::new).getAvatarUrl()));
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

    public List<ProfileDTO> getProfilesAll() {
        return userRepository.findAll().stream().map(elem -> {
            try {
                return userMapper.toProfileDTO(elem);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public ProfileDTO getOneProfileById(int id){
        return userRepository.findById(id).map(elem -> {
            try {
                return userMapper.toProfileDTO(elem);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        ).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public UserDTO update(UserDTO user, MultipartFile file) throws IOException {
        User user1 = userRepository.findById(user.getId()).orElseThrow(NoSuchElementException::new);
        if(!file.isEmpty()){
            Files.deleteIfExists(
                    Paths.get("Server/src/main/resources/images/" + user1.getAvatarUrl()));
            String filename = UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
            Files.write(Paths.get( "Server/src/main/resources/images/" + filename), file.getBytes());
            user.setAvatarUrl(filename);
            user.setPassword(encoder.encode(user.getPassword()));
        }
        else{
            user.setAvatarUrl(user1.getAvatarUrl());
        }
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(user1, user)));
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
