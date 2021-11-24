package com.voxloud.image_service.service.impl;

import com.voxloud.image_service.model.Account;
import com.voxloud.image_service.repository.AccountRepository;
import com.voxloud.image_service.service.AuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AccountRepository accountRepository;

    public AuthenticationServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account register(String login, String password) {
        Account account = new Account();
        account.setLogin(login);
        account.setPassword(password);
        return accountRepository.save(account);
    }
}
