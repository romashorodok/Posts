package com.example.server.services.user.impls;

import com.example.server.dto.ProfileDTO;
import com.example.server.dto.UserDTO;
import com.example.server.mappers.UserMapper;
import com.example.server.model.User;
import com.example.server.repository.UserRepository;
import com.example.server.services.user.UserService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
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
    UserMapper mapper;
    @Autowired
    PasswordEncoder encoder;
    @Value("${images.path.save}")
    private String path;

    @Override
    public UserDTO save(UserDTO user, @Nullable MultipartFile file) throws IOException {
        Optional<User> exists = userRepository.findByEmail(user.getEmail());

        if (exists.isPresent()) return null;

        if (user.getId() != null) {
            return null;
        }

        if (file != null) {
            String filename = UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
            Files.write(Paths.get(path + filename), file.getBytes());
            user.setAvatarUrl(filename);
        }

        user.setPassword(encoder.encode(user.getPassword()));

        return mapper.toDTO(userRepository.save(mapper.toEntity(user)));
    }

    @Override
    public void delete(int id) throws IOException {
        Files.deleteIfExists(Paths.get(path + userRepository.findById(id).orElseThrow(NoSuchElementException::new).getAvatarUrl()));
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO getOne(int id) {
        return userRepository.findById(id).map(mapper::toDTO).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<ProfileDTO> getProfilesAll(int size) {
        return userRepository.findAll().stream().map(elem -> mapper.toProfileDTO(elem, size)).collect(Collectors.toList());
    }

    public ProfileDTO getOneProfileById(int id, int size) {
        return userRepository.findById(id).map(elem -> mapper.toProfileDTO(elem, size)).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public UserDTO update(UserDTO user, @Nullable MultipartFile file) throws IOException {
        User user1 = userRepository.findById(user.getId()).orElseThrow(NoSuchElementException::new);

        User freshEntity = mapper.toEntity(user1, user);

        if (user.getPassword() != null) freshEntity.setPassword(encoder.encode(user.getPassword()));

        if (file != null && !file.isEmpty()) {
            if (!user1.getAvatarUrl().equals("")) {
                Files.deleteIfExists(Paths.get(path + user1.getAvatarUrl()));
            }

            String filename = UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
            Files.write(Paths.get(path + filename), file.getBytes());
            user.setAvatarUrl(filename);
        }

        return mapper.toDTO(userRepository.save(freshEntity));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(NoSuchElementException::new);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE" + role));
        });
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
