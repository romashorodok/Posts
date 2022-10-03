package com.example.server.services.tag;


import com.example.server.dto.TagDTO;

import java.util.List;

public interface TagService {
    TagDTO save(TagDTO tag);
    void delete(int id);
    TagDTO getOne(int id);
    List<TagDTO> getAll();
    TagDTO update(TagDTO tag);

}
