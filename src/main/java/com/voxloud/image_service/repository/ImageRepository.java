package com.voxloud.image_service.repository;

import com.voxloud.image_service.model.Account;
import com.voxloud.image_service.model.Image;
import com.voxloud.image_service.model.Tag;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.core.metrics.StartupStep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> getBySize(Long size);

    Optional<Image> getByName(String name);

    Optional<Image> getByReference(String reference);

    List<Image> getByContentType(String contentType);

    List<Image> getByAccount(Account account);
}
