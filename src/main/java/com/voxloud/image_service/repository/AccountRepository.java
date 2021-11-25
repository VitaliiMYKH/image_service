package com.voxloud.image_service.repository;

import com.voxloud.image_service.model.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "select a from Account a join fetch a.roles join fetch a.images where a.login = :login")
    Optional<Account> getByLogin(@Param(value = "login") String login);
}
