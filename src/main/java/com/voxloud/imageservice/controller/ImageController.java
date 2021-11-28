package com.voxloud.imageservice.controller;

import com.voxloud.imageservice.dto.ImageRequestDto;
import com.voxloud.imageservice.dto.ImageResponseDto;
import com.voxloud.imageservice.model.Account;
import com.voxloud.imageservice.model.Image;
import com.voxloud.imageservice.model.Tag;
import com.voxloud.imageservice.service.AccountService;
import com.voxloud.imageservice.service.ImageService;
import com.voxloud.imageservice.service.TagService;
import com.voxloud.imageservice.service.mapper.ImageMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images")
public class ImageController {
    private final ImageService imageService;
    private final AccountService accountService;
    private final ImageMapper imageMapper;
    private final TagService tagService;

    @Autowired
    public ImageController(ImageService imageService,
                           AccountService accountService,
                           ImageMapper imageMapper, TagService tagService) {
        this.imageService = imageService;
        this.accountService = accountService;
        this.imageMapper = imageMapper;
        this.tagService = tagService;
    }

    @PostMapping("/add")
    public List<ImageResponseDto> add(Authentication auth,
                                      @RequestBody List<ImageRequestDto> imageRequestDto) {
        UserDetails principal = (UserDetails) auth.getPrincipal();
        String username = principal.getUsername();
        Account account = accountService.getByLogin(username);
        List<Image> images = imageRequestDto.stream()
                .map(imageMapper::mapToModel)
                .collect(Collectors.toList());
        List<Image> savedImages = imageService.addListOfImages(account, images);
        return savedImages.stream()
                .map(imageMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ImageResponseDto getById(Authentication auth, @PathVariable Long id) {
        UserDetails principal = (UserDetails) auth.getPrincipal();
        String username = principal.getUsername();
        Account account = accountService.getByLogin(username);
        return imageMapper.mapToDto(imageService.getById(account, id));
    }

    @GetMapping("/by-name")
    public ImageResponseDto getByName(Authentication auth, @RequestParam String name) {
        UserDetails principal = (UserDetails) auth.getPrincipal();
        String username = principal.getUsername();
        Account account = accountService.getByLogin(username);
        return imageMapper.mapToDto(imageService.getByName(account, name));
    }

    @GetMapping("/by-size")
    public List<ImageResponseDto> getBySize(Authentication auth, @RequestParam Long size) {
        UserDetails principal = (UserDetails) auth.getPrincipal();
        String username = principal.getUsername();
        Account account = accountService.getByLogin(username);
        return imageService.getBySize(account, size)
               .stream()
               .map(imageMapper::mapToDto)
               .collect(Collectors.toList());
    }

    @GetMapping("/by-reference")
    public ImageResponseDto getByReference(Authentication auth, @RequestParam String reference) {
        UserDetails principal = (UserDetails) auth.getPrincipal();
        String username = principal.getUsername();
        Account account = accountService.getByLogin(username);
        return imageMapper.mapToDto(imageService.getByReference(account, reference));

    }

    @GetMapping("/by-contentType")
    public List<ImageResponseDto> getByContentType(Authentication auth,
                                                   @RequestParam String contentType) {
        UserDetails principal = (UserDetails) auth.getPrincipal();
        String username = principal.getUsername();
        Account account = accountService.getByLogin(username);
        return imageService.getByContentType(account, contentType)
                .stream()
                .map(imageMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("by-tagName")
    public List<ImageResponseDto> getByTagName(Authentication auth, @RequestParam String tagName) {
        UserDetails principal = (UserDetails) auth.getPrincipal();
        String username = principal.getUsername();
        Account account = accountService.getByLogin(username);
        Tag tagByTagName = tagService.getTagByTagName(tagName);
        return imageService.getImageByTag(account, tagByTagName)
                .stream()
                .map(imageMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
