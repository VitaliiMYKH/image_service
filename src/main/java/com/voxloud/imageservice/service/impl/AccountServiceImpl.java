package com.voxloud.imageservice.service.impl;

import com.voxloud.imageservice.exception.DataProcessingException;
import com.voxloud.imageservice.model.Account;
import com.voxloud.imageservice.model.Image;
import com.voxloud.imageservice.repository.AccountRepository;
import com.voxloud.imageservice.repository.ImageRepository;
import com.voxloud.imageservice.service.AccountService;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final ImageRepository imageRepository;

    public AccountServiceImpl(AccountRepository accountRepository,
                              PasswordEncoder passwordEncoder,
                              ImageRepository imageRepository) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.imageRepository = imageRepository;
    }

    @Override
    public Account updateAccount(Account account, List<Image> images) {
        imageRepository.saveAll(images);
        account.setImages(images);
        return accountRepository.save(account);
    }

    @Override
    public Account getByLogin(String login) {
        return accountRepository.getByLogin(login)
               .orElseThrow(() -> new DataProcessingException("Can`t find in"
               + " database account with login: " + login));
    }

    @Override
    public Account getById(Long id) {
        return accountRepository.getAccountById(id)
                .orElseThrow(() -> new DataProcessingException("Can`t find in"
                + " database account with id: " + id));
    }

    @Override
    public Account add(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }
}
