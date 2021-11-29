package com.voxloud.imageservice.dto;

import java.util.List;
import lombok.Data;

@Data
public class AccountResponseDto {
    private Long id;
    private String login;
    private List<Long> imagesId;
}
