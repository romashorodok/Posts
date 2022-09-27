package com.example.server.services.comment.impls;

import com.example.server.model.Comment;
import com.example.server.repository.CommentRepository;
import com.example.server.services.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        if(comment.getId()!=null){
            return null;
        }
        return commentRepository.save(comment);
    }

    @Override
    public void delete(int id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Comment getOne(int id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment update(Comment comment) {
        if(comment.getId()==null){
            return null;
        }
        return commentRepository.save(comment);
    }
}
