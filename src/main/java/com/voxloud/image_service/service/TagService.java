package com.voxloud.image_service.service;

import com.voxloud.image_service.model.Tag;
import java.util.List;

public interface TagService {
    List<Tag> getTagsById(List<Long> tagsId);

    Tag getTagByTagName(String tagName);
}
