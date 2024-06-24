package com.devsu.microservice.account.api.exception;

import com.devsu.microservice.common.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends ResourceNotFoundException {
    private static final String ACCOUNT_NOT_FOUND_BY_ACCOUNT_NUMBER = "La cuenta no existe (Número de cuenta): ";
    private static final String ACCOUNT_NOT_FOUND_BY_ACCOUNT_ID = "La cuenta no existe (ID de cuenta): ";

    public AccountNotFoundException(String accountNumber) {
        super(ACCOUNT_NOT_FOUND_BY_ACCOUNT_NUMBER + accountNumber);
    }

    public AccountNotFoundException(long accountId) {
        super(ACCOUNT_NOT_FOUND_BY_ACCOUNT_ID + accountId);
    }

}
