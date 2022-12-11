package com.example.server.mappers;

import com.example.server.dto.*;
import com.example.server.model.Post;
import com.example.server.model.User;
import org.mapstruct.*;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Mapper(componentModel = "spring", uses = {ProfilePostMapper.class})
public interface UserMapper {
    @Named("urlToByte")

    static byte[] urlToByte(String url) throws IOException {
        if (url != null && !url.equals("")) {

            Path path = Paths.get("Server/src/main/resources/images/" + url);
            if (Files.exists(path)) {
                return Base64Utils.decode(Files.readAllBytes(path));
            }
        }
        return null;
    }

    User toEntity(UserDTO user);

    UserDTO toDTO(User user);

    User toEntity(@MappingTarget User user, UserDTO userDTO);

    @Mapping(source = "avatarUrl", target = "avatar", qualifiedByName = "urlToByte")
    @Mapping(source = "posts", target = "posts", qualifiedByName = "toPage")
    ProfileDTO toProfileDTO(User user, @Context int size);

    @Mapping(source = "avatarUrl", target = "avatar", qualifiedByName = "urlToByte")
    CommentatorDTO toCommentatorDTO(User user);

    PageDTO<RecentPostDTO> toPage(Long totalElements, List<Post> content);

    @Named("toPage")
    default PageDTO<RecentPostDTO> toPage(List<Post> content, @Context int size) {
        int contentSize = content.size();
        if (contentSize < size) {
            return toPage((long) content.size(), content);
        }
        return toPage((long) content.size(), content.subList(0, size));
    }
}
