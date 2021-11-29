package com.voxloud.imageservice.service.impl;

import com.voxloud.imageservice.exception.DataProcessingException;
import com.voxloud.imageservice.model.Account;
import com.voxloud.imageservice.model.Image;
import com.voxloud.imageservice.model.Tag;
import com.voxloud.imageservice.repository.ImageRepository;
import com.voxloud.imageservice.repository.TagRepository;
import com.voxloud.imageservice.service.AccountService;
import com.voxloud.imageservice.service.ImageService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final TagRepository tagRepository;
    private final AccountService accountService;

    public ImageServiceImpl(ImageRepository imageRepository,
                            TagRepository tagRepository,
                            AccountService accountService) {
        this.imageRepository = imageRepository;
        this.tagRepository = tagRepository;
        this.accountService = accountService;
    }

    @Override
    public List<Image> addListOfImages(Account account, List<Image> images) {
        accountService.updateAccount(account, images);
        return imageRepository.saveAll(images);
    }

    @Override
    public Image getById(Account account, Long id) {
        Optional<Image> optionalImageById = imageRepository.getImageById(id);
        Image imageById = optionalImageById
                .orElseThrow(() -> new DataProcessingException("Can`t get image by id " + id));
        if (account.getImages().contains(imageById)) {
            return imageById;
        }
        throw new DataProcessingException("Can`t get Image, which is not in current account");
    }

    @Override
    public Image getByName(Account account, String name) {
        Image imageByName = imageRepository.getByName(name)
                .orElseThrow(() -> new DataProcessingException("Can`t get image by name " + name));
        if (account.getImages().contains(imageByName)) {
            return imageByName;
        }
        throw new DataProcessingException("Can`t get Image, which is not in current account");
    }

    @Override
    public List<Image> getBySize(Account account, Long size) {
        List<Image> sameSizeImages = imageRepository.getBySize(size);
        if (sameSizeImages.isEmpty()) {
            throw new DataProcessingException("Can`t get images by size: " + size);
        }
        List<Image> accountImagesBySize = account.getImages()
                .stream()
                .filter(sameSizeImages::contains)
                .collect(Collectors.toList());
        if (accountImagesBySize.isEmpty()) {
            throw new DataProcessingException("Can`t get Image, which is not in current account");
        }
        return accountImagesBySize;
    }

    @Override
    public Image getByReference(Account account, String reference) {
        Image imageByReference = imageRepository.getByReference(reference)
                .orElseThrow(() -> new DataProcessingException("Can`t "
                        + "get image by reference: " + reference));
        if (account.getImages().contains(imageByReference)) {
            return imageByReference;
        }
        throw new DataProcessingException("Can`t get Image, which is not in current account");
    }

    @Override
    public List<Image> getByContentType(Account account, String contentType) {
        List<Image> sameContentTypeImages = imageRepository.getByContentType(contentType);
        if (sameContentTypeImages.isEmpty()) {
            throw new DataProcessingException("Can`t get images by content type: " + contentType);
        }
        List<Image> accountImagesByContentType = account.getImages()
                .stream()
                .filter(sameContentTypeImages::contains)
                .collect(Collectors.toList());
        if (accountImagesByContentType.isEmpty()) {
            throw new DataProcessingException("Can`t get Image, which is not in current account");
        }
        return accountImagesByContentType;
    }

    @Override
    public List<Image> getImageByTag(Account account, Tag tag) {
        List<Image> imageByTags = imageRepository.getImageByTags(tag);
        if (imageByTags.isEmpty()) {
            throw new DataProcessingException("Can`t get Images with tag: " + tag);
        }
        List<Image> accountImagesByTag = account.getImages()
                .stream()
                .filter(imageByTags::contains)
                .collect(Collectors.toList());
        if (accountImagesByTag.isEmpty()) {
            throw new DataProcessingException("Can`t get images which is not in current account");
        }
        return accountImagesByTag;
    }
}
