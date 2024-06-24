package com.devsu.microservice.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidFieldException extends RuntimeException {

    private static final String INVALID_FIELD = "Campo inválido: ";

    public InvalidFieldException(String field) {
        super(INVALID_FIELD + field);
    }
}
