package com.devsu.microservice.account.api.async;


import com.devsu.microservice.account.api.dto.ClientDTO;
import com.devsu.microservice.account.api.exception.ClientNotFoundException;
import com.devsu.microservice.account.api.service.ClientServiceClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
@Service
public class AsyncClientService {

    @Autowired
    private ClientServiceClient clientServiceClient;

    @Async
    public CompletableFuture<ClientDTO> getClientById(Long clientId) {
        try {
            ClientDTO client = clientServiceClient.getClientById(clientId);
            return CompletableFuture.completedFuture(client);
        } catch (FeignException e) {
            if (e.status() == 404) {
                throw new ClientNotFoundException(clientId);
            }
            throw e;
        }
    }
}
