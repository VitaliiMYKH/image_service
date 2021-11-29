package com.voxloud.imageservice.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.voxloud.imageservice.service.AccountService;
import com.voxloud.imageservice.service.mapper.AccountMapper;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

class AccountControllerTest {
    private AccountService accountService;
    private AccountMapper accountMapper;
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        accountService = Mockito.mock(AccountService.class);
        accountMapper = Mockito.mock(AccountMapper.class);
        accountController = new AccountController(accountService,accountMapper);
    }

    @Test
    void getByLogin() {

    }
}