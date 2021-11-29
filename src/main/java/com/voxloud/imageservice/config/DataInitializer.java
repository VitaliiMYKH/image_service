package com.voxloud.imageservice.config;

import com.voxloud.imageservice.model.Account;
import com.voxloud.imageservice.model.Image;
import com.voxloud.imageservice.model.Role;
import com.voxloud.imageservice.model.Tag;
import com.voxloud.imageservice.service.AccountService;
import com.voxloud.imageservice.service.ImageService;
import com.voxloud.imageservice.service.RoleService;
import com.voxloud.imageservice.service.TagService;
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
    @Autowired
    private TagService tagService;

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
        Tag nature = new Tag();
        nature.setTagName("nature");
        tagService.addTag(nature);
        Tag art = new Tag();
        art.setTagName("art");
        tagService.addTag(art);

        Image imageUser = new Image();
        imageUser.setName("userImage");
        imageUser.setReference("images.com");
        imageUser.setContentType("entertainment");
        imageUser.setSize(700L);
        imageUser.setTags(List.of(nature));
        imageService.addListOfImages(user, List.of(imageUser));

        Image imageAdmin = new Image();
        imageAdmin.setName("adminImage");
        imageAdmin.setReference("admin.com");
        imageAdmin.setContentType("work");
        imageAdmin.setSize(700L);
        imageAdmin.setTags(List.of(art));
        imageService.addListOfImages(admin, List.of(imageAdmin));
    }
}
