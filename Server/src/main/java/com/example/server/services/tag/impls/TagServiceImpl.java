package com.example.server.services.tag.impls;

import com.example.server.model.Tag;
import com.example.server.repository.TagRepository;
import com.example.server.services.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagRepository tagRepository;

    @Override
    public Tag save(Tag tag) {
        if(tag.getId()!=null){
            return null;
        }
        return tagRepository.save(tag);
    }

    @Override
    public void delete(int id) {
        tagRepository.deleteById(id);
    }

    @Override
    public Tag getOne(int id) {
        return tagRepository.findById(id).get();
    }

    @Override
    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag update(Tag tag) {
        if(tag.getId()==null){
            return null;
        }
        return tagRepository.save(tag);
    }
}
