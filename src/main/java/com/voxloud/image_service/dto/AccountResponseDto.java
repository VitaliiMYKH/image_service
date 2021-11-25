package com.voxloud.image_service.dto;

import com.voxloud.image_service.model.Image;
import com.voxloud.image_service.model.Role;
import java.util.List;
import java.util.Set;
import lombok.Data;

@Data
public class AccountResponseDto {
    private Long id;
    private String login;
    private List<Long> imagesId;
}
