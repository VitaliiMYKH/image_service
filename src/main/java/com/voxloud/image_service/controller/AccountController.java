package com.voxloud.image_service.controller;

import com.voxloud.image_service.dto.AccountResponseDto;
import com.voxloud.image_service.service.AccountService;
import com.voxloud.image_service.service.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/accounts")
public class AccountController {
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountController(AccountService accountService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @GetMapping
    public AccountResponseDto getByLogin(@RequestParam String login){
        return accountMapper.mapToDto(accountService.getByLogin(login));
    }

}
