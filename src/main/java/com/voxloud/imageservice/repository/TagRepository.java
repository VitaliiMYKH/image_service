package com.voxloud.imageservice.repository;

import com.voxloud.imageservice.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag getTagById(Long tagId);

    Tag getTagByTagName(String tagName);
}
