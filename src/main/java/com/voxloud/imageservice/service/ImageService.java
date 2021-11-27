package com.voxloud.imageservice.service;

import com.voxloud.imageservice.model.Account;
import com.voxloud.imageservice.model.Image;
import java.util.List;

public interface ImageService {
    List<Image> addListOfImages(Account account, List<Image> image);

    Image getById(Account account, Long id);

    Image getByName(Account account, String name);

    List<Image> getBySize(Account account, Long size);

    Image getByReference(Account account, String reference);

    List<Image> getByContentType(Account account, String reference);

  //  List<Image> getByAccount(Account account);
}
