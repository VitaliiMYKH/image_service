package com.voxloud.image_service.service;

import com.voxloud.image_service.model.Account;

public interface AuthenticationService {
    Account register(String login, String password);
}
