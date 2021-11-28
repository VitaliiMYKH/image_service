package com.voxloud.imageservice.controller;

import com.voxloud.imageservice.dto.TagRequestDto;
import com.voxloud.imageservice.dto.TagResponseDto;
import com.voxloud.imageservice.service.AccountService;
import com.voxloud.imageservice.service.TagService;
import com.voxloud.imageservice.service.mapper.ImageMapper;
import com.voxloud.imageservice.service.mapper.TagMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;
    private final AccountService accountService;
    private final ImageMapper imageMapper;

    public TagController(TagService tagService,
                         TagMapper tagMapper,
                         AccountService accountService,
                         ImageMapper imageMapper) {
        this.tagService = tagService;
        this.tagMapper = tagMapper;
        this.accountService = accountService;
        this.imageMapper = imageMapper;
    }

    @PostMapping
    public TagResponseDto addTag(@RequestBody TagRequestDto tagRequestDto) {
        return tagMapper.mapToDto(tagService.addTag(tagMapper.mapToModel(tagRequestDto)));
    }

    @GetMapping
    public TagResponseDto getTagByTagName(@RequestParam String tagName) {
        return tagMapper.mapToDto(tagService.getTagByTagName(tagName));
    }
}
