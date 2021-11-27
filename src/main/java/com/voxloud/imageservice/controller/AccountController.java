package com.voxloud.imageservice.controller;

import com.voxloud.imageservice.dto.AccountResponseDto;
import com.voxloud.imageservice.dto.ImageResponseDto;
import com.voxloud.imageservice.model.Account;
import com.voxloud.imageservice.service.AccountService;
import com.voxloud.imageservice.service.mapper.AccountMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
    public AccountResponseDto getByLogin(Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        return accountMapper.mapToDto(accountService.getByLogin(principal.getUsername()));
    }

  /*  @GetMapping("/images")
    public List<ImageResponseDto> getByAccount(Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String username = principal.getUsername();
        Account account = accountService.getByLogin(username);
        return imageService.getByAccount(account)
                .stream()
                .map(imageMapper::mapToDto)
                .collect(Collectors.toList());
    }*/

}
