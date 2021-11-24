package com.voxloud.image_service.service.mapper;

import com.voxloud.image_service.dto.ImageResponseDto;
import com.voxloud.image_service.model.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {
    public ImageResponseDto mapToDto(Image image) {
        ImageResponseDto imageResponseDto = new ImageResponseDto();
        imageResponseDto.setId(image.getId());
        imageResponseDto.setAccount(image.getAccount());
        imageResponseDto.setContentType(image.getContentType());
        imageResponseDto.setReference(image.getReference());
        imageResponseDto.setName(image.getName());
        imageResponseDto.setSize(image.getSize());
        return imageResponseDto;
    }
}
