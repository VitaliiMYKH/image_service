package com.voxloud.image_service.dto;

import com.voxloud.image_service.model.Account;
import com.voxloud.image_service.model.Tag;
import java.util.List;
import java.util.Set;
import lombok.Data;

@Data
public class ImageRequestDto {
    private String name;
    private Long size;
    private String reference;
    private String contentType;
    private Long accountId;
    private List<Long> tagId;
}
