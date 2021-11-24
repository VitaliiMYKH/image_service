package com.voxloud.image_service.repository;

import com.voxloud.image_service.model.Account;
import com.voxloud.image_service.model.Image;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> getBySize(Long size);
    Optional<Image> getByName(String name);
    Optional<Image> getByReference(String reference);
    List<Image> getByContentType(String contentType);
    List<Image> getByAccount(Account account);
}
