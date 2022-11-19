package com.example.server.services.tag.impls;

import com.example.server.dto.TagDTO;
import com.example.server.mappers.TagMapper;
import com.example.server.repository.TagRepository;
import com.example.server.services.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagRepository repository;
    @Autowired
    TagMapper mapper;

    @Override
    public TagDTO save(TagDTO tag) {
        if(tag.getId()!=null){
            return null;
        }
        return mapper.toDTO(repository.save(mapper.toEntity(tag)));
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public TagDTO getOne(int id) {
        return repository.findById(id).map(elem -> mapper.toDTO(elem)).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<TagDTO> getAll() {
        return repository.findAll().stream().map(elem -> mapper.toDTO(elem)).collect(Collectors.toList());
    }

    @Override
    public TagDTO update(TagDTO tag) {
        return mapper.toDTO(repository.save(mapper.toEntity(repository.findById(tag.getId()).orElseThrow(NoSuchElementException::new), tag)));
    }
}
