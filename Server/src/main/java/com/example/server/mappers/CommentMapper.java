package com.example.server.mappers;

import com.example.server.dto.CommentDTO;
import com.example.server.dto.CommentSaveDTO;
import com.example.server.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;



@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CommentMapper {
    Comment toEntity(CommentSaveDTO comment);
    CommentSaveDTO toCommentSaveDTO(Comment comment);
    CommentDTO toDTO(Comment comment);
    Comment toEntity(@MappingTarget Comment comment, CommentSaveDTO commentDTO);
}
