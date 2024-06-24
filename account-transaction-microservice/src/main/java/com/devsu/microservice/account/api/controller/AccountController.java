package com.devsu.microservice.account.api.controller;

import com.devsu.microservice.account.api.dto.AccountResponse;
import com.devsu.microservice.account.api.entity.Account;
import com.devsu.microservice.account.api.service.AccountService;
import com.devsu.microservice.account.api.validation.group.OnCreate;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/cuentas")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Validated({OnCreate.class, Default.class}) @RequestBody Account account) throws ExecutionException, InterruptedException {
        AccountResponse response = accountService.createAccount(account);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable Long id) throws ExecutionException, InterruptedException {
        AccountResponse response = accountService.getAccountById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        List<AccountResponse> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponse> updateAccount(@PathVariable Long id, @Validated @RequestBody Account account) throws ExecutionException, InterruptedException {
        AccountResponse updatedAccount = accountService.updateAccount(id, account);
        return ResponseEntity.ok(updatedAccount);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AccountResponse> patchAccount(@PathVariable Long id, @RequestBody Map<String, Object> updates) throws ExecutionException, InterruptedException {
        AccountResponse patchedAccount = accountService.patchAccount(id, updates);
        return ResponseEntity.ok(patchedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }


}
