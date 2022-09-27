package com.example.server.services.comment;

import com.example.server.model.Comment;

import java.util.List;

public interface CommentService {
    Comment save(Comment comment);
    void delete(int id);
    Comment getOne(int id);
    List<Comment> getAll();
    Comment update(Comment comment);
}
