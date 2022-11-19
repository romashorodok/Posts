package com.example.server.mappers;

import com.example.server.dto.TagDTO;
import com.example.server.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TagMapper {
    Tag toEntity(TagDTO tag);
    TagDTO toDTO(Tag tag);
    Tag toEntity(@MappingTarget Tag tag, TagDTO tagDTO);
}
