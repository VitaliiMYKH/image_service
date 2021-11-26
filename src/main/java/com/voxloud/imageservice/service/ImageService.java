package com.voxloud.imageservice.service;

import com.voxloud.imageservice.model.Account;
import com.voxloud.imageservice.model.Image;
import java.util.List;

public interface ImageService {
    List<Image> addListOfImages(List<Image> image);

    Image getById(Long id);

    Image getByName(String name);

    List<Image> getBySize(Long size);

    Image getByReference(String reference);

    List<Image> getByContentType(String reference);

    List<Image> getByAccount(Account account);
}
