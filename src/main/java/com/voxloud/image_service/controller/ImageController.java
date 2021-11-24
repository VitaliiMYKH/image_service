package com.voxloud.image_service.controller;

import com.voxloud.image_service.dto.ImageResponseDto;
import com.voxloud.image_service.model.Account;
import com.voxloud.image_service.service.AccountService;
import com.voxloud.image_service.service.ImageService;
import com.voxloud.image_service.service.mapper.ImageMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images")
public class ImageController {
    private final ImageService imageService;
    private final AccountService accountService;
    private final ImageMapper imageMapper;

    @Autowired
    public ImageController(ImageService imageService, AccountService accountService, ImageMapper imageMapper) {
        this.imageService = imageService;
        this.accountService = accountService;
        this.imageMapper = imageMapper;
    }

    @GetMapping("/{id}")
    public ImageResponseDto getById(@PathVariable Long id) {
        return imageMapper.mapToDto(imageService.getById(id));
    }

    @GetMapping("/by-name")
    public ImageResponseDto getByName(@RequestParam String name) {
        return imageMapper.mapToDto(imageService.getByName(name));
    }

    @GetMapping("/by-size")
    public List<ImageResponseDto> getBySize(@RequestParam Long size) {
       return imageService.getBySize(size)
               .stream()
               .map(imageMapper::mapToDto)
               .collect(Collectors.toList());
    }

    @GetMapping("/by-reference")
    public ImageResponseDto getByReference(@RequestParam String reference) {
        return imageMapper.mapToDto(imageService.getByReference(reference));

    }
    @GetMapping("/by-contentType")
    public List<ImageResponseDto> getByContentType(@RequestParam String contentType) {
        return imageService.getByContentType(contentType)
                .stream()
                .map(imageMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-account")
    public List<ImageResponseDto> getByAccount(Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String username = principal.getUsername();
        Account account = accountService.getByLogin(username);
        return imageService.getByAccount(account)
                .stream()
                .map(imageMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
