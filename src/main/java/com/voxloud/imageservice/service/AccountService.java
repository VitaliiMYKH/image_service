package com.voxloud.imageservice.service;

import com.voxloud.imageservice.model.Account;

public interface AccountService {
    Account getByLogin(String login);

    Account getById(Long id);

    Account add(Account account);
}
