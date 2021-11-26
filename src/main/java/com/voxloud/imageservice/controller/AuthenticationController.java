package com.voxloud.imageservice.controller;

import com.voxloud.imageservice.dto.AccountRequestDto;
import com.voxloud.imageservice.dto.AccountResponseDto;
import com.voxloud.imageservice.model.Account;
import com.voxloud.imageservice.service.AuthenticationService;
import com.voxloud.imageservice.service.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final AccountMapper accountMapper;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService,
                                    AccountMapper accountMapper) {
        this.authenticationService = authenticationService;
        this.accountMapper = accountMapper;
    }

    @PostMapping("/register")
    public AccountResponseDto register(@RequestBody AccountRequestDto accountRequestDto) {
        Account account = authenticationService
                .register(accountRequestDto.getLogin(), accountRequestDto.getPassword());
        return accountMapper.mapToDto(account);
    }
}
