package com.voxloud.imageservice.service.impl;

import com.voxloud.imageservice.exception.DataProcessingException;
import com.voxloud.imageservice.model.Account;
import com.voxloud.imageservice.model.Image;
import com.voxloud.imageservice.model.Role;
import com.voxloud.imageservice.model.Tag;
import com.voxloud.imageservice.repository.AccountRepository;
import com.voxloud.imageservice.repository.ImageRepository;
import com.voxloud.imageservice.service.AccountService;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

class AccountServiceImplTest {
    private AccountService accountService;
    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;
    private ImageRepository imageRepository;
    private Tag tag;
    private Role role;
    private Image image;
    private Account account;

    @BeforeEach
    void setUp() {
        accountRepository = Mockito.mock(AccountRepository.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        imageRepository = Mockito.mock(ImageRepository.class);
        accountService = new AccountServiceImpl(accountRepository,passwordEncoder,imageRepository);

        tag = new Tag();
        tag.setTagName("testTagName");
        image = new Image();
        image.setContentType("testContentType");
        image.setName("testName");
        image.setReference("testReference" );
        image.setSize(10L);
        image.setTags(List.of(tag));
        role = new Role();
        role.setRoleName("test");
        account = new Account();
        account.setLogin("test@mail.com");
        account.setPassword("1234");
        account.setRoles(Set.of(role));
        account.setImages(List.of(image));
    }

    @Test
    void updateAccount_Ok() {
        Account inputAccount = new Account();
        inputAccount.setLogin("test@mail.com");
        inputAccount.setPassword("1234");
        inputAccount.setRoles(Set.of(role));
        Account outputAccount = new Account();
        outputAccount.setLogin("test@mail.com");
        outputAccount.setPassword("1234");
        outputAccount.setRoles(Set.of(role));
        outputAccount.setImages(List.of(image));
        Mockito.when(accountRepository.save(outputAccount)).thenReturn(outputAccount);
        Account actual = accountService.updateAccount(inputAccount, List.of(image));
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(List.of(image), actual.getImages());
        Assertions.assertEquals("test@mail.com", actual.getLogin());
        Assertions.assertEquals("1234", actual.getPassword());
        Assertions.assertEquals(Set.of(role), actual.getRoles());
    }

    @Test
    void getByLogin_Ok() {
        String login = "test@mail.com";
        Mockito.when(accountRepository.getByLogin(login)).thenReturn(Optional.of(account));
        Account actual = accountService.getByLogin(login);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(account,actual);
    }

    @Test
    void getByLogin_NotOk() {
        String login = "test@mail.com";
        String invalidLogin = "invalidLogin";
        Mockito.when(accountRepository.getByLogin(login)).thenReturn(Optional.of(account));
        try {
            accountService.getByLogin(invalidLogin);
        } catch (DataProcessingException e) {
            Assertions.assertEquals("Can`t find in database account with login: " + invalidLogin, e.getMessage());
            return;
        }
        Assertions.fail("Expected to receive DataProcessingException");
    }

    @Test
    void getById_Ok() {
        Long id = 1L;
        Mockito.when(accountRepository.getAccountById(id)).thenReturn(Optional.of(account));
        Account actual = accountService.getById(id);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(account,actual);
    }

    @Test
    void getById_NotOk() {
        Long id = 1L;
        Long invalidId = 2L;
        Mockito.when(accountRepository.getAccountById(id)).thenReturn(Optional.of(account));
        try {
            accountService.getById(invalidId);
        } catch (DataProcessingException e) {
            Assertions.assertEquals("Can`t find in database account with id: " + invalidId, e.getMessage());
            return;
        }
        Assertions.fail("Expected to receive DataProcessingException");
    }

    @Test
    void add_Ok() {
        String encodedPassword = "12345";
        Account encodedAccount = new Account();
        encodedAccount.setLogin("test@mail.com");
        encodedAccount.setRoles(Set.of(role));
        encodedAccount.setImages(List.of(image));
        encodedAccount.setPassword(encodedPassword);
        Mockito.when(passwordEncoder.encode(account.getPassword())).thenReturn(encodedPassword);
        Mockito.when(accountRepository.save(account)).thenReturn(encodedAccount);
        Account actual = accountService.add(account);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(encodedAccount, actual);
    }
}