package com.voxloud.imageservice.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.voxloud.imageservice.exception.DataProcessingException;
import com.voxloud.imageservice.model.Account;
import com.voxloud.imageservice.model.Image;
import com.voxloud.imageservice.model.Role;
import com.voxloud.imageservice.model.Tag;
import com.voxloud.imageservice.repository.ImageRepository;
import com.voxloud.imageservice.repository.TagRepository;
import com.voxloud.imageservice.service.AccountService;
import com.voxloud.imageservice.service.ImageService;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.crypto.spec.OAEPParameterSpec;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ImageServiceImplTest {
    private ImageRepository imageRepository;
    private TagRepository tagRepository;
    private AccountService accountService;
    private ImageService imageService;

    private Role role;
    private String roleName;
    private Tag tag;
    private Image image;
    private Account account;
    private String password;
    private String login;
    private Account updatedAccount;
    private Image updatedImage;
    private Long id;
    private Long invalidId;
    private String imageContentType;
    private String imageName;
    private String imageReference;
    private Long imageSize;

    @BeforeEach
    void setUp() {
       imageRepository = Mockito.mock(ImageRepository.class);
       tagRepository = Mockito.mock(TagRepository.class);
       accountService = Mockito.mock(AccountService.class);
       imageService = new ImageServiceImpl(imageRepository, tagRepository, accountService);
       id = 1L;
       invalidId = 2L;
        roleName = "USER";
        role = new Role();
        role.setId(id);
        role.setRoleName(roleName);
        tag = new Tag();
        tag.setTagName("testTagName");
        imageContentType = "testContentType";
        imageName = "testName";
        imageReference = "testReference";
        imageSize = 10L;
        image = new Image();
        image.setContentType(imageContentType);
        image.setName(imageName);
        image.setReference(imageReference);
        image.setSize(imageSize);
        image.setTags(List.of(tag));
        updatedImage = new Image();
        updatedImage.setContentType("testContentType");
        updatedImage.setName("testName");
        updatedImage.setReference("testReference" );
        updatedImage.setSize(10L);
        updatedImage.setTags(List.of(tag));
        updatedImage.setId(id);
        password = "1234";
        login = "test@mail.com";
        account = new Account();
        account.setId(1L);
        account.setLogin(login);
        account.setPassword(password);
        account.setRoles(Set.of(role));
        updatedAccount = new Account();
        updatedAccount.setId(id);
        updatedAccount.setLogin(login);
        updatedAccount.setPassword(password);
        updatedAccount.setRoles(Set.of(role));
        updatedAccount.setImages(List.of(updatedImage));
    }

    @Test
    void addListOfImages_Ok() {
        Mockito.when(accountService.updateAccount(account,List.of(image))).thenReturn(updatedAccount);
        Mockito.when(imageRepository.saveAll(List.of(image))).thenReturn(List.of(updatedImage));
        List<Image> actual = imageService.addListOfImages(account, List.of(image));
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(List.of(updatedImage), actual);
    }

    @Test
    void getById_Ok() {
        Mockito.when(imageRepository.getImageById(id)).thenReturn(Optional.of(updatedImage));
        Image actual = imageService.getById(updatedAccount, id);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(updatedImage, actual);
    }

    @Test
    void getById_IdNotOk() {
        Mockito.when(imageRepository.getImageById(id)).thenReturn(Optional.of(updatedImage));
        try {
            imageService.getById(updatedAccount,invalidId);
        } catch (DataProcessingException e) {
            Assertions.assertEquals("Can`t get image by id " + invalidId, e.getMessage());
            return;
        }
        Assertions.fail("Expected to receive DataProcessingException");
    }

    @Test
    void getById_accountNotOk() {
        account.setImages(List.of(image));
        Mockito.when(imageRepository.getImageById(id)).thenReturn(Optional.of(updatedImage));
        try {
            imageService.getById(account, id);
        } catch (DataProcessingException e) {
            Assertions.assertEquals("Can`t get Image, which is not in current account", e.getMessage());
            return;
        }
        Assertions.fail("Expected to receive DataProcessingException");
    }

    @Test
    void getByName_Ok() {
        Mockito.when(imageRepository.getByName(imageName)).thenReturn(Optional.of(updatedImage));
        Image actual = imageService.getByName(updatedAccount, imageName);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(updatedImage, actual);
    }

    @Test
    void getByName_nameNotOk() {
        String invalidName = "invalid";
        Mockito.when(imageRepository.getByName(imageName)).thenReturn(Optional.of(updatedImage));
        try {
            Image actual = imageService.getByName(updatedAccount, invalidName);
        } catch (DataProcessingException e) {
            Assertions.assertEquals("Can`t get image by name " + invalidName, e.getMessage());
            return;
        }
        Assertions.fail("Expected to receive DataProcessingException");
    }

    @Test
    void getByName_accountNotOk() {
        account.setImages(List.of(image));
        Mockito.when(imageRepository.getByName(imageName)).thenReturn(Optional.of(updatedImage));
        try {
            Image actual = imageService.getByName(account, imageName);
        } catch (DataProcessingException e) {
            Assertions.assertEquals("Can`t get Image, which is not in current account", e.getMessage());
            return;
        }
        Assertions.fail("Expected to receive DataProcessingException");
    }

    @Test
    void getBySize_Ok() {
        Mockito.when(imageRepository.getBySize(imageSize)).thenReturn(List.of(updatedImage));
        List<Image> actual = imageService.getBySize(updatedAccount, imageSize);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(List.of(updatedImage), actual);
    }

    @Test
    void getBySize_sizeNotOk() {
        Long invalidSize = 5L;
        Mockito.when(imageRepository.getBySize(imageSize)).thenReturn(List.of(updatedImage));
        try {
            List<Image> actual = imageService.getBySize(updatedAccount, invalidSize);
        } catch (DataProcessingException e) {
            Assertions.assertEquals("Can`t get images by size: " + invalidSize, e.getMessage());
            return;
        }
        Assertions.fail("Expected to receive DataProcessingException");
    }

    @Test
    void getBySize_accountNotOk() {
        account.setImages(List.of(image));
        Mockito.when(imageRepository.getBySize(imageSize)).thenReturn(List.of(updatedImage));
        try {
            List<Image> actual = imageService.getBySize(account, imageSize);
        } catch (DataProcessingException e) {
            Assertions.assertEquals("Can`t get Image, which is not in current account", e.getMessage());
            return;
        }
        Assertions.fail("Expected to receive DataProcessingException");
    }

    @Test
    void getByReference_Ok() {
        Mockito.when(imageRepository.getByReference(imageReference)).thenReturn(Optional.of(updatedImage));
        Image actual = imageService.getByReference(updatedAccount, imageReference);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(updatedImage, actual);
    }

    @Test
    void getByReference_referenceNotOk() {
        String invalidReference = "invalidReference";
        Mockito.when(imageRepository.getByReference(imageReference)).thenReturn(Optional.of(updatedImage));
        try {
            Image actual = imageService.getByReference(updatedAccount, invalidReference);
        } catch (DataProcessingException e) {
            Assertions.assertEquals("Can`t get image by reference: " + invalidReference, e.getMessage());
            return;
        }
        Assertions.fail("Expected to receive DataProcessingException");
    }

    @Test
    void getByReference_accountNotOk() {
        account.setImages(List.of(image));
        Mockito.when(imageRepository.getByReference(imageReference)).thenReturn(Optional.of(updatedImage));
        try {
            Image actual = imageService.getByReference(account, imageReference);
        } catch (DataProcessingException e) {
            Assertions.assertEquals("Can`t get Image, which is not in current account", e.getMessage());
            return;
        }
        Assertions.fail("Expected to receive DataProcessingException");
    }

    @Test
    void getByContentType_Ok() {
        Mockito.when(imageRepository.getByContentType(imageContentType)).thenReturn(List.of(updatedImage));
        List<Image> actual = imageService.getByContentType(updatedAccount, imageContentType);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(List.of(updatedImage), actual);
    }

    @Test
    void getByContentType_contentTypeNotOk() {
        String invalidContentType = "invalidContentTpe";
        Mockito.when(imageRepository.getByContentType(imageContentType)).thenReturn(List.of(updatedImage));
        try {
            List<Image> actual = imageService.getByContentType(updatedAccount, invalidContentType);
        } catch (DataProcessingException e) {
            Assertions.assertEquals("Can`t get images by content type: " + invalidContentType, e.getMessage());
            return;
        }
        Assertions.fail("Expected to receive DataProcessingException");
    }

    @Test
    void getByContentType_accountNotOk() {
        account.setImages(List.of(image));
        Mockito.when(imageRepository.getByContentType(imageContentType)).thenReturn(List.of(updatedImage));
        try {
            List<Image> actual = imageService.getByContentType(account, imageContentType);
        } catch (DataProcessingException e) {
            Assertions.assertEquals("Can`t get Image, which is not in current account", e.getMessage());
            return;
        }
        Assertions.fail("Expected to receive DataProcessingException");
    }

    @Test
    void getImageByTag_Ok() {
        Mockito.when(imageRepository.getImageByTags(tag)).thenReturn(List.of(updatedImage));
        List<Image> actual = imageService.getImageByTag(updatedAccount, tag);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(List.of(updatedImage), actual);
    }

    @Test
    void getImageByTag_tagNotOk() {
        Tag invalidTag = new Tag();
        invalidTag.setTagName("invalidTagName");
        Mockito.when(imageRepository.getImageByTags(tag)).thenReturn(List.of(updatedImage));
        try {
            List<Image> actual = imageService.getImageByTag(updatedAccount, invalidTag);
        } catch (DataProcessingException e) {
            Assertions.assertEquals("Can`t get Images with tag: " + invalidTag, e.getMessage());
            return;
        }
        Assertions.fail("Expected to receive DataProcessingException");
    }

    @Test
    void getImageByTag_accountNotOk() {
        account.setImages(List.of(image));
        Mockito.when(imageRepository.getImageByTags(tag)).thenReturn(List.of(updatedImage));
        try {
            List<Image> actual = imageService.getImageByTag(account, tag);
        } catch (DataProcessingException e) {
            Assertions.assertEquals("Can`t get images which is not in current account", e.getMessage());
            return;
        }
        Assertions.fail("Expected to receive DataProcessingException");
    }
}