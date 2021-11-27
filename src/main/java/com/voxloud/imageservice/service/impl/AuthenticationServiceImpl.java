package com.voxloud.imageservice.service.impl;

import com.voxloud.imageservice.model.Account;
import com.voxloud.imageservice.model.Image;
import com.voxloud.imageservice.repository.AccountRepository;
import com.voxloud.imageservice.service.AccountService;
import com.voxloud.imageservice.service.AuthenticationService;
import com.voxloud.imageservice.service.ImageService;
import com.voxloud.imageservice.service.RoleService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AccountRepository accountRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final ImageService imageService;
    private final AccountService accountService;

    @Autowired
    public AuthenticationServiceImpl(AccountRepository accountRepository,
                                     RoleService roleService,
                                     PasswordEncoder passwordEncoder, ImageService imageService, AccountService accountService) {
        this.accountRepository = accountRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.imageService = imageService;
        this.accountService = accountService;
    }

    @Override
    public Account register(String login, String password) {
        Account account = new Account();
        account.setLogin(login);
        account.setPassword(passwordEncoder.encode(password));
        account.setRoles(Set.of(roleService.getRoleByRoleName("USER")));
       // accountRepository.save(account);
        Image image = new Image();
        imageService.addListOfImages(account, List.of(image));
        return accountService.getByLogin(login);
    //    account.setImages(List.of(image));
    }
}
