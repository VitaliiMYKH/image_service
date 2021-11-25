package com.voxloud.image_service.dto;

import com.voxloud.image_service.model.Role;
import java.util.Set;
import lombok.Data;
import lombok.NonNull;

@Data
public class AccountRequestDto {
    private String login;
    private String password;
}
