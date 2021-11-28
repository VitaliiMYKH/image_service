package com.voxloud.imageservice.service;

import com.voxloud.imageservice.model.Tag;
import java.util.List;

public interface TagService {
    Tag addTag(Tag tag);

    List<Tag> getTagsById(List<Long> tagsId);

    Tag getTagByTagName(String tagName);
}
