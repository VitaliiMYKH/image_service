package com.voxloud.imageservice.dto;

import java.util.List;
import lombok.Data;
import lombok.NonNull;

@Data
public class ImageRequestDto {
    @NonNull
    private String name;
    @NonNull
    private Long size;
    @NonNull
    private String reference;
    @NonNull
    private String contentType;
    /*@NonNull*/
    private List<Long> tagId;
}
