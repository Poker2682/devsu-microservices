package com.devsu.microservice.account.api.exception;

import com.devsu.microservice.common.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientNotFoundException extends ResourceNotFoundException {
    private static final String CLIENT_NOT_FOUND_BY_CLIENT_ID = "El cliente no existe (ID de cliente): ";

    public ClientNotFoundException(long clientId) {
        super(CLIENT_NOT_FOUND_BY_CLIENT_ID + clientId);
    }

}

