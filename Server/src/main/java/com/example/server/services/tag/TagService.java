package com.example.server.services.tag;


import com.example.server.model.Tag;

import java.util.List;

public interface TagService {
    Tag save(Tag tag);
    void delete(int id);
    Tag getOne(int id);
    List<Tag> getAll();
    Tag update(Tag tag);

}
