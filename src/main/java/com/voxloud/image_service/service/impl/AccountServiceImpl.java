package com.voxloud.image_service.service.impl;

import com.voxloud.image_service.exception.DataProcessingException;
import com.voxloud.image_service.model.Account;
import com.voxloud.image_service.repository.AccountRepository;
import com.voxloud.image_service.service.AccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Account getByLogin(String login) {
       return accountRepository.getByLogin(login).orElseThrow(() -> new DataProcessingException("Can`t find in" +
                       " database account with login: " + login));
    }

    @Override
    public Account getById(Long id) {
        return accountRepository.getById(id);
    }

    @Override
    public Account add(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }
}
