package com.voxloud.imageservice.service;

import com.voxloud.imageservice.model.Account;

public interface AuthenticationService {
    Account register(String login, String password);
}
