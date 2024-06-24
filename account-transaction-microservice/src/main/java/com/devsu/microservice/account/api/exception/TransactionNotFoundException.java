package com.devsu.microservice.account.api.exception;

import com.devsu.microservice.common.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TransactionNotFoundException extends ResourceNotFoundException {
    private static final String TRANSACTION_NOT_FOUND_BY_TRANSACTION_ID = "El movimiento no existe (ID de movimiento): ";

    public TransactionNotFoundException(long transactionId) {
        super(TRANSACTION_NOT_FOUND_BY_TRANSACTION_ID + transactionId);
    }
}
