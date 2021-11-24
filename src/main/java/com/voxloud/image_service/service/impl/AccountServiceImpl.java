package com.voxloud.image_service.service.impl;

import com.voxloud.image_service.model.Account;
import com.voxloud.image_service.repository.AccountRepository;
import com.voxloud.image_service.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getByLogin(String login) {
       return accountRepository.getByLogin(login).get();
    }
}
