package com.voxloud.image_service.service.mapper;

import com.voxloud.image_service.dto.AccountRequestDto;
import com.voxloud.image_service.dto.AccountResponseDto;
import com.voxloud.image_service.model.Account;
import com.voxloud.image_service.model.Image;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public AccountResponseDto mapToDto(Account account) {
        AccountResponseDto accountResponseDto = new AccountResponseDto();
        accountResponseDto.setId(account.getId());
        accountResponseDto.setLogin(account.getLogin());
        if (account.getImages() == null) {
            accountResponseDto.setImagesId(Collections.emptyList());
            return accountResponseDto;
        }
        List<Long> imagesId = account.getImages().stream()
                .map(Image::getId)
                .collect(Collectors.toList());
        accountResponseDto.setImagesId(imagesId);
        return accountResponseDto;
    }

   /* public Account toModel(AccountRequestDto accountRequestDto) {
        Account account = new Account();
        account.setLogin(accountRequestDto.getLogin());
    }*/
}
