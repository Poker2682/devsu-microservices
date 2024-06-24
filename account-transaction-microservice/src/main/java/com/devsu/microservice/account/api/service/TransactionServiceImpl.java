package com.devsu.microservice.account.api.service;

import com.devsu.microservice.account.api.dto.TransactionRequest;
import com.devsu.microservice.account.api.dto.TransactionResponse;
import com.devsu.microservice.account.api.entity.Account;
import com.devsu.microservice.account.api.entity.Transaction;
import com.devsu.microservice.account.api.exception.AccountNotFoundException;
import com.devsu.microservice.account.api.exception.InsufficientFundsException;
import com.devsu.microservice.account.api.exception.TransactionNotFoundException;
import com.devsu.microservice.account.api.repository.AccountRepository;
import com.devsu.microservice.account.api.repository.TransactionRepository;
import com.devsu.microservice.common.exception.InvalidFieldException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public TransactionResponse createTransaction(TransactionRequest transactionRequest) {
        Account account = accountRepository.getAccountByAccountNumber(transactionRequest.getAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException(transactionRequest.getAccountNumber()));

        account.setBalance(account.getBalance() + transactionRequest.getAmount());

        if(account.getBalance() < 0) {
            throw new InsufficientFundsException();
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionRequest.getAmount());
        transaction.inferType();
        transaction.setAccount(account);
        transaction.setDate(transactionRequest.getDate() == null ? new Date() : transactionRequest.getDate());
        transaction.setBalanceAfterTransaction(account.getBalance());

        transactionRepository.save(transaction);
        accountRepository.save(account);

        return TransactionResponse.fromTransactionEntity(transaction);
    }

    @Override
    public TransactionResponse getTransactionById(Long id) {
        Transaction found = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
        return TransactionResponse.fromTransactionEntity(found);
    }

    @Override
    public List<TransactionResponse> getAllTransactions() {
         return transactionRepository.findAll().stream().map(TransactionResponse::fromTransactionEntity).toList();
    }

    @Override
    @Transactional
    public TransactionResponse updateTransaction(Long id, TransactionRequest transactionRequest) {
        Transaction existingTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
        Transaction existingTransactionBackup = new Transaction();
        existingTransactionBackup.copy(existingTransaction);

        Double newTransactionAmount = transactionRequest.getAmount();

        Account newAccount = accountRepository.getAccountByAccountNumber(transactionRequest.getAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException(transactionRequest.getAccountNumber()));

        existingTransaction.setAmount(newTransactionAmount);
        existingTransaction.inferType();
        existingTransaction.setAccount(newAccount);
        existingTransaction.setDate(transactionRequest.getDate());

        Double balanceAfterTransaction = updateAccountBalanceBeforeTransactionUpdate(existingTransactionBackup, existingTransaction);
        existingTransaction.setBalanceAfterTransaction(balanceAfterTransaction);


        transactionRepository.save(existingTransaction);
        return TransactionResponse.fromTransactionEntity(existingTransaction);
    }

    @Override
    @Transactional
    public void deleteTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));

        Double amount = transaction.getAmount();
        Account account = transaction.getAccount();

        account.setBalance(account.getBalance() - amount);

        transactionRepository.deleteById(id);
        accountRepository.save(account);
    }

    @Override
    @Transactional
    public TransactionResponse patchTransaction(Long id, Map<String, Object> updates) {
        Transaction existingTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));

        Transaction existingTransactionBackup = new Transaction();
        existingTransactionBackup.copy(existingTransaction);

        updates.forEach((key, value) -> {
            switch (key) {
                case "date":
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
                    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

                    Date date;
                    try {
                        date = sdf.parse((String) value);
                    } catch (ParseException e) {
                        throw new InvalidFieldException((String) value);
                    }
                    
                    existingTransaction.setDate(date);
                    break;
                case "accountNumber":
                    Account account = accountRepository.getAccountByAccountNumber((String) value)
                            .orElseThrow(() -> new AccountNotFoundException((String) value));
                    existingTransaction.setAccount(account);
                    break;
                case "amount":
                    existingTransaction.setAmount((Double) value);
                    Double balanceAfterTransaction = updateAccountBalanceBeforeTransactionUpdate(existingTransactionBackup, existingTransaction);
                    existingTransaction.setBalanceAfterTransaction(balanceAfterTransaction);
                    existingTransaction.inferType();
                    break;
                default:
                    throw new InvalidFieldException(key);
            }
        });

        transactionRepository.save(existingTransaction);
        return TransactionResponse.fromTransactionEntity(existingTransaction);
    }

    private Double updateAccountBalanceBeforeTransactionUpdate(Transaction existingTransaction, Transaction newTransaction) {
        Account newAccount = newTransaction.getAccount();
        Double newTransactionAmount = newTransaction.getAmount();
        Double previousTransactionAmount = existingTransaction.getAmount();
        Account previousAccount = existingTransaction.getAccount();

        previousAccount.setBalance(previousAccount.getBalance() - previousTransactionAmount);
        newAccount.setBalance(newAccount.getBalance() + newTransactionAmount);

        if(newAccount.getBalance() < 0) {
            throw new InsufficientFundsException();
        }

        accountRepository.save(previousAccount);
        accountRepository.save(newAccount);

        return newAccount.getBalance();
    }
}
