package com.devsu.microservice.account.api.service;

import com.devsu.microservice.account.api.async.AsyncClientService;
import com.devsu.microservice.account.api.dto.ClientDTO;
import com.devsu.microservice.account.api.dto.AccountResponse;
import com.devsu.microservice.account.api.entity.Account;
import com.devsu.microservice.account.api.exception.AccountNotFoundException;
import com.devsu.microservice.account.api.model.AccountType;
import com.devsu.microservice.account.api.repository.AccountRepository;
import com.devsu.microservice.common.exception.InvalidFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AsyncClientService asyncClientService;

    @Override
    public AccountResponse createAccount(Account account) throws ExecutionException, InterruptedException {
        CompletableFuture<ClientDTO> clientFuture = asyncClientService.getClientById(account.getClientId());

        ClientDTO client = clientFuture.get();
        AccountResponse response = AccountResponse.fromAccountAndClientName(account, client.getName());

        accountRepository.save(account);
        return response;
    }

    @Override
    public AccountResponse getAccountById(Long id) throws ExecutionException, InterruptedException {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        ClientDTO client = asyncClientService.getClientById(account.getClientId()).get();

        return AccountResponse.fromAccountAndClientName(account, client.getName());
    }

    @Override
    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll().stream().map(account -> {
            ClientDTO client = null;
            try {
                client = asyncClientService.getClientById(account.getClientId()).get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return AccountResponse.fromAccountAndClientName(account, client.getName());
        }).toList();
    }

    @Override
    public AccountResponse updateAccount(Long id, Account account) throws ExecutionException, InterruptedException {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        ClientDTO client = asyncClientService.getClientById(account.getClientId()).get();

        existingAccount.copy(account);
        accountRepository.save(existingAccount);

        return AccountResponse.fromAccountAndClientName(existingAccount, client.getName());
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public AccountResponse patchAccount(Long id, Map<String, Object> updates) throws ExecutionException, InterruptedException {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        ClientDTO client = asyncClientService.getClientById(existingAccount.getClientId()).get();

        updates.forEach((key, value) -> {
            switch (key) {
                case "accountNumber":
                    existingAccount.setAccountNumber((String) value);
                    break;
                case "balance":
                    existingAccount.setBalance((Double) value);
                    break;
                case "accountType":
                    existingAccount.setAccountType(AccountType.valueOf((String) value));
                    break;
                case "status":
                    existingAccount.setStatus((Boolean) value);
                    break;
                default:
                    throw new InvalidFieldException(key);
            }
        });

        accountRepository.save(existingAccount);
        return AccountResponse.fromAccountAndClientName(existingAccount, client.getName());
    }

}
