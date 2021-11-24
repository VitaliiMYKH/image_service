package com.voxloud.image_service.dto;

import com.voxloud.image_service.model.Account;
import javax.persistence.ManyToOne;
import lombok.Data;

@Data
public class ImageResponseDto {
    private Long id;
    private String name;
    private Long size;
    private String reference;
    private String contentType;
    private Account account;
}
