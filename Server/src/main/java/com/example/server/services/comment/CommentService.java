package com.example.server.services.comment;

import com.example.server.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO save(CommentDTO comment);
    void delete(int id);
    CommentDTO getOne(int id);
    List<CommentDTO> getAll();
    CommentDTO update(CommentDTO comment);
}
