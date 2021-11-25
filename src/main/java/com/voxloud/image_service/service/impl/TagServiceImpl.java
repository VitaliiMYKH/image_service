package com.voxloud.image_service.service.impl;

import com.voxloud.image_service.model.Tag;
import com.voxloud.image_service.repository.TagRepository;
import com.voxloud.image_service.service.TagService;
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
