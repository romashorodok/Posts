
package com.example.server.mappers;

import com.example.server.dto.LikeDTO;
import com.example.server.model.Like;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LikeMapper {
    Like toEntity(LikeDTO like);
    LikeDTO toDTO(Like like);
    Like toEntity(@MappingTarget Like like, LikeDTO likeDTO);
}

