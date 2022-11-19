package com.example.server.services.comment;

import com.example.server.dto.CommentDTO;
import com.example.server.dto.CommentSaveDTO;

import java.util.List;

public interface CommentService {
    CommentSaveDTO save(CommentSaveDTO comment);
    void delete(int id);
    CommentDTO getOne(int id);
    List<CommentDTO> getAll();
    CommentSaveDTO update(CommentSaveDTO comment);
}
