package com.voxloud.image_service.service;

import com.voxloud.image_service.model.Account;

public interface AccountService {
    Account getByLogin(String login);

    Account getById(Long id);
    Account add(Account account);
}
