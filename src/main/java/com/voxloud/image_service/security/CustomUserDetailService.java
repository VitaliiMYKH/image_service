package com.voxloud.image_service.security;

import com.voxloud.image_service.model.Account;
import com.voxloud.image_service.model.Role;
import com.voxloud.image_service.service.AccountService;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    public final AccountService accountService;

    @Autowired
    public CustomUserDetailService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Account account = accountService.getByLogin(login);
        User.UserBuilder userBuilder = User.withUsername(login);
        userBuilder.password(account.getPassword());
        userBuilder.roles(account.getRoles()
                .stream()
                .map(Role::getRoleName)
                .toArray(String[]::new));
        return userBuilder.build();
    }
}
