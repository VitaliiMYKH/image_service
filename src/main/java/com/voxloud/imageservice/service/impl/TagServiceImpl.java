package com.voxloud.imageservice.service.impl;

import com.voxloud.imageservice.model.Tag;
import com.voxloud.imageservice.repository.TagRepository;
import com.voxloud.imageservice.service.TagService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> getTagsById(List<Long> tagsId) {
        return tagsId.stream()
               .map(tagRepository::getTagById)
               .collect(Collectors.toList());
    }

    @Override
    public Tag getTagByTagName(String tagName) {
        return tagRepository.getTagByTagName(tagName);
    }
}
