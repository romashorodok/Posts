package com.example.server.mappers;

import com.example.server.dto.TagDTO;
import com.example.server.model.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {
    public TagDTO toDTO(Tag tag){
        TagDTO dto = new TagDTO();
        dto.setId(tag.getId());
        dto.setName(tag.getName());
        return dto;
    }
    public Tag toEntity(Tag tag, TagDTO dto){
        tag.setId(dto.getId());
        tag.setName(dto.getName());
        return tag;
    }
}
