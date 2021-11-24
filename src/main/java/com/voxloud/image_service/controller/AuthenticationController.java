package com.voxloud.image_service.controller;

import com.voxloud.image_service.dto.AccountRequestDto;
import com.voxloud.image_service.dto.AccountResponseDto;
import com.voxloud.image_service.model.Account;
import com.voxloud.image_service.service.AuthenticationService;
import com.voxloud.image_service.service.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final AccountMapper accountMapper;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, AccountMapper accountMapper) {
        this.authenticationService = authenticationService;
        this.accountMapper = accountMapper;
    }

    @PostMapping("/register")
    public AccountResponseDto register(@RequestBody AccountRequestDto accountRequestDto) {
        Account account = authenticationService.register(accountRequestDto.getLogin(), accountRequestDto.getPassword());
        return accountMapper.mapToDto(account);
    }
}
