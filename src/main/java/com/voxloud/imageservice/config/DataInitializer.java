package com.voxloud.imageservice.config;

import com.voxloud.imageservice.model.Account;
import com.voxloud.imageservice.model.Role;
import com.voxloud.imageservice.service.AccountService;
import com.voxloud.imageservice.service.RoleService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleService roleService;

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setRoleName("ADMIN");
        roleService.save(adminRole);
        Role userRole = new Role();
        userRole.setRoleName("USER");
        roleService.save(userRole);
        Account user = new Account();
        user.setLogin("user@gmail.com");
        user.setPassword("u12345");
        user.setRoles(Set.of(userRole));
        accountService.add(user);
        Account admin = new Account();
        admin.setLogin("admin@gmail.com");
        admin.setPassword("a12345");
        admin.setRoles(Set.of(adminRole));
        accountService.add(admin);
    }
}
