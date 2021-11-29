package com.voxloud.imageservice.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class AccountRequestDto {
    @NonNull
    private String login;
    @NonNull
    private String password;
}
