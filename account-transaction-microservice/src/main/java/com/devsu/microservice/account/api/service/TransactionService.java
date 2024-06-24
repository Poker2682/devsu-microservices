package com.devsu.microservice.account.api.service;

import com.devsu.microservice.account.api.dto.TransactionRequest;
import com.devsu.microservice.account.api.dto.TransactionResponse;

import java.util.List;
import java.util.Map;

public interface TransactionService {
    TransactionResponse createTransaction(TransactionRequest transaction);
    TransactionResponse getTransactionById(Long id);
    List<TransactionResponse> getAllTransactions();
    TransactionResponse updateTransaction(Long id, TransactionRequest transaction);
    void deleteTransaction(Long id);
    TransactionResponse patchTransaction(Long id, Map<String, Object> updates);
}
