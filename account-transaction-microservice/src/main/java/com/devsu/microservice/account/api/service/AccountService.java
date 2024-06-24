package com.devsu.microservice.account.api.service;

import com.devsu.microservice.account.api.dto.AccountResponse;
import com.devsu.microservice.account.api.entity.Account;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface AccountService {
    AccountResponse createAccount(Account account) throws ExecutionException, InterruptedException;
    AccountResponse getAccountById(Long id) throws ExecutionException, InterruptedException;
    List<AccountResponse> getAllAccounts();
    AccountResponse updateAccount(Long id, Account account) throws ExecutionException, InterruptedException;
    void deleteAccount(Long id);
    AccountResponse patchAccount(Long id, Map<String, Object> updates) throws ExecutionException, InterruptedException;
}
