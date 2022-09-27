package com.example.server.services.user.impls;

import com.example.server.model.User;
import com.example.server.repository.UserRepository;
import com.example.server.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        if(user.getId()!=null){
            return null;
        }
        return userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getOne(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) {
        if(user.getId()==null){
            return null;
        }
        return userRepository.save(user);
    }
}
