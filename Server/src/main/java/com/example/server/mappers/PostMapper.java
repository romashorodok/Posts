package com.example.server.mappers;


import com.example.server.dto.PostDTO;
import com.example.server.dto.RecentPostDTO;
import com.example.server.dto.ViewPostDTO;
import com.example.server.model.*;
import org.mapstruct.*;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Mapper(componentModel = "spring",
        uses = {CommentMapper.class, UserMapper.class})
public interface PostMapper {
    Post toEntity(PostDTO post);
    PostDTO toDTO(Post post);
    Post toEntity(@MappingTarget Post post, PostDTO postDTO);
    @Mapping(source = "imageUrl", target = "image", qualifiedByName = "urlToByte1")
    ViewPostDTO toViewPostDTO(Post post);
    @Mapping(source = "imageUrl", target = "image", qualifiedByName = "urlToByte1")
    RecentPostDTO toRecentPostDTO(Post post);
    @Named("urlToByte1")
    static byte[] urlToByte(String url) throws IOException {
        if(!url.equals("")){
            Path path = Paths.get("Server/src/main/resources/images/" + url);
            if(Files.exists(path)){
                return Files.readAllBytes(path);
            }
        }
        return null;
    }
}
