package com.voxloud.imageservice.repository;

import com.voxloud.imageservice.model.Image;
import com.voxloud.imageservice.model.Tag;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> getImageById(Long id);

    Optional<Image> getByName(String name);

    Optional<Image> getByReference(String reference);

    List<Image> getByContentType(String contentType);

    List<Image> getBySize(Long size);

    List<Image> getImageByTags(Tag tag);

}
