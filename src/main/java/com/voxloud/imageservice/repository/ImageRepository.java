package com.voxloud.imageservice.repository;

import com.voxloud.imageservice.model.Account;
import com.voxloud.imageservice.model.Image;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface ImageRepository extends JpaRepository<Image, Long> {

    Image getById(Long id);

    Optional<Image> getByName(String name);

    Optional<Image> getByReference(String reference);

    List<Image> getByContentType(String contentType);

    List<Image> getBySize(Long size);

  //  List<Image> getByAccount(Account account);
}
