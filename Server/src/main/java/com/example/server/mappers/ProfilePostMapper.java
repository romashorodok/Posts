package com.example.server.mappers;

import com.example.server.dto.RecentPostDTO;
import com.example.server.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Mapper(componentModel = "spring")
public interface ProfilePostMapper {
    @Mapping(source = "imageUrl", target = "image", qualifiedByName = "urlToByte1")
    RecentPostDTO toRecentPostDTO(Post post);
    @Named("urlToByte1")
    static byte[] urlToByte(String url) throws IOException {
        if(!url.equals("")){
            Path path = Paths.get("Server/src/main/resources/images/" + url);
            if(Files.exists(path)){
                return Base64Utils.decode(Files.readAllBytes(path));
            }
        }
        return null;
    }
}
