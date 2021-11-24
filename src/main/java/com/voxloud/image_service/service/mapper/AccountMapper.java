package com.voxloud.image_service.service.mapper;

import com.voxloud.image_service.dto.AccountRequestDto;
import com.voxloud.image_service.dto.AccountResponseDto;
import com.voxloud.image_service.model.Account;
import com.voxloud.image_service.model.Image;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public AccountResponseDto mapToDto(Account account) {
        AccountResponseDto accountResponseDto = new AccountResponseDto();
        accountResponseDto.setLogin(account.getLogin());
        accountResponseDto.setPassword(account.getPassword());
        accountResponseDto.setImages(account.getImages());
        return accountResponseDto;
    }

   /* public Account toModel(AccountRequestDto accountRequestDto) {
        Account account = new Account();
        account.setLogin(accountRequestDto.getLogin());
    }*/
}
