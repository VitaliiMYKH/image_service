package com.voxloud.image_service.repository;

import com.voxloud.image_service.model.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag getTagById(Long tagId);

    Tag getTagByTagName(String tagName);
}
