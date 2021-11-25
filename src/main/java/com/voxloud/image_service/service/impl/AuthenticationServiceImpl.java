package com.voxloud.image_service.service.impl;

import com.voxloud.image_service.model.Account;
import com.voxloud.image_service.repository.AccountRepository;
import com.voxloud.image_service.repository.RoleRepository;
import com.voxloud.image_service.service.AuthenticationService;
import com.voxloud.image_service.service.RoleService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AccountRepository accountRepository;
    private final RoleService roleService;

    @Autowired
    public AuthenticationServiceImpl(AccountRepository accountRepository, RoleService roleService) {
        this.accountRepository = accountRepository;
        this.roleService = roleService;
    }

    @Override
    public Account register(String login, String password) {
        Account account = new Account();
        account.setLogin(login);
        account.setPassword(password);
        account.setRoles(Set.of(roleService.getRoleByRoleName("USER")));
        return accountRepository.save(account);
    }
}
