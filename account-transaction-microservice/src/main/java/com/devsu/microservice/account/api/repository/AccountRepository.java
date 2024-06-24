package com.devsu.microservice.account.api.repository;

import com.devsu.microservice.account.api.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByAccountNumber(String accountNumber);
    Optional<Account> getAccountByAccountNumber(String accountNumber);
}
