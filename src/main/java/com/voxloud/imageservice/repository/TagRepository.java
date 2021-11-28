package com.voxloud.imageservice.repository;

import com.voxloud.imageservice.model.Tag;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> getTagById(Long tagId);

    Optional<Tag> getTagByTagName(String tagName);
}
