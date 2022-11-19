package com.example.server.mappers;

import com.example.server.dto.ProfileDTO;
import com.example.server.dto.UserDTO;
import com.example.server.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDTO user);
    UserDTO toDTO(User user);
    User toEntity(@MappingTarget User user, UserDTO userDTO);
    @Mapping(source = "avatarUrl", target = "avatar", qualifiedByName = "urlToByte")
    ProfileDTO toProfileDTO(User user);
    @Named("urlToByte")
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
