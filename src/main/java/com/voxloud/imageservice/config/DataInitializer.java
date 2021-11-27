package com.voxloud.imageservice.config;

import com.voxloud.imageservice.model.Account;
import com.voxloud.imageservice.model.Image;
import com.voxloud.imageservice.model.Role;
import com.voxloud.imageservice.service.AccountService;
import com.voxloud.imageservice.service.ImageService;
import com.voxloud.imageservice.service.RoleService;
import java.util.List;
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
    @Autowired
    private ImageService imageService;

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
      //  user.setImages(List.of(imageUser));
        accountService.add(user);
        Account admin = new Account();
        admin.setLogin("admin@gmail.com");
        admin.setPassword("a12345");
        admin.setRoles(Set.of(adminRole));
   //     admin.setImages(List.of(imageAdmin));
        accountService.add(admin);


        Image imageUser = new Image();
        imageUser.setName("userImage");
        imageService.addListOfImages(user, List.of(imageUser));
        Image imageAdmin = new Image();
        imageAdmin.setName("adminImage");
        imageService.addListOfImages(admin, List.of(imageAdmin));
    }
}
