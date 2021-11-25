package com.voxloud.image_service.service.impl;

import com.voxloud.image_service.exception.DataProcessingException;
import com.voxloud.image_service.model.Account;
import com.voxloud.image_service.model.Image;
import com.voxloud.image_service.model.Tag;
import com.voxloud.image_service.repository.ImageRepository;
import com.voxloud.image_service.repository.TagRepository;
import com.voxloud.image_service.service.ImageService;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final TagRepository tagRepository;

    public ImageServiceImpl(ImageRepository imageRepository, TagRepository tagRepository) {
        this.imageRepository = imageRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Image> addListOfImages(List<Image> images) {
        return imageRepository.saveAll(images);
    }

    @Override
    public Image getById(Long id) {
        return imageRepository.getById(id);
    }

    @Override
    public Image getByName(String name) {
        return imageRepository.getByName(name)
                .orElseThrow(() -> new DataProcessingException("Can`t get image by name " + name));
    }

    @Override
    public List<Image> getBySize(Long size) {
        List<Image> sameSizeImages = imageRepository.getBySize(size);
        if (sameSizeImages.isEmpty()) {
            throw new DataProcessingException("Can`t get images by size: " + size);
        }
        return sameSizeImages;
    }

    @Override
    public Image getByReference(String reference) {
        return imageRepository.getByReference(reference)
                .orElseThrow(() -> new DataProcessingException("Can`t get image by reference: " + reference));
    }

    @Override
    public List<Image> getByContentType(String contentType) {
        List<Image> sameContentTypeImages = imageRepository.getByContentType(contentType);
        if (sameContentTypeImages.isEmpty()) {
            throw new DataProcessingException("Can`t get images by contentType: " + contentType);
        }
        return sameContentTypeImages;
    }

    @Override
    public List<Image> getByAccount(Account account) {
        List<Image> sameAccountImages = imageRepository.getByAccount(account);
        if (sameAccountImages.isEmpty()) {
            throw new DataProcessingException("Can`t get images by account: " + account);
        }
        return sameAccountImages;
    }
}
