package com.example.server.services.like.impls;

import com.example.server.model.Like;
import com.example.server.repository.LikeRepository;
import com.example.server.services.like.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LikeServiceImpl implements LikeService{
    @Autowired
    LikeRepository likeRepository;

    @Override
    public Like save(Like like) {
        if(like.getId()!=null){
            return null;
        }
        return likeRepository.save(like);
    }

    @Override
    public void delete(int id) {
        likeRepository.deleteById(id);
    }

    @Override
    public Like getOne(int id) {
        return likeRepository.findById(id).get();
    }

    @Override
    public List<Like> getAll() {
        return likeRepository.findAll();
    }

    @Override
    public Like update(Like like) {
        if(like.getId()==null){
            return null;
        }
        return likeRepository.save(like);
    }
}
