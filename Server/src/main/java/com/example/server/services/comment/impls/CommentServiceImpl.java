package com.example.server.services.comment.impls;

import com.example.server.dto.CommentDTO;
import com.example.server.mappers.CommentMapper;
import com.example.server.model.Comment;
import com.example.server.repository.CommentRepository;
import com.example.server.services.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository repository;
    @Autowired
    CommentMapper mapper;

    @Override
    public CommentDTO save(CommentDTO comment) {
        if(comment.getId()!=null){
            return null;
        }
        return mapper.toDTO(repository.save(mapper.toEntity(new Comment(), comment)));
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public CommentDTO getOne(int id) {
        return repository.findById(id).map(mapper::toDTO).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<CommentDTO> getAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CommentDTO update(CommentDTO comment) {
        return mapper.toDTO(repository.save(mapper.toEntity(repository.findById(comment.getId()).orElseThrow(NoSuchElementException::new), comment)));
    }
}
