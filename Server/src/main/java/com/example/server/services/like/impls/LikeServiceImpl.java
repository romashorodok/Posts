package com.example.server.services.like.impls;

import com.example.server.dto.LikeDTO;
import com.example.server.mappers.LikeMapper;
import com.example.server.repository.LikeRepository;
import com.example.server.services.like.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class LikeServiceImpl implements LikeService{
    @Autowired
    LikeRepository repository;
    @Autowired
    LikeMapper mapper;

    @Override
    public LikeDTO save(LikeDTO like) {
        if(like.getId()!=null){
            return null;
        }
        return mapper.toDTO(repository.save(mapper.toEntity(like)));
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public LikeDTO getOne(int id) {
        return repository.findById(id).map(elem -> mapper.toDTO(elem)).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<LikeDTO> getAll() {
        return repository.findAll().stream().map(elem -> mapper.toDTO(elem)).collect(Collectors.toList());
    }

    @Override
    public LikeDTO update(LikeDTO like) {
        return mapper.toDTO(repository.save(mapper.toEntity(repository.findById(like.getId()).orElseThrow(NoSuchElementException::new), like)));
    }
}
