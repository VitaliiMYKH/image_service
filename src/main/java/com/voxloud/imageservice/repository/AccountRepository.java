package com.voxloud.imageservice.repository;

import com.voxloud.imageservice.model.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("select a from Account a join fetch a.roles "
            + "join fetch a.images where LOWER(a.login) = LOWER(:login)")
    Optional<Account> getByLogin(@Param("login") String login);

    Optional<Account> getAccountById(Long id);
}
