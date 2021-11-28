package com.voxloud.imageservice.service.impl;

import com.voxloud.imageservice.exception.DataProcessingException;
import com.voxloud.imageservice.model.Tag;
import com.voxloud.imageservice.repository.TagRepository;
import com.voxloud.imageservice.service.TagService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag addTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public List<Tag> getTagsById(List<Long> tagsId) {
        return tagsId.stream()
               .map(tagRepository::getTagById)
               .map(Optional::get)
               .collect(Collectors.toList());
    }

    @Override
    public Tag getTagByTagName(String tagName) {
        return tagRepository.getTagByTagName(tagName)
                .orElseThrow(() -> new DataProcessingException("Can`t "
                        + "get tag by tag name: " + tagName));
    }
}
