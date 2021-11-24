package com.voxloud.image_service.repository;

import com.voxloud.image_service.model.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> getByLogin(String login);
}
