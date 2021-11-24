package com.voxloud.image_service.service;

import com.voxloud.image_service.model.Account;

public interface AccountService {
    Account getByLogin(String login);
}
