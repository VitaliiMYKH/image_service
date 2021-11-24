package com.voxloud.image_service.dto;

import com.voxloud.image_service.model.Image;
import java.util.List;
import lombok.Data;

@Data
public class AccountResponseDto {
    private Long id;
    private String login;
    private String password;
    private List<Image> images;
}
