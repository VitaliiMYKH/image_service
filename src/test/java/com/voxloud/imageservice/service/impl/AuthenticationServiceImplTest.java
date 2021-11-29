package com.voxloud.imageservice.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.voxloud.imageservice.model.Account;
import com.voxloud.imageservice.model.Image;
import com.voxloud.imageservice.model.Role;
import com.voxloud.imageservice.model.Tag;
import com.voxloud.imageservice.service.AccountService;
import com.voxloud.imageservice.service.AuthenticationService;
import com.voxloud.imageservice.service.ImageService;
import com.voxloud.imageservice.service.RoleService;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

class AuthenticationServiceImplTest {
    private AuthenticationService authenticationService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    private ImageService imageService;
    private AccountService accountService;
    private Role role;
    private String roleName;
    private Tag tag;
    private Image image;
    private Account account;
    private String password;
    private String login;

    @BeforeEach
    void setUp() {
        roleService = Mockito.mock(RoleService.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        imageService = Mockito.mock(ImageService.class);
        accountService = Mockito.mock(AccountService.class);
        authenticationService = new AuthenticationServiceImpl(roleService,
                passwordEncoder, imageService, accountService);
        roleName = "USER";
        role = new Role();
        role.setId(1L);
        role.setRoleName(roleName);
        tag = new Tag();
        tag.setTagName("testTagName");
        image = new Image();
        image.setContentType("testContentType");
        image.setName("testName");
        image.setReference("testReference" );
        image.setSize(10L);
        image.setTags(List.of(tag));
        password = "1234";
        login = "test@mail.com";
        account = new Account();
        account.setLogin(login);
        account.setPassword(password);
        account.setRoles(Set.of(role));
    }

    @Test
    void register() {
        String encodedPassword = "12345";
        Account registeredAccount = new Account();
        registeredAccount.setLogin("test@mail.com");
        registeredAccount.setImages(List.of(image));
        registeredAccount.setPassword(encodedPassword);
        registeredAccount.setRoles(Set.of(role));
        Mockito.when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        Mockito.when(roleService.getRoleByRoleName(roleName)).thenReturn(role);
        Mockito.when(imageService.addListOfImages(account, List.of(image))).thenReturn(List.of(image));
        Mockito.when(accountService.getByLogin(login)).thenReturn(registeredAccount);
        Account actual = authenticationService.register(login, password);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(registeredAccount, actual);
    }
}