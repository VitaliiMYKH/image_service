package com.voxloud.imageservice.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.voxloud.imageservice.exception.DataProcessingException;
import com.voxloud.imageservice.model.Tag;
import com.voxloud.imageservice.repository.TagRepository;
import com.voxloud.imageservice.service.TagService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TagServiceImplTest {
    private TagRepository tagRepository;
    private TagService tagService;
    private Tag unsavedTag;
    private Tag savedTag;
    private String tagName;
    private Long tagId;


    @BeforeEach
    void setUp() {
        tagRepository = Mockito.mock(TagRepository.class);
        tagService = new TagServiceImpl(tagRepository);
        tagName = "testTagName";
        tagId = 1L;
        unsavedTag = new Tag();
        unsavedTag.setTagName(tagName);
        savedTag = new Tag();
        savedTag.setId(tagId);
        savedTag.setTagName(tagName);
    }

    @Test
    void addTag() {
        Mockito.when(tagRepository.save(unsavedTag)).thenReturn(savedTag);
        Tag actual = tagService.addTag(unsavedTag);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(savedTag, actual);
    }

    @Test
    void getTagsById_Ok() {
        List<Long> tagsId = List.of(tagId);
        Mockito.when(tagRepository.getTagById(tagId)).thenReturn(Optional.of(savedTag));
        List<Tag> actual = tagService.getTagsById(tagsId);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(List.of(savedTag), actual);
    }

    @Test
    void getTagsByTagName_Ok() {
        Mockito.when(tagRepository.getTagByTagName(tagName)).thenReturn(Optional.of(savedTag));
        Tag actual = tagService.getTagByTagName(tagName);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(savedTag, actual);
    }

    @Test
    void getTagsByTagName_NotOk() {
        String invalidTagName = "invalidTagName";
        Mockito.when(tagRepository.getTagByTagName(tagName)).thenReturn(Optional.of(savedTag));
        try {
            tagService.getTagByTagName(invalidTagName);
        } catch (DataProcessingException e) {
            Assertions.assertEquals("Can`t get tag by tag name: " + invalidTagName, e.getMessage());
            return;
        }
        Assertions.fail("Expected to receive DataProcessingException");
    }
}