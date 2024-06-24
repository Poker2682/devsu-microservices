package com.devsu.microservice.account.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InsufficientFundsException extends RuntimeException {

    private static final String INSUFFICIENT_FUNDS = "Fondos insuficientes";

    public InsufficientFundsException() {
        super(INSUFFICIENT_FUNDS);
    }
}
