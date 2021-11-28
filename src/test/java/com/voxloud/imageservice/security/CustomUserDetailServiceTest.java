package com.voxloud.imageservice.security;

import static org.junit.jupiter.api.Assertions.*;

import com.voxloud.imageservice.model.Account;
import com.voxloud.imageservice.model.Role;
import com.voxloud.imageservice.service.AccountService;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

class CustomUserDetailServiceTest {
    private UserDetailsService userDetailsService;
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = Mockito.mock(AccountService.class);
        userDetailsService = new CustomUserDetailService(accountService);
    }

    @Test
    void loadUserByUsername_OK() {
        String login = "test@mail.com";
        Account test = new Account();
        test.setLogin(login);
        test.setPassword("1234");
        Role user = new Role();
        user.setRoleName("USER");
        test.setRoles(Set.of(user));

        Mockito.when(accountService.getByLogin(login)).thenReturn(test);
        UserDetails actual = userDetailsService.loadUserByUsername(login);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(login, actual.getUsername());
        Assertions.assertEquals("1234", actual.getPassword());
    }
}