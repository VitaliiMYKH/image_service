package com.voxloud.imageservice.service;

import com.voxloud.imageservice.model.Account;
import com.voxloud.imageservice.model.Image;
import java.util.List;

public interface AccountService {
    Account updateAccount(Account account, List<Image> images);

    Account getByLogin(String login);

    Account getById(Long id);

    Account add(Account account);
}
