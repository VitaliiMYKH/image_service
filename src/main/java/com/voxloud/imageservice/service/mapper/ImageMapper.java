package com.voxloud.imageservice.service.mapper;

import com.voxloud.imageservice.dto.ImageRequestDto;
import com.voxloud.imageservice.dto.ImageResponseDto;
import com.voxloud.imageservice.model.Image;
import com.voxloud.imageservice.model.Tag;
import com.voxloud.imageservice.service.AccountService;
import com.voxloud.imageservice.service.TagService;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {
    private final AccountService accountService;
    private final TagService tagService;

    public ImageMapper(AccountService accountService, TagService tagService) {
        this.accountService = accountService;
        this.tagService = tagService;
    }

    public ImageResponseDto mapToDto(Image image) {
        ImageResponseDto imageResponseDto = new ImageResponseDto();
        imageResponseDto.setId(image.getId());
        imageResponseDto.setContentType(image.getContentType());
        imageResponseDto.setReference(image.getReference());
        imageResponseDto.setName(image.getName());
        imageResponseDto.setSize(image.getSize());
        imageResponseDto.setTagId(image.getTags()
                .stream()
                .map(Tag::getId)
                .collect(Collectors.toList()));
        return imageResponseDto;
    }

    public Image mapToModel(ImageRequestDto imageRequestDto) {
        Image image = new Image();
        image.setContentType(imageRequestDto.getContentType());
        image.setName(imageRequestDto.getName());
        image.setReference(imageRequestDto.getReference());
        image.setSize(imageRequestDto.getSize());
        image.setTags(tagService.getTagsById(imageRequestDto.getTagId()));
        return image;
    }
}
