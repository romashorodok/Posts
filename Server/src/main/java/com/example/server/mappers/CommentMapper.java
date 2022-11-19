package com.example.server.mappers;

import com.example.server.dto.CommentDTO;
import com.example.server.dto.CommentSaveDTO;
import com.example.server.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toEntity(CommentSaveDTO comment);
    CommentSaveDTO toCommentSaveDTO(Comment comment);
    @Mapping(source = "user.avatarUrl", target = "user.avatar", qualifiedByName = "urlToByte")
    CommentDTO toDTO(Comment comment);
    Comment toEntity(@MappingTarget Comment comment, CommentSaveDTO commentDTO);
    @Named("urlToByte")
    static byte[] urlToByte(String url) throws IOException {
        if(!url.isEmpty()){
            Path path = Paths.get("Server/src/main/resources/images/" + url);
            if(Files.exists(path)){
                return Files.readAllBytes(path);
            }
        }
        return null;
    }
}
