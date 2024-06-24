package com.devsu.microservice.account.api.service;

import com.devsu.microservice.account.api.dto.ClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clientService")
public interface ClientServiceClient {

    @GetMapping("/{id}")
    ClientDTO getClientById(@PathVariable("id") Long id);
}


